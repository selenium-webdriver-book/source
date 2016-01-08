package swip.ch12decorating.httpstatuscode;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import net.lightbody.bmp.proxy.http.BrowserMobHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class HttpStatusCodeSupplier {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpStatusCodeSupplier.class);

    private int httpStatusCode;

    public HttpStatusCodeSupplier(ProxyServer server, URI baseUrl) {
        server.addResponseInterceptor(
                (BrowserMobHttpResponse httpResponse, Har har) -> {
                    String path = httpResponse.getEntry().getRequest().getUrl();
                    boolean capture = !path.matches(".*\\.(js|css|ico|json)") &&
                            path.startsWith(baseUrl.toString());
                    LOGGER.info("path={}, capture={}", path, capture);
                    if (httpResponse.getRawResponse() != null && capture) {
                        httpStatusCode = httpResponse.getRawResponse()
                                .getStatusLine().getStatusCode();
                        LOGGER.info("httpStatusCode={}", httpStatusCode);
                    }
                });
    }

    public int get() throws InterruptedException {
        // a short wait, as interceptors occur on another thread
        Thread.sleep(1000);
        LOGGER.info("requesting status code, httpStatusCode={}", httpStatusCode);
        if (httpStatusCode == 0) {
            throw new IllegalStateException(
                    "no request has yet been successfully intercepted");
        }
        return httpStatusCode;
    }
}
