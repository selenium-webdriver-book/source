package swb.ch11drivers;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

public class FirefoxDriverBinarySupplier extends AbstractDriverBinarySupplier {

    private static final String FILE_BASE = "geckodriver";

    @Override
    URL createUrl() throws IOException {
        int arch = is64Bit() ? 64 : 32;
        return new URL(String.format("https://github.com/mozilla/geckodriver/releases/download/v0.11.1/geckodriver-v0.11.1-%s.%s",
                isWin() ? "win" + arch : isLinux() ? "linux" + arch : "macos",
                isWin() ? "zip" : "tar.gz"));
    }

    @Override
    Path resolvePath(Path driverDir) {
        return driverDir.resolve(isWin() ? FILE_BASE + ".exe" : FILE_BASE);
    }

    @Override
    void unpackFile(Path download, Path driverPath) throws IOException {
        LOGGER.info("extracting driver from " + download + "  to " + driverPath);

        byte[] buffer = new byte[1024];
        try (TarArchiveInputStream in = new TarArchiveInputStream(new GZIPInputStream(new FileInputStream(download.toFile())))) {
            TarArchiveEntry entry = in.getNextTarEntry();

            LOGGER.info("extracting " + entry.getName());

            try (FileOutputStream out = new FileOutputStream(driverPath.toFile())) {
                int len;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            }
        }

    }
}
