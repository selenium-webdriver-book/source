package swip.framework;

import java.io.File;

public interface CapturesScreencast {

    void startScreencastCapture(File file);

    void endScreencastCapture();
}
