package swip.ch07managingwebdriver.injecting;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class WebDriverConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DesiredCapabilities desiredCapabilities(
            @Value("${webdriver.capabilities.browserName:firefox}") String browserName) {
        return new DesiredCapabilities(browserName, "", Platform.ANY);
    }

    @Bean
    public WebDriverSupplier webDriverSupplier() {
        return new WebDriverSupplier();
    }

    @Bean
    public WebDriver webDriver(WebDriverSupplier webDriverSupplier,
                               DesiredCapabilities desiredCapabilities) {
        return webDriverSupplier.get(desiredCapabilities);
    }

    @Bean
    public String baseUrl(@Value("${webdriver.baseUrl:http://localhost:8080}") String value) {
        return value;
    }
}
