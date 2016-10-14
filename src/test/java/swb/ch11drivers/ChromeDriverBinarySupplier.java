package swb.ch11drivers;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Scanner;

public class ChromeDriverBinarySupplier extends AbstractDriverBinarySupplier {

    private static final String BASE_PATH = "http://chromedriver.storage.googleapis.com";
    private static final String FILE_BASE = "chromedriver";
    private static final int ARCH = OS.matches("linux|mac") && OS_ARCH.endsWith("64") ? 64 : 32;

    @Override
    URL createUrl() throws IOException {
        return new URL(BASE_PATH + "/" + lastRelease() + "/" + FILE_BASE + OS + ARCH + ".zip");
    }

    @Override
    Path resolvePath(Path driverDir) {
        return driverDir.resolve(OS.equals("win") ? FILE_BASE + ".exe" : FILE_BASE);
    }

    private String lastRelease() throws IOException {
        URL url = new URL(BASE_PATH + "/LATEST_RELEASE");

        try (Scanner scanner = new Scanner(url.openStream())) {
            return scanner.useDelimiter("\\A").next().trim(); //<9>
        }
    }
}
