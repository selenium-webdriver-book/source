package swip.ch08unicorns.fileupload;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import swip.ch07managingwebdriver.injecting.Config;
import swip.ch07managingwebdriver.injecting.WebDriverRunner;

import javax.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
@Config(exclude = {"browserName=safari", "browserName=phantomjs", "browserName=chrome"})
public class FileUploadIT {

    @Inject
    private WebDriver driver;
    private Path tempFile;

    @Before
    public void setUp() throws Exception {
        tempFile = Files.createTempFile(Paths.get("."), "tmp", ".txt");
    }

    @After
    public void tearDown() throws Exception {
        Files.delete(tempFile);
    }

    @Test
    public void uploadFile() throws Exception {
        driver.get("/file-upload.html");

        if (driver instanceof RemoteWebDriver) { // if the driver is remote, we need to tell it how to find the file
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        }

        driver
                .findElement(By.name("file"))
                .sendKeys(tempFile.toFile().getCanonicalPath()); // we can just send the location of the file, you should use the canonical path to make sure the driver works

        driver.findElement(By.cssSelector("input[type='submit']"))
                .click();

        assertEquals("Thank You!", driver.findElement(By.tagName("h1")).getText());

    }
}
