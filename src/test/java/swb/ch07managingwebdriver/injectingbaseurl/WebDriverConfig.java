package swb.ch07managingwebdriver.injectingbaseurl;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import swb.ch11drivers.ChromeDriverBinarySupplier;
import swb.ch11drivers.FirefoxDriverBinarySupplier;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

@Configuration
public class WebDriverConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DesiredCapabilities desiredCapabilities(
            @Value("${webdriver.capabilities.browserName:chrome}") String browserName
    ) {
        return new DesiredCapabilities(browserName, "", Platform.ANY);
    }

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver(DesiredCapabilities desiredCapabilities) throws IOException {
        switch (desiredCapabilities.getBrowserName()) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", String.valueOf(new ChromeDriverBinarySupplier().get(Paths.get("target"))));
                return new ChromeDriver(desiredCapabilities);
            case BrowserType.FIREFOX:
                System.setProperty("webdriver.gecko.driver", String.valueOf(new FirefoxDriverBinarySupplier().get(Paths.get("target"))));
                return new FirefoxDriver(desiredCapabilities);
            case BrowserType.PHANTOMJS:
                return new PhantomJSDriver(desiredCapabilities);
            default:
                throw new IllegalStateException("unknown browser " + desiredCapabilities.getBrowserName());
        }
    }

    @Bean
    public URI baseUrl(@Value("${webdriver.baseUrl:http://localhost:8080}") URI value) {
        return value;
    }
}
