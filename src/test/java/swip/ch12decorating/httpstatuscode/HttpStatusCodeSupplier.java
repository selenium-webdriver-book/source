package swip.ch12decorating.httpstatuscode;

import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class HttpStatusCodeSupplier extends HttpFiltersSourceAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpStatusCodeSupplier.class);

    private int httpStatusCode;

    public HttpStatusCodeSupplier(URI baseUrl) {
        super();
    }

    @Override
    public HttpFilters filterRequest(HttpRequest originalRequest) {
        return new HttpFiltersAdapter(originalRequest) {

            @Override
            public HttpObject serverToProxyResponse(HttpObject httpObject) {
                return super.serverToProxyResponse(httpObject);
            }
        };
    }

    public int get() throws InterruptedException {
        LOGGER.info("requesting status code, httpStatusCode={}", httpStatusCode);
        if (httpStatusCode == 0) {
            throw new IllegalStateException(
                    "no request has yet been successfully intercepted");
        }
        return httpStatusCode;
    }
}
