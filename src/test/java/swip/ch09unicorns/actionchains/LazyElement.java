package swip.ch09unicorns.actionchains;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

import java.util.List;

@SuppressWarnings("Convert2Lambda")
public class LazyElement implements WebElement, Locatable {
    private final SearchContext searchContext;
    private final By locator;

    public LazyElement(SearchContext searchContext, By locator) {
        this.searchContext = searchContext;
        this.locator = locator;
    }

    private WebElement get() {
        return searchContext.findElement(locator);
    }

    @Override
    public void click() {
        get().click();
    }

    @Override
    public void submit() {
        get().submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        get().sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        get().clear();
    }

    @Override
    public String getTagName() {
        return get().getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return get().getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return get().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return get().isEnabled();
    }

    @Override
    public String getText() {
        return get().getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return get().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return get().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return get().isDisplayed();
    }

    @Override
    public Point getLocation() {
        return get().getLocation();
    }

    @Override
    public Dimension getSize() {
        return get().getSize();
    }


    @Override
    public Rectangle getRect() {
        return get().getRect();
    }


    @Override
    public String getCssValue(String propertyName) {
        return get().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return get().getScreenshotAs(target);
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable)get()).getCoordinates();
    }
}
