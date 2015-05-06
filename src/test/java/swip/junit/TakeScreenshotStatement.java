package swip.junit;

import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

class TakeScreenshotStatement extends Statement {
    private static final Logger LOGGER = Logger.getLogger(TakeScreenshotStatement.class.getName());
    private final WebDriver driver;
    private final String testName;
    private final Statement statement;

    TakeScreenshotStatement(WebDriver driver, String testName, Statement statement) {
        this.driver = driver;
        this.testName = testName;
        this.statement = statement;
    }

    @Override
    public void evaluate() throws Throwable {
        try {
            statement.evaluate();
        } finally {
            screenshot(new File("target/wd", testName + ".png"));
        }
    }

    private void screenshot(File saveAs) throws IOException {
        Files.createDirectories(saveAs.getParentFile().toPath());
        if (Boolean.getBoolean("webdriver.screenshot") && driver instanceof TakesScreenshot) {
            File file = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE)
                    .getAbsoluteFile();
            if (saveAs.exists()) {
                Files.delete(saveAs.toPath());
            }
            Files.move(file.toPath(), saveAs.toPath());
            LOGGER.info("saved " + saveAs);
        }
    }
}
