package swip.ch11drivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;

public class ChromeDriverBinarySupplier implements WebDriverBinarySupplier {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChromeDriverBinarySupplier.class);

    @Override
    public Path get(Path driverDir) throws IOException {
        String osName = System.getProperty("os.name"); // <1> You typically need to know what operating system and architecture you are running on to get the binary.
        String osArch = System.getProperty("os.arch");

        String os = osName.contains("win") ? "win" : osName.contains("nix") ? "linux" : "mac";
        int arch = os.equals("linux") && osArch.endsWith("64") ? 64 : 32; // <2> Chrome has a 64 bit version for linux, 32 bit for everyone else.

        Path download = Paths.get(System.getProperty("java.io.tmpdir"), "chrome-driver"); // <3> The name and download destination.
        Path driverPath = driverDir.resolve(os.equals("win") ? "chromedriver.exe" : "chromedriver");

        if (!driverPath.toFile().exists()) { // <4> Do not do this if you already have it.

            URL url = new URL("http://chromedriver.storage.googleapis.com/2.16/chromedriver_" + os + arch + ".zip");

            if (!download.toFile().exists()) {
                LOGGER.info("downloading " + url + " to " + download);

                try (ReadableByteChannel rbc = Channels.newChannel(url.openStream()); // <5> Download the file using Java 7's NIO APIs.
                     FileOutputStream fos = new FileOutputStream(download.toFile())) {
                    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                }
            }

            LOGGER.info("extracting chrome driver to " + driverPath);

            try (FileSystem fileSystem = FileSystems.newFileSystem(
                    URI.create("jar:file:" + download), // <6> Java 7 can unpack a jar file automatically. A jar file is a zip file.
                    Collections.emptyMap())
            ) {
                Files.walkFileTree(fileSystem.getPath("/"), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                        LOGGER.info("unzipping " + file); // <7> We assume we wil only find a single file is the zip.
                        Files.copy(
                                file,
                                driverPath,
                                StandardCopyOption.REPLACE_EXISTING
                        );

                        return FileVisitResult.CONTINUE;
                    }
                });
            }


            LOGGER.info("making " + driverPath + " executable");

            if (!driverPath.toFile().setExecutable(true)) { // <8> Finally, we need to make it executable.
                throw new IllegalStateException("failed to make " + driverPath + " executable");
            }
        }

        return driverPath;
    }
}
