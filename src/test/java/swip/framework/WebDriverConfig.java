package swip.framework;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.AbstractMap;

@Configuration
public class WebDriverConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    public WebDriverFactory webDriverFactory(@Value("${webdriver.remote:false}") boolean remoteDriver,
                                             @Value("${webdriver.remote.url:http://localhost:4444/wd/hub}") URL remoteUrl) {
        return new WebDriverFactory(remoteDriver, remoteUrl);
    }

    @Bean
    public WebDriverCleaner webDriverCleaner() {
        return new WebDriverCleaner();
    }

    @Bean
    public DesiredCapabilities desiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities("firefox", "", Platform.ANY);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        String prefix = "webdriver.capabilities.";

        System.getProperties().entrySet().stream()
                .map(e -> new AbstractMap.SimpleImmutableEntry<>(String.valueOf(e.getKey()), e.getValue()))
                .filter(e -> e.getKey().startsWith(prefix))
                .forEach(e -> capabilities.setCapability(e.getKey().substring(prefix.length()), e.getValue()));

        return capabilities;
    }

    @Bean
    @Primary
    @Scope("prototype")
    public WebDriver webDriver(WebDriverCleaner webDriverCleaner, @Qualifier("dirtyWebDriver") WebDriver driver) {
        return webDriverCleaner.cleanWebDriver(driver);
    }

    @Bean(destroyMethod = "quit")
    @Lazy
    public WebDriver dirtyWebDriver(WebDriverFactory webDriverFactory, DesiredCapabilities desiredCapabilities, URI baseUrl) throws IOException {
        return webDriverFactory.webDriver(desiredCapabilities, baseUrl);
    }

    @Bean
    public URI baseUrl(@Value("${webdriver.baseUrl:http://auto}") URI value) throws UnknownHostException {
        if (value.equals(URI.create("http://auto"))) {
            return URI.create("http://" + InetAddress.getLocalHost().getHostAddress() + ":8080");
        }
        return value;
    }

    @Bean
    public WebElementScreenshotTaker webElementScreenshotTaker() {
        return new WebElementScreenshotTaker();
    }

    @Bean
    public Ocr ocr(@Value("${ocr.url:http://130.211.57.252:8080/ocr-file-upload}") URI url) {
        return new Ocr(url);
    }
}
