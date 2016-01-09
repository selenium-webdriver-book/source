package swip.ch07managingwebdriver.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotTaker extends AbstractTestExecutionListener {
    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        TakesScreenshot takesScreenshot = (TakesScreenshot) testContext.getApplicationContext()
                .getBean(WebDriver.class);
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File dir = new File("target/screenshots");
        if (!dir.exists()) {
            Files.createDirectories(dir.toPath());
        }
        File file = new File(dir,
                testContext.getTestClass().getName() + "_" + testContext.getTestMethod().getName() + ".png");
        FileUtils.deleteQuietly(file);
        FileUtils.moveFile(screenshot, file);
        System.err.println("saved screenshot as " + file);
    }
}