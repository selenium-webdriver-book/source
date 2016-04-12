package swip.ch12wrapping.v2;

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
class DelegatingWebDriver
    implements WebDriver, JavascriptExecutor, HasInputDevices,
    HasCapabilities, TakesScreenshot,
    FindsById, FindsByClassName, FindsByLinkText, FindsByName,
    FindsByCssSelector, FindsByTagName, FindsByXPath {

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
        driver.switchTo().window(driver.getWindowHandle());
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

    @Deprecated
    @Override
    public WebElement findElementByClassName(String using) {
        return ((FindsByClassName) driver).findElementByClassName(using);
    }

    @Deprecated
    @Override
    public List<WebElement> findElementsByClassName(String using) {
        return ((FindsByClassName) driver).findElementsByClassName(using);
    }

    @Deprecated
    @Override
    public WebElement findElementByCssSelector(String using) {
        return ((FindsByCssSelector) driver).findElementByCssSelector(using);
    }

    @Deprecated
    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        return ((FindsByCssSelector) driver).findElementsByCssSelector(using);
    }

    @Deprecated
    @Override
    public WebElement findElementById(String using) {
        return ((FindsById) driver).findElementById(using);
    }

    @Deprecated
    @Override
    public List<WebElement> findElementsById(String using) {
        return ((FindsById) driver).findElementsById(using);
    }

    @Deprecated
    @Override
    public WebElement findElementByLinkText(String using) {
        return ((FindsByLinkText) driver).findElementByLinkText(using);
    }

    @Deprecated
    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        return ((FindsByLinkText) driver).findElementsByLinkText(using);
    }

    @Deprecated
    @Override
    public WebElement findElementByPartialLinkText(String using) {
        return ((FindsByLinkText) driver).findElementByPartialLinkText(using);
    }

    @Deprecated
    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        return ((FindsByLinkText) driver).findElementsByPartialLinkText(using);
    }

    @Deprecated
    @Override
    public WebElement findElementByName(String using) {
        return ((FindsByName) driver).findElementByName(using);
    }

    @Deprecated
    @Override
    public List<WebElement> findElementsByName(String using) {
        return ((FindsByName) driver).findElementsByName(using);
    }

    @Deprecated
    @Override
    public WebElement findElementByTagName(String using) {
        return ((FindsByTagName) driver).findElementByTagName(using);
    }

    @Deprecated
    @Override
    public List<WebElement> findElementsByTagName(String using) {
        return ((FindsByTagName) driver).findElementsByTagName(using);
    }

    @Deprecated
    @Override
    public WebElement findElementByXPath(String using) {
        return ((FindsByXPath) driver).findElementByXPath(using);
    }

    @Deprecated
    @Override
    public List<WebElement> findElementsByXPath(String using) {
        return ((FindsByXPath) driver).findElementsByXPath(using);
    }

    @Override
    public Capabilities getCapabilities() {
        return ((HasCapabilities) driver).getCapabilities();
    }
}
