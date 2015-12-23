package swip.ch15pageflow.framework;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import swip.framework.WebDriverConfig;

import java.io.IOException;

@Configuration
@Import(WebDriverConfig.class)
public class BrowserConfig {

    @Bean(destroyMethod = "quit")
    @Scope("prototype")
    public Browser browser(WebDriver driver) {
        return new Browser(driver);
    }

    @Bean(destroyMethod = "quit")
    @Scope("prototype")
    public Browser chrome(@Qualifier("chromeDriver") WebDriver webDriver) throws IOException {
        return new Browser(webDriver);
    }
}
