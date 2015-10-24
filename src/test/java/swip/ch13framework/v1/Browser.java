package swip.ch13framework.v1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Browser {
    private final WebDriver driver; // <1>

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public Element findElement(By by) {
        return new Element(driver.findElement(by));
    } // <3>

    public void get(String url) {
        driver.get(url);
    }
}
