package swip.ch11decorating.httpstatuscode;

import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpStatusCodeConfig {

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
    public WebDriver webDriver(DesiredCapabilities desiredCapabilities, ProxyServer server) {
        return HttpStatusCodeDecorator.httpStatusCodeDriver(new FirefoxDriver(desiredCapabilities), server);
    }
}
