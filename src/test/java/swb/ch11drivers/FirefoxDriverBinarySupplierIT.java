package swb.ch11drivers;

import org.junit.Test;

import java.nio.file.Paths;

public class FirefoxDriverBinarySupplierIT {

    @Test
    public void acquireFirefox() throws Exception {
        new FirefoxDriverBinarySupplier().get(Paths.get("target"));
    }

}