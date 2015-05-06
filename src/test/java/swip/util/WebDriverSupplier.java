package swip.util;

import org.openqa.selenium.Alert;
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
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static swip.util.Drivers.baseUrlDriver;
import static swip.util.Drivers.driverWithAddedShutdownHook;

public class WebDriverSupplier {
    private static final Logger LOGGER = Logger.getLogger(WebDriverSupplier.class.getName());
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

        WebDriver driver = cache.get(desiredCapabilities);

        clean(driver);

        return driver;
    }

    private void clean(WebDriver driver) {
        // TODO - update managing the driver with clean-up activities
        try {
            Alert alert = ExpectedConditions.alertIsPresent().apply(driver);
            if (alert != null) {
                alert.dismiss();
            }
        } catch (UnsupportedOperationException ignored) {
            // not all browsers support this
            LOGGER.info("failed to close alert " + driver + " unsupported operation");
        }
        // must delete after alert closed
        driver.manage().deleteAllCookies();
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
