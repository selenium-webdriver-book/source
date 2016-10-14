package swb.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import swb.ch11drivers.ChromeDriverBinarySupplier;
import swb.ch11drivers.FirefoxDriverBinarySupplier;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;

public class WebDriverFactory {

    private final boolean remoteDriver;
    private final URL remoteUrl;

    WebDriverFactory(boolean remoteDriver, URL remoteUrl) {
        this.remoteDriver = remoteDriver;
        this.remoteUrl = remoteUrl;
    }

    WebDriver webDriver(DesiredCapabilities desiredCapabilities, URI baseUrl) throws IOException {

        WebDriver baseDriver = remoteDriver ?
                remoteDriver(remoteUrl, desiredCapabilities) :
                localDriver(desiredCapabilities);

        return new BaseUrlDriver(baseDriver, baseUrl);
    }

    private WebDriver localDriver(DesiredCapabilities desiredCapabilities) throws IOException {
        switch (desiredCapabilities.getBrowserName()) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", String.valueOf(new ChromeDriverBinarySupplier().get(Paths.get("target"))));
                return new ChromeDriver(desiredCapabilities);
            case BrowserType.FIREFOX:
                System.setProperty("webdriver.gecko.driver", String.valueOf(new FirefoxDriverBinarySupplier().get(Paths.get("target"))));
                return new FirefoxDriver(desiredCapabilities);
            case BrowserType.HTMLUNIT:
                return new HtmlUnitDriver(desiredCapabilities);
            case BrowserType.SAFARI:
                return new SafariDriver(desiredCapabilities);
            case BrowserType.IE:
                return new InternetExplorerDriver(desiredCapabilities);
            case BrowserType.PHANTOMJS:
                return new PhantomJSDriver(desiredCapabilities);
            default:
                throw new IllegalStateException("unknown browser " + desiredCapabilities.getBrowserName());
        }
    }

    private WebDriver remoteDriver(URL remoteUrl, DesiredCapabilities desiredCapabilities) {
        return new Augmenter().augment(new RemoteWebDriver(remoteUrl, desiredCapabilities));
    }
}
