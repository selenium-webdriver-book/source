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
class DelegatingWebDriver extends DelegatingSearchContext<WebDriver> implements WebDriver, JavascriptExecutor, FindsById, FindsByClassName, FindsByLinkText, FindsByName, FindsByCssSelector, FindsByTagName, FindsByXPath, HasInputDevices, HasCapabilities, TakesScreenshot {

    DelegatingWebDriver(WebDriver driver) {
        super(driver);
    }

    @Override
    public Options manage() {
        return delegate.manage();
    }

    @Override
    public void get(String url) {
        delegate.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return delegate.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return delegate.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return delegate.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return delegate.findElement(by);
    }

    @Override
    public String getPageSource() {
        return delegate.getPageSource();
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public void quit() {
        delegate.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return delegate.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return delegate.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return delegate.switchTo();
    }

    @Override
    public Navigation navigate() {
        return delegate.navigate();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) delegate).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return ((JavascriptExecutor) delegate).executeAsyncScript(script, args);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot) delegate).getScreenshotAs(target);
    }

    @Override
    public Keyboard getKeyboard() {
        return ((HasInputDevices) delegate).getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return ((HasInputDevices) delegate).getMouse();
    }

    @Override
    public WebElement findElementByClassName(String using) {
        return ((FindsByClassName) delegate).findElementByClassName(using);
    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
        return ((FindsByClassName) delegate).findElementsByClassName(using);
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        return ((FindsByCssSelector) delegate).findElementByCssSelector(using);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        return ((FindsByCssSelector) delegate).findElementsByCssSelector(using);
    }

    @Override
    public WebElement findElementById(String using) {
        return ((FindsById) delegate).findElementById(using);
    }

    @Override
    public List<WebElement> findElementsById(String using) {
        return ((FindsById) delegate).findElementsById(using);
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        return ((FindsByLinkText) delegate).findElementByLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        return ((FindsByLinkText) delegate).findElementsByLinkText(using);
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        return ((FindsByLinkText) delegate).findElementByPartialLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        return ((FindsByLinkText) delegate).findElementsByPartialLinkText(using);
    }

    @Override
    public WebElement findElementByName(String using) {
        return ((FindsByName) delegate).findElementByName(using);
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
        return ((FindsByName) delegate).findElementsByName(using);
    }

    @Override
    public WebElement findElementByTagName(String using) {
        return ((FindsByTagName) delegate).findElementByTagName(using);
    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
        return ((FindsByTagName) delegate).findElementsByTagName(using);
    }

    @Override
    public WebElement findElementByXPath(String using) {
        return ((FindsByXPath) delegate).findElementByXPath(using);
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        return ((FindsByXPath) delegate).findElementsByXPath(using);
    }

    @Override
    public Capabilities getCapabilities() {
        return ((HasCapabilities) delegate).getCapabilities();
    }
}
