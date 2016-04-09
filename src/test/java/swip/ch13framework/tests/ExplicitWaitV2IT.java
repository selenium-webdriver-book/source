package swip.ch13framework.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import swip.ch13framework.v2.Browser;
import swip.ch13framework.v2.Element;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.linkText;

@RunWith(WebDriverRunner.class)
public class ExplicitWaitV2IT {

    private Browser browser;

    @Inject private void setWebDriver(WebDriver driver) {
        this.browser = new Browser(driver, "http://localhost:8080");
    }

    @Test(expected = NoSuchElementException.class)
    public void expectNoSuchElementException() throws Exception {
        browser.get("/location-chooser.html");
        browser.findElement(linkText("change location")).click();
        Element tabMenu = browser.findElement(By.id("location"));
        tabMenu.findElement(linkText("CANADA")).click();
        tabMenu.findElement(linkText("Ontario")).click();
        assertEquals("Ontario", browser
                .findElement(By.cssSelector(".tools-location strong"))
                .getText());
    }
}
