package swip.framework;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import swip.ch10drivers.ChromeDriverBinarySupplier;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static swip.ch11decorating.baseurl.BaseUrlDecorator.baseUrlDriver;

@Configuration
public class WebDriverConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DesiredCapabilities desiredCapabilities(
            @Value("${webdriver.capabilities.browserName:firefox}") String browserName
    ) {
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return capabilities;
    }

    @Bean
    @Primary
    @Scope("prototype")
    public WebDriver cleanWebDriver(WebDriver driver) throws Exception {

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        closeRedundantWindows(driver);

        return driver;
    }

    private void closeRedundantWindows(WebDriver driver) {
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 1) {

            driver.switchTo().defaultContent();

            String topWindowHandle = driver.getWindowHandle();

            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(topWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    driver.close();
                }
            }
        }
    }

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver(
            @Value("${webdriver.remote:false}") boolean remoteDriver,
            @Value("${webdriver.remote.url:http://localhost:4444/wd/hub}") URL remoteUrl,
            DesiredCapabilities desiredCapabilities) throws Exception {

        WebDriver baseDriver = remoteDriver ?
                remoteDriver(remoteUrl, desiredCapabilities) :
                localDriver(desiredCapabilities);

        return baseUrlDriver(baseDriver);
    }

    private WebDriver localDriver(DesiredCapabilities desiredCapabilities) throws IOException {
        switch (desiredCapabilities.getBrowserName()) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", String.valueOf(new ChromeDriverBinarySupplier().get(Paths.get("target"))));
                return new ChromeDriver(desiredCapabilities);
            case BrowserType.FIREFOX:
                return new FirefoxDriver(desiredCapabilities);
            case BrowserType.HTMLUNIT:
                return new HtmlUnitDriver(desiredCapabilities);
            case BrowserType.SAFARI:
                return new SafariDriver(desiredCapabilities);
            default:
                throw new IllegalStateException("unknown browser " + desiredCapabilities.getBrowserName());
        }
    }

    private WebDriver remoteDriver(URL remoteUrl, DesiredCapabilities desiredCapabilities) {
        return new Augmenter().augment(new RemoteWebDriver(remoteUrl, desiredCapabilities));
    }

    @Bean
    public URI baseUrl(@Value("${webdriver.baseUrl:http://localhost:8080}") URI value) {
        return value;
    }
}
