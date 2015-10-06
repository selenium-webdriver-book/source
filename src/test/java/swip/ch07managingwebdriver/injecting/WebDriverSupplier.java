package swip.ch07managingwebdriver.injecting;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swip.ch10drivers.ChromeDriverBinarySupplier;
import swip.ch10drivers.WebDriverBinarySupplier;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static swip.ch11decorating.baseurl.BaseUrlDecorator.baseUrlDriver;

public class WebDriverSupplier {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverSupplier.class);

    static {
        System.setProperty("webdriver.chrome.driver", "target/chromedriver");
    }

    private final Map<DesiredCapabilities, WebDriver> cache = new HashMap<>();
    private final Map<String, WebDriverBinarySupplier> binarySuppliers = ImmutableMap.of(
            BrowserType.CHROME, new ChromeDriverBinarySupplier()
    );

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

    private WebDriver getLocalDriver(DesiredCapabilities desiredCapabilities) {
        acquireBinary(desiredCapabilities.getBrowserName());
        switch (desiredCapabilities.getBrowserName()) {
            case BrowserType.SAFARI:
                return new SafariDriver(desiredCapabilities);
            case BrowserType.CHROME:
                return new ChromeDriver(desiredCapabilities);
            case BrowserType.FIREFOX:
                return new FirefoxDriver(desiredCapabilities);
            case BrowserType.HTMLUNIT:
                return new HtmlUnitDriver(desiredCapabilities);
            case BrowserType.PHANTOMJS:
                return new PhantomJSDriver(desiredCapabilities);
            case BrowserType.IPAD:
            case BrowserType.IPHONE:
                return augmentedRemoteWebDriver(desiredCapabilities, 5555);
            default: // # assume we're running a local driver
                return augmentedRemoteWebDriver(desiredCapabilities, 4444);
        }
    }

    private void acquireBinary(String browserName) {
        if (binarySuppliers.containsKey(browserName)) {
            try {
                binarySuppliers.get(browserName).get(Paths.get("target"));
            } catch (IOException e) {
                throw new RuntimeException("failed acquire binary", e);
            }
        }
    }

    public WebDriver get(DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        if (!cache.containsKey(desiredCapabilities)) {

            EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(baseUrlDriver(Drivers.driverWithAddedShutdownHook(getDriver(desiredCapabilities))));

            eventFiringWebDriver.register(new AbstractWebDriverEventListener() {
                @Override
                public void afterNavigateTo(String url, WebDriver driver) {
                    LOGGER.info("opened " + driver.getCurrentUrl());
                }
            });

            cache.put(desiredCapabilities, eventFiringWebDriver);
        }

        try {
            return Drivers.cleaned(cache.get(desiredCapabilities));
        } catch (SessionNotFoundException e) {
            LOGGER.warn("unable to connect to driver, removing from cache: " + e.getMessage());
            cache.remove(desiredCapabilities);
            return get(desiredCapabilities);
        }
    }

    private WebDriver getDriver(DesiredCapabilities desiredCapabilities) {
        if (Boolean.getBoolean("webdriver.remote")) {
            if (desiredCapabilities.getBrowserName().equals(BrowserType.HTMLUNIT)) {
                return new HtmlUnitDriver(desiredCapabilities);
            }
            return augmentedRemoteDriver(System.getProperty("webdriver.remote.url", "http://localhost:4444/wd/hub"), desiredCapabilities);
        } else {
            return getLocalDriver(desiredCapabilities);
        }
    }

}
