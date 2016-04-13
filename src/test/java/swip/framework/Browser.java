package swip.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.function.Supplier;

public class Browser extends DelegatingWebDriver implements FormElements {

    public Browser(WebDriver driver) {
        super(driver);
    }

    public void doubleClick(Supplier<By> by) {

        Element element = untilFound(by);
        new Actions(this) // #C create actions from the driver
            .doubleClick(element) // #D add a double-click to the sequence
            .perform(); // #E perform the sequence
    }
}