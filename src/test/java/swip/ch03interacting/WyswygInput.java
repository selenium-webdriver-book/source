package swip.ch03interacting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WyswygInput {
    private static final By ITALIC_BUTTON_SELECTOR = By.className("mce-i-italic");
    private static final By EDITOR_SELECTOR = By.id("editor_ifr");
    private static final By BODY_SELECTOR = By.tagName("body");
    private final WebElement editorFrame;
    private final WebDriver driver;
    private final WebElement body;

    public WyswygInput(WebDriver driver) {
        this.driver = driver;
        editorFrame = driver.findElement(EDITOR_SELECTOR);
        focusEditor();
        body = driver.findElement(BODY_SELECTOR);
        blurEditor();
    }

    private WebDriver focusEditor() {
        return driver.switchTo().frame(editorFrame);
    }

    public void setText(String text) {
        focusEditor();
        body.clear();
        body.sendKeys(text);
        blurEditor();
    }

    public void appendItalicText(String italicText) {

        driver.findElement(ITALIC_BUTTON_SELECTOR).click();

        focusEditor();
        body.sendKeys(italicText);

        blurEditor();
        driver.findElement(ITALIC_BUTTON_SELECTOR).click();

    }

    private WebDriver blurEditor() {
        return driver.switchTo().defaultContent();
    }
}
