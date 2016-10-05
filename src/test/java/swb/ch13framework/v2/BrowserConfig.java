package swb.ch13framework.v2;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import swb.framework.WebDriverConfig;

@Configuration
@Import(WebDriverConfig.class)
public class BrowserConfig {

    @Bean
    public Browser browser(WebDriver driver) {
        return new Browser(driver);
    }
}
