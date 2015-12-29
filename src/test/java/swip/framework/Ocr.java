package swip.framework;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Ocr {
    private final URI serviceLocation;

    public Ocr(URI serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String recognise(File imageFile) throws IOException {
        String formatName = imageFile.getName().replaceFirst("^.*\\.(.*)$", "$1");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadFile = new HttpPost(serviceLocation);
            uploadFile.setEntity(MultipartEntityBuilder.create()
                    .setBoundary("BOUNDARY")
                    .addPart("1", new StringBody("{\"engine\": \"tesseract\"}", ContentType.APPLICATION_JSON))
                    .addPart("2", new FileBody(imageFile, ContentType.create("image/" + formatName)))
                    .build());
            uploadFile.setHeader("Content-Type", "multipart/related; boundary=BOUNDARY");


            try (CloseableHttpResponse response = httpClient.execute(uploadFile)) {
                if (200 != response.getStatusLine().getStatusCode()) {
                    throw new IllegalStateException("expected 200 OK, got " + response.getStatusLine());
                }
                return EntityUtils.toString(response.getEntity());
            }
        }
    }
}
