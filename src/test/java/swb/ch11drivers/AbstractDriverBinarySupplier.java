package swb.ch11drivers;

import org.slf4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

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

abstract class AbstractDriverBinarySupplier implements WebDriverBinarySupplier {
    static final String OS_ARCH = getProperty("os.arch");
    static final String OS_NAME = getProperty("os.name");
    static final String OS = OS_NAME.contains("win") ? "win" :
            OS_NAME.contains("nix") ? "linux" : "mac";
    private static final Logger LOGGER = getLogger(ChromeDriverBinarySupplier.class);
    private final Path download = Paths.get(getProperty("java.io.tmpdir"),
            getClass().getSimpleName());

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

    abstract Path resolvePath(Path driverDir);

    abstract URL createUrl() throws IOException;

    private void download() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(download.toFile())) {
            fos.getChannel().transferFrom(createChannel(), 0, Long.MAX_VALUE);
        }
    }

    private void unzipFiles(final Path driverPath) throws IOException {
        LOGGER.info("extracting driver to " + driverPath);

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
}
