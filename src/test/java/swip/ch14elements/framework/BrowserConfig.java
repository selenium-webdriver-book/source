package swip.ch14elements.framework;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swip.framework.WebDriverConfig;

@Configuration
@Import(WebDriverConfig.class)
public class BrowserConfig {

    @Bean
    public Browser browser(WebDriver driver) {
        return new Browser(driver);
    }
}
