package swip.ch08unicorns.windows;

import org.openqa.selenium.WebDriver;

import java.util.function.BiFunction;

public class WindowHandler {
    private final WebDriver driver;
    private Runnable openingBlock; // the block of code needed to open the window
    private BiFunction<String, String, Boolean> identifier
            = (windowHandle, originalWindowHandle) -> !windowHandle.equals(originalWindowHandle); // a function to identify the window

    public WindowHandler(WebDriver driver) {
        this.driver = driver;
    }

    public WindowHandler identifiedBy(BiFunction<String, String, Boolean> identifier) {
        this.identifier = identifier;
        return this;
    }

    public WindowHandler openWith(Runnable openingBlock) {
        this.openingBlock = openingBlock;
        return this;
    }

    public void then(Runnable operationBlock) {
        String originalWindowHandle = driver.getWindowHandle();
        try {
            openingBlock.run();
            String identifiedHandle = driver
                    .getWindowHandles()
                    .stream()
                    .filter(windowHandle -> identifier.apply(windowHandle, originalWindowHandle))
                    .findFirst()
                    .get();

            driver.switchTo().window(identifiedHandle);
            try {
                operationBlock.run();
            } finally {
                if (driver.getWindowHandle().equals(identifiedHandle)) { // only close if it has not already been closed
                    driver.close();
                }
            }
        } finally {
            driver.switchTo().window(originalWindowHandle);
        }
    }
}
