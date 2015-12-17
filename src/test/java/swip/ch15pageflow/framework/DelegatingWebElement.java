package swip.ch15pageflow.framework;

import org.openqa.selenium.*;

import java.util.List;
import java.util.function.Function;

public class DelegatingWebElement implements WebElement {
    private WebElement delegate;
    private ExplicitWait searchContext;
    private Function<ExplicitWait, Element> finder;

    public DelegatingWebElement(WebElement delegate) {
        this.delegate = delegate;
    }

    @Override
    public void click() {
        delegate.click();
    }

    public void click2() {
        try {
            delegate.click();
        } catch (StaleElementReferenceException e) {          //<2>
            this.delegate = finder.apply(searchContext);
            click2();
        }
    }

    @Override
    public void submit() {
        delegate.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        delegate.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public String getTagName() {
        return delegate.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return delegate.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return delegate.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return delegate.isEnabled();
    }

    @Override
    public String getText() {
        return delegate.getText();
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
    public boolean isDisplayed() {
        return delegate.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return delegate.getLocation();
    }

    @Override
    public Dimension getSize() {
        return delegate.getSize();
    }

    @Override
    public String getCssValue(String propertyName) {
        return delegate.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return delegate.getScreenshotAs(outputType);
    }

    public void setSerachContext(ExplicitWait searchContext) {
        this.searchContext = searchContext;
    }

    public void setBy(Function<ExplicitWait, Element> finder) {
        this.finder = finder;
    }
}
