package swb.framework;

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

    public File takeScreenshot(WebDriver driver, WebElement element) throws IOException {
        return takeScreenshot((TakesScreenshot) driver, element);
    }

    public File takeScreenshot(TakesScreenshot takesScreenshot, WebElement element) throws IOException {
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);

        BufferedImage fullImg = ImageIO.read(screenshot);
        Point point = element.getLocation();

        BufferedImage cropped = fullImg.getSubimage(point.getX(), point.getY(),
                element.getSize().getWidth(), element.getSize().getHeight());

        String formatName = screenshot.getName().replaceFirst("^.*\\.(.*)$", "$1");
        ImageIO.write(cropped, formatName, screenshot);

        return screenshot;
    }
}
