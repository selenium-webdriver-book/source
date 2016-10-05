package swb;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            new ServletFileUpload(new DiskFileItemFactory())
                    .parseRequest(req)
                    .stream()
                    .filter(i -> !i.isFormField())
                    .forEach(i -> {
                        if (i.getName().isEmpty()) {
                            throw new IllegalStateException("no name");
                        }
                        System.out.printf("uploaded %s%n", i.getName());
                    });
        } catch (Exception e) {
            throw new ServletException(e);
        }

        resp.setStatus(302);
        resp.addHeader("Location", "thank-you.html");
    }
}
