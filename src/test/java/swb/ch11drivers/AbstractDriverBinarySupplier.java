package swb.ch11drivers;

import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.System.getProperty;
import static java.nio.channels.Channels.newChannel;
import static org.slf4j.LoggerFactory.getLogger;

abstract class AbstractDriverBinarySupplier implements WebDriverBinarySupplier {
    static final Logger LOGGER = getLogger(ChromeDriverBinarySupplier.class);
    private static final String OS_NAME = getProperty("os.name").toLowerCase();
    private static final String OS_ARCH = getProperty("os.arch");
    private static final String TMP = getProperty("java.io.tmpdir");

    static {
        LOGGER.info("os.name=" + OS_NAME);
        LOGGER.info("os.arch=" + OS_ARCH);
        LOGGER.info("tmp=" + TMP);
    }

    static boolean isLinux() {
        return OS_NAME.contains("linux");
    }

    static boolean isWin() {
        return OS_NAME.contains("win");
    }

    static boolean is64Bit() {
        return OS_ARCH.endsWith("64");
    }

    @Override
    public Path get(Path driverDir) throws IOException {

        URL url = createUrl();
        Path download = Paths.get(TMP, url.getFile().replaceFirst("^.*/", ""));
        Path driverPath = resolvePath(driverDir);

        if (!download.toFile().exists()) {
            download(url, download);
        }
        if (!driverPath.toFile().exists()) {
            unpackFile(download, driverPath);
            makeExecutable(driverPath);
        }
        return driverPath;
    }

    abstract Path resolvePath(Path driverDir);

    abstract URL createUrl() throws IOException;

    private void download(URL url, Path download) throws IOException {
        LOGGER.info("downloading " + url + " to " + download);
        try (FileOutputStream out = new FileOutputStream(download.toFile())) {
            out.getChannel().transferFrom(newChannel(url.openStream()), 0, Long.MAX_VALUE);
        }
    }

    void unpackFile(Path download, Path driverPath) throws IOException {
        LOGGER.info("extracting driver from " + download + "  to " + driverPath);

        byte[] buffer = new byte[1024];
        try (ZipInputStream in = new ZipInputStream(new FileInputStream(download.toFile()))) {
            ZipEntry entry = in.getNextEntry();

            LOGGER.info("extracting " + entry.getName());

            try (FileOutputStream out = new FileOutputStream(driverPath.toFile())) {
                int len;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            }
        }
    }

    private void makeExecutable(Path path) {
        LOGGER.info("making " + path + " executable");

        if (!path.toFile().setExecutable(true)) {
            throw new IllegalStateException("failed to make " + path + " executable");
        }
    }

}
