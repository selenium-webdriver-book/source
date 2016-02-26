package swip.framework;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.*;

import java.util.List;
import java.util.Set;

/**
 * Implements all the same interfaces as {@link org.openqa.selenium.remote.RemoteWebDriver}.
 *
 * @see org.openqa.selenium.remote.RemoteWebDriver
 */
class DelegatingWebDriver implements WebDriver, JavascriptExecutor, FindsById, FindsByClassName, FindsByLinkText, FindsByName, FindsByCssSelector, FindsByTagName, FindsByXPath, HasInputDevices, HasCapabilities, TakesScreenshot {
    private final WebDriver driver;

    DelegatingWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeAsyncScript(script, args);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot) driver).getScreenshotAs(target);
    }

    @Override
    public Keyboard getKeyboard() {
        return ((HasInputDevices) driver).getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return ((HasInputDevices) driver).getMouse();
    }

    @Override
    public WebElement findElementByClassName(String using) {
        return ((FindsByClassName) driver).findElementByClassName(using);
    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
        return ((FindsByClassName) driver).findElementsByClassName(using);
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        return ((FindsByCssSelector) driver).findElementByCssSelector(using);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        return ((FindsByCssSelector) driver).findElementsByCssSelector(using);
    }

    @Override
    public WebElement findElementById(String using) {
        return ((FindsById) driver).findElementById(using);
    }

    @Override
    public List<WebElement> findElementsById(String using) {
        return ((FindsById) driver).findElementsById(using);
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        return ((FindsByLinkText) driver).findElementByLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        return ((FindsByLinkText) driver).findElementsByLinkText(using);
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        return ((FindsByLinkText) driver).findElementByPartialLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        return ((FindsByLinkText) driver).findElementsByPartialLinkText(using);
    }

    @Override
    public WebElement findElementByName(String using) {
        return ((FindsByName) driver).findElementByName(using);
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
        return ((FindsByName) driver).findElementsByName(using);
    }

    @Override
    public WebElement findElementByTagName(String using) {
        return ((FindsByTagName) driver).findElementByTagName(using);
    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
        return ((FindsByTagName) driver).findElementsByTagName(using);
    }

    @Override
    public WebElement findElementByXPath(String using) {
        return ((FindsByXPath) driver).findElementByXPath(using);
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        return ((FindsByXPath) driver).findElementsByXPath(using);
    }

    @Override
    public Capabilities getCapabilities() {
        return ((HasCapabilities) driver).getCapabilities();
    }
}
