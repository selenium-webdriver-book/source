package swb.ch11drivers;

import org.slf4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

import static java.lang.System.getProperty;
import static java.net.URI.create;
import static java.nio.channels.Channels.newChannel;
import static java.nio.file.FileSystems.newFileSystem;
import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.Files.copy;
import static java.nio.file.Files.walkFileTree;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Collections.emptyMap;
import static org.slf4j.LoggerFactory.getLogger;

public class ChromeDriverBinarySupplier implements WebDriverBinarySupplier {

    private Logger LOGGER = getLogger(ChromeDriverBinarySupplier.class);
    private String BASE_PATH = "http://chromedriver.storage.googleapis.com";
    private Path download = Paths.get(getProperty("java.io.tmpdir"),
        "chrome-driver");  //<3>
    private String osName = getProperty("os.name"); // <1>
    private String osArch = getProperty("os.arch");
    private String os = osName.contains("win") ? "win" :
        osName.contains("nix") ? "linux" : "mac";
    private int arch = os.matches("linux|mac") && osArch.endsWith("64") ? 64 : 32;   //<2>

    @Override
    public Path get(Path driverDir) throws IOException {

        Path driverPath = resolvePath(driverDir);
        if (!driverPath.toFile().exists()) { //<4>
            if (!download.toFile().exists()) {
                download();
            }
            unzipFiles(driverPath);
            makeExecutable(driverPath);
        }
        return driverPath;
    }

    private void download() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(download.toFile())) {
            fos.getChannel().transferFrom(createChannel(), 0, Long.MAX_VALUE);
        }
    }

    private void unzipFiles(final Path driverPath) throws IOException {
        LOGGER.info("extracting chrome driver to " + driverPath);

        try (FileSystem fileSystem = createFile()) {
            walkFileTree(fileSystem.getPath("/"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(
                    Path file, BasicFileAttributes attrs) throws IOException {

                    LOGGER.info("unzipping " + file); //<7>
                    copy(file, driverPath, REPLACE_EXISTING);

                    return CONTINUE;
                }
            });
        }
    }

    private void makeExecutable(Path path) {
        LOGGER.info("making " + path + " executable");

        if (!path.toFile().setExecutable(true)) { //<8>
            throw new IllegalStateException("failed to make " + path + " executable");
        }
    }

    private ReadableByteChannel createChannel() throws IOException {
        URL url = createUrl();

        LOGGER.info("downloading " + url + " to " + download);
        return newChannel(url.openStream());           //<5>
    }

    private FileSystem createFile() throws IOException {
        return newFileSystem(create("jar:file:" + download), emptyMap());  //<6>
    }

    private URL createUrl() throws IOException {
        return new URL(BASE_PATH + "/" + lastRelease() +
            "/chromedriver_" + os + arch + ".zip");
    }

    private Path resolvePath(Path driverDir) {
        return driverDir.resolve(os.equals("win") ?
            "chromedriver.exe" : "chromedriver");
    }

    private String lastRelease() throws IOException {

        URL url = new URL(BASE_PATH + "/LATEST_RELEASE");

        try (Scanner scanner = new Scanner(url.openStream())) {
            return scanner.useDelimiter("\\A").next().trim(); //<9>
        }
    }
}
