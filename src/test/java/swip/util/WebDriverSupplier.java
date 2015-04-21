package swip.util;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static swip.util.Drivers.baseUrlDriver;
import static swip.util.Drivers.driverWithAddedShutdownHook;

public class WebDriverSupplier {
    private final Map<DesiredCapabilities, WebDriver> cache = new HashMap<>();

    static {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
    }

    private static WebDriver newLocalDriver(DesiredCapabilities desiredCapabilities) {
        switch (desiredCapabilities.getBrowserName()) {
            case BrowserType.SAFARI:
                return new SafariDriver(desiredCapabilities);
            case BrowserType.CHROME:
                return new ChromeDriver(desiredCapabilities);
            case BrowserType.FIREFOX:
                return new FirefoxDriver(desiredCapabilities);
            case BrowserType.HTMLUNIT:
                return new HtmlUnitDriver(desiredCapabilities);
            case BrowserType.IPAD:
            case BrowserType.IPHONE:
                return augmentedRemoteWebDriver(desiredCapabilities, 5555);
            default: // # assume we're running a local driver
                return augmentedRemoteWebDriver(desiredCapabilities, 4444);
        }
    }

    private static WebDriver augmentedRemoteWebDriver(DesiredCapabilities desiredCapabilities, int port) {
        return augmentedRemoteDriver("http://localhost:" + port + "/wd/hub", desiredCapabilities);
    }

    private static WebDriver augmentedRemoteDriver(String url, DesiredCapabilities desiredCapabilities) {
        try {
            return new Augmenter().augment(new RemoteWebDriver(new URL(url), desiredCapabilities));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public WebDriver get() {
        return get(new DesiredCapabilities(BrowserType.FIREFOX, "", Platform.ANY));
    }

    public WebDriver get(DesiredCapabilities desiredCapabilities) {

        if (!cache.containsKey(desiredCapabilities)) {
            cache.put(desiredCapabilities, baseUrlDriver(driverWithAddedShutdownHook(newDriver(desiredCapabilities))));
        }

        return cache.get(desiredCapabilities);
    }

    private WebDriver newDriver(DesiredCapabilities desiredCapabilities) {
        if (Boolean.getBoolean("webdriver.remote")) {
            if (desiredCapabilities.getBrowserName().equals(BrowserType.HTMLUNIT)) {
                return new HtmlUnitDriver(desiredCapabilities);
            }
            return augmentedRemoteDriver(System.getProperty("webdriver.remote.url"), desiredCapabilities);
        } else {
            return newLocalDriver(desiredCapabilities);
        }
    }
}
