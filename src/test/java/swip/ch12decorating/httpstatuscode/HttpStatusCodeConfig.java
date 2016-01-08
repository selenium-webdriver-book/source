package swip.ch12decorating.httpstatuscode;

import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import swip.framework.WebDriverConfig;

import java.net.URI;

@Configuration
@Import(WebDriverConfig.class)
public class HttpStatusCodeConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public ProxyServer proxyServer() {
        return new ProxyServer(9091);
    }

    @Primary
    @Bean
    public DesiredCapabilities proxyingDesiredCapabilities(ProxyServer proxyServer,
                                                           @Qualifier("desiredCapabilities") DesiredCapabilities capabilities) {
        capabilities.setCapability(CapabilityType.PROXY, proxyServer.seleniumProxy());
        return capabilities;
    }

    @Bean
    public HttpStatusCodeSupplier httpStatusCodeSupplier(ProxyServer server, URI baseUrl) {
        return new HttpStatusCodeSupplier(server, baseUrl);
    }
}
