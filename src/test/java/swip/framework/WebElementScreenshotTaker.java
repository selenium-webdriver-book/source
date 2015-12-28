package swip.framework;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WebElementScreenshotTaker {

    public void takeScreenshot(WebDriver driver, WebElement element, File destFile) throws IOException {
        takeScreenshot((TakesScreenshot) driver, element, destFile);
    }

    public void takeScreenshot(TakesScreenshot takesScreenshot, WebElement element, File destFile) throws IOException {
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);

        BufferedImage fullImg = ImageIO.read(screenshot);
        Point point = element.getLocation();

        BufferedImage cropped = fullImg.getSubimage(point.getX(), point.getY(),
                element.getSize().getWidth(), element.getSize().getHeight());

        String formatName = destFile.getName().replaceFirst("^.*\\.(.*)$", "$1");
        ImageIO.write(cropped, formatName, destFile);
    }
}
