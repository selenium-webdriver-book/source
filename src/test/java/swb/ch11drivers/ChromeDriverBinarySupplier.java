package swb.ch11drivers;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Scanner;

public class ChromeDriverBinarySupplier extends AbstractDriverBinarySupplier {

    private static final String BASE_PATH = "http://chromedriver.storage.googleapis.com";
    private static final String FILE_BASE = "chromedriver";

    @Override
    URL createUrl() throws IOException {
        int arch = !isWin() && is64Bit() ? 64 : 32;
        return new URL(String.format("%s/%s/%s_%s%d.zip",
                BASE_PATH,
                lastRelease(),
                FILE_BASE,
                isWin() ? "win" : isLinux() ? "linux" : "mac",
                arch));
    }

    @Override
    Path resolvePath(Path driverDir) {
        return driverDir.resolve(isWin() ? FILE_BASE + ".exe" : FILE_BASE);
    }

    private String lastRelease() throws IOException {
        URL url = new URL(BASE_PATH + "/LATEST_RELEASE");

        try (Scanner scanner = new Scanner(url.openStream())) {
            return scanner.useDelimiter("\\A").next().trim(); //<9>
        }
    }
}
