package swip.ch09unicorns.tooltips;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
                WebElement tipElement = driver.findElement(By.className("tooltip-inner"));
                new WebDriverWait(driver, 1)
                        .until((WebDriver webDriver) -> !tipElement.getText().isEmpty());
                return tipElement.getText();
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
