package swip.ch12decorating.httpstatuscode;

import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.net.URI;

@Configuration
public class HttpStatusCodeConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public ProxyServer proxyServer() {
        return new ProxyServer(9091);
    }

    @Bean
    public DesiredCapabilities desiredCapabilities(ProxyServer proxyServer) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxyServer.seleniumProxy());
        return capabilities;
    }

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver(DesiredCapabilities desiredCapabilities, ProxyServer server, URI baseUrl) {
        return HttpStatusCodeDecorator.httpStatusCodeDriver(new FirefoxDriver(desiredCapabilities), server, baseUrl);
    }

    @Bean
    public URI baseUrl(@Value("${webdriver.baseUrl:http://127.0.0.1:8080}") URI value) {
        return value;
    }
}
