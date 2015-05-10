package swip.ch08unicorns.tooltips;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.function.Function;

public class Tooltip {

    public static String tip(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);

        boolean popover = element.getAttribute("data-toggle").equals("popover");

        Function<Actions, Actions> action =
                popover ? a -> a.click(element) : a -> a.moveToElement(element); // change activation strategy if we are a pop-over or not

        action.apply(new Actions(driver)).perform(); // apply the stratagy to an action chain

        try {
            return popover
                    ? driver
                    .findElement(By.id(element.getAttribute("aria-describedBy"))) // locate pop-overs
                    .findElement(By.className("popover-content"))
                    .getText()
                    : driver.findElement(By.className("tooltip-inner")).getText(); // locate tooltips
        } catch (NoSuchElementException e1) {
            return element.getAttribute("title");
        }
    }
}
