package swb.framework;

import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;

public class HttpStatusCodeSupplier extends HttpFiltersSourceAdapter {

    private int httpStatusCode;

    @Override
    public HttpFilters filterRequest(HttpRequest originalRequest) {
        return new HttpFiltersAdapter(originalRequest) {
            public String uri;

            @Override
            public HttpResponse proxyToServerRequest(HttpObject httpObject) {
                if (httpObject instanceof HttpRequest) {
                    HttpRequest httpRequest = (HttpRequest) httpObject;
                    uri = httpRequest.getUri();
                }
                return super.proxyToServerRequest(httpObject);
            }

            @Override
            public HttpObject serverToProxyResponse(HttpObject httpObject) {
                if (httpObject instanceof HttpResponse) {
                    HttpResponse httpResponse = (HttpResponse) httpObject;
                    if (uri.endsWith("html")) {
                        httpStatusCode = httpResponse.getStatus().code();
                    }
                }
                return super.serverToProxyResponse(httpObject);
            }
        };
    }

    public int get() throws InterruptedException {
        if (httpStatusCode == 0) {
            throw new IllegalStateException(
                    "no request has yet been successfully intercepted");
        }
        return httpStatusCode;
    }
}
