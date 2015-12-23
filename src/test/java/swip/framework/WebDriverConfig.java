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
import java.net.URI;
import java.net.URL;
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

    @Bean
    @Scope("prototype")
    public WebDriver chromeDriver(WebDriverCleaner webDriverCleaner, @Qualifier("dirtyChromeDriver") WebDriver dirtyChromeDriver) throws IOException {
        return webDriverCleaner.cleanWebDriver(dirtyChromeDriver);
    }

    @Bean(destroyMethod = "quit")
    @Lazy
    public WebDriver dirtyChromeDriver(WebDriverFactory webDriverFactory) throws IOException {
        return webDriverFactory.webDriver(DesiredCapabilities.chrome());
    }

    @Bean(destroyMethod = "quit")
    @Lazy
    public WebDriver dirtyWebDriver(WebDriverFactory webDriverFactory, DesiredCapabilities desiredCapabilities) throws IOException {
        return webDriverFactory.webDriver(desiredCapabilities);
    }

    @Bean
    public URI baseUrl(@Value("${webdriver.baseUrl:http://localhost:8080}") URI value) {
        return value;
    }
}
