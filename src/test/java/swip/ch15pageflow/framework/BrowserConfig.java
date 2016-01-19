package swip.ch15pageflow.framework;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import swip.framework.WebDriverConfig;

@Configuration
@Import(WebDriverConfig.class)
public class BrowserConfig {

    @Bean(destroyMethod = "quit")
    @Scope("prototype")
    public Browser browser(WebDriver driver) {
        return new Browser(driver);
    }
}
