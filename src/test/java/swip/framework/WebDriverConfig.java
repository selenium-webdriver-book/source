package swip.framework;

import org.littleshoot.proxy.HttpFiltersSource;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
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
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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

    private static int freePort() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            return serverSocket.getLocalPort();
        }
    }

    @Bean(destroyMethod = "stop")
    public HttpProxyServer proxyServer(HttpFiltersSource httpFiltersSource) throws IOException, InterruptedException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 0);
        return DefaultHttpProxyServer.bootstrap()
                .withNetworkInterface(inetSocketAddress)
                .withFiltersSource(httpFiltersSource)
                .withPort(freePort())
                .start();
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
    public DesiredCapabilities desiredCapabilities(HttpProxyServer proxyServer) throws UnknownHostException {
        DesiredCapabilities capabilities = new DesiredCapabilities("firefox", "", Platform.ANY);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        String httpProxy = proxyServer.getListenAddress().toString().substring(1); // remove a leading "/"
        Proxy proxy = new Proxy().setHttpProxy(httpProxy).setSslProxy(httpProxy)
                .setFtpProxy(httpProxy).setSocksProxy(httpProxy);
        capabilities.setCapability(CapabilityType.PROXY, proxy);

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
    public WebDriver webDriver(WebDriverCleaner webDriverCleaner, @Qualifier("dirtyWebDriver") WebDriver driver,
                               ScreencastCapture screencastCapture) {
        return screencastCapture.createCapturingWebDriver(webDriverCleaner.cleanWebDriver(driver));
    }

    @Bean(destroyMethod = "quit")
    @Lazy
    public WebDriver dirtyWebDriver(WebDriverFactory webDriverFactory, DesiredCapabilities desiredCapabilities, URI baseUrl) throws IOException {
        return webDriverFactory.webDriver(desiredCapabilities, baseUrl);
    }

    @Primary
    @Bean
    public URI baseUrl(@Value("${webdriver.baseUrl:http://auto}") URI value) throws UnknownHostException {
        if (value.equals(URI.create("http://auto"))) {
            return URI.create("http://" + InetAddress.getLocalHost().getHostAddress() + ":8080");
        }
        return value;
    }

    @Bean
    public URI baseUrlHttps(@Value("${webdriver.baseUrlHttps:https://auto}") URI value) throws UnknownHostException {
        if (value.equals(URI.create("https://auto"))) {
            return URI.create("https://" + InetAddress.getLocalHost().getHostAddress() + ":8443");
        }
        return value;
    }

    @Bean
    public HttpStatusCodeSupplier httpStatusCodeSupplier() {
        return new HttpStatusCodeSupplier();
    }

    @Bean
    public ScreencastCapture screencastCapture() throws IOException {
        return new ScreencastCapture();
    }

    @Bean
    public WebElementScreenshotTaker webElementScreenshotTaker() {
        return new WebElementScreenshotTaker();
    }
}
