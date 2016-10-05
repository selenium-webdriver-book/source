package swb.ch11drivers;

import java.io.IOException;
import java.nio.file.Path;

public interface WebDriverBinarySupplier {
    /**
     * @param target A directory to place the binary in.
     * @return The name of the binary file.
     */
    Path get(Path target) throws IOException;
}
