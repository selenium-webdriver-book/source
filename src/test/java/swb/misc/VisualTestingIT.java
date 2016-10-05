package swb.misc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import swb.framework.WebDriverRunner;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Ignore
@RunWith(WebDriverRunner.class)
public class VisualTestingIT {
    @Inject
    private WebDriver driver;

    private static boolean makeThumbnail(File f) throws IOException {
        return ImageIO.write(resizeImage(ImageIO.read(f), 400), "png", new File(f.getName().replace(".png", "-thumb.png")));
    }

    private static BufferedImage diffImages(BufferedImage a, BufferedImage b) {
        int w = Math.max(a.getWidth(), b.getWidth());
        int h = Math.max(a.getHeight(), b.getHeight());
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                img.setRGB(x, y, 0xffffff - (rgb(a, x, y) ^ rgb(b, x, y)));
            }
        }

        return img;
    }

    private static int rgb(BufferedImage a, int x, int y) {
        return a.getWidth() > x && a.getHeight() > y ? a.getRGB(x, y) : 0;
    }

    private static BufferedImage resizeImage(BufferedImage image, int max) {
        int w = image.getWidth();
        int h = image.getHeight();

        double scale = 1.0 / (w > h ? (double) w / max : (double) h / max);
        int rW = (int) (w * scale);
        int rH = (int) (h * scale);

        assert rW <= max && rH <= max;

        BufferedImage outImage = new BufferedImage(rW, rH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = outImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(image, 0, 0, rW, rH, null);
        g.dispose();

        return outImage;
    }

    @Before
    public void setUp() throws Exception {
        driver.manage().window().setSize(new Dimension(800, 600));
    }

    @Test
    public void exploratoryTest() throws Exception {
        driver.get("/styled-elements.html");
        File a = new File("a.png");
        screenshot(a);
        driver.get("/styled-elements-2.html");
        File b = new File("b.png");
        screenshot(b);

        File diff = new File("a-b.png");
        createDiff(a, b, diff);
        for (File f : new File[]{a, b, diff}) {
            makeThumbnail(f);
        }
    }

    private void createDiff(File a, File b, File diff) throws IOException {
        ImageIO.write(diffImages(ImageIO.read(a), ImageIO.read(b)), "png", diff);
    }

    private void screenshot(File saveAs) throws IOException {
        File file = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE)
                .getAbsoluteFile();
        if (saveAs.exists()) {
            Files.delete(saveAs.toPath());
        }
        Files.move(file.toPath(), saveAs.toPath());
    }

}
