package swip.ch15pageflow.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;

import java.util.Set;

public class DelegatingWebDriver extends DelegatingSearchContext<WebDriver> implements WebDriver, HasInputDevices {

    public DelegatingWebDriver(WebDriver delegate) {
       super( delegate);
    }

    @Override
    public void get(String url) {
        delegate.get(url);
        delegate.switchTo().window(delegate.getWindowHandle());
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
    public Options manage() {
        return delegate.manage();
    }

    @Override
    public Keyboard getKeyboard() {
        return ((HasInputDevices) delegate).getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return ((HasInputDevices) delegate).getMouse();
    }
}
