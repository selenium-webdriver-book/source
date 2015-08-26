package swip.ch08unicorns.tooltips;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Tooltip {

    public static String tip(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);

        String type = element.getAttribute("data-toggle");

        if (type == null) {
            type = "title";
        }

        switch (type) {
            case "title":
                return element.getAttribute("title");
            case "tooltip":
                // TODO - Safari failure?
                new Actions(driver).moveToElement(element).perform();
                return driver.findElement(By.className("tooltip-inner")).getText();
            case "popover":
                new Actions(driver).click(element).perform();
                return driver
                        .findElement(By.id(element.getAttribute("aria-describedBy")))
                        .findElement(By.className("popover-content"))
                        .getText();
            default:
                throw new AssertionError(type);
        }
    }
}
