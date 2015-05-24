package swip.ch07managingwebdriver;

import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

class TakeScreenshotStatement extends Statement { // <1> Implement `Statement` to be part of a JUnit test.
    private static final Logger LOGGER
            = Logger.getLogger(TakeScreenshotStatement.class.getName());
    private final WebDriver driver;
    private final String testName;
    private final Statement statement; // <2> A statement is part of a chain-of-responsibility, so we need to know the statement to evaluate.

    TakeScreenshotStatement(WebDriver driver, String testName, Statement statement) {
        this.driver = driver;
        this.testName = testName;
        this.statement = statement;
    }

    @Override
    public void evaluate() throws Throwable {
        try {
            statement.evaluate(); // <3> Invoke the test
        } finally {
            screenshot(new File("target/wd", testName + ".png")); // <4> Take the screenshot at the end of the test.
        }
    }

    private void screenshot(File saveAs) throws IOException {
        if (Boolean.getBoolean("webdriver.screenshot")
                && driver instanceof TakesScreenshot) { // <5> Take a screenshot and save it somewhere.
            File file = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE)
                    .getAbsoluteFile();
            if (saveAs.exists()) {
                Files.delete(saveAs.toPath());
            }
            Files.createDirectories(saveAs.getParentFile().toPath());
            Files.move(file.toPath(), saveAs.toPath());
            LOGGER.info("saved " + saveAs);
        }
    }
}
