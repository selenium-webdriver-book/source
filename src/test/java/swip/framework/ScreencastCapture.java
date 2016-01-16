package swip.framework;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swip.framework.util.GifSequenceWriter;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

/**
 * Capture the steps in a page flow or test scenario, and save it as a page for later viewing.
 */
public class ScreencastCapture implements AutoCloseable, CapturesScreencast {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreencastCapture.class);
    private static final List<String> CAPTURE_BLACKLIST = Arrays.asList("findElement", "findElements", "manage", "getAttribute",
            "getCoordinates", "getKeyboard", "getMouse", "getTagName", "getTitle", "getText", "getPageSource",
            "getScreenshotAs", "getWindowHandle", "getWindowHandles", "isDisplayed", "isVisible", "switchTo");
    private FileImageOutputStream outputStream;
    private GifSequenceWriter gifSequenceWriter;

    private static Class[] getInterfaces(Class<?> driverClass) {
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(driverClass);
        return allInterfaces.toArray(new Class[allInterfaces.size()]);
    }

    @Override
    public void startScreencastCapture(File file) {
        LOGGER.info("starting screencast capture file={}", file);
        try {
            if (!file.getParentFile().exists()) {
                Files.createDirectory(file.getParentFile().toPath());
            }
            outputStream = new FileImageOutputStream(file);
            gifSequenceWriter = new GifSequenceWriter(outputStream, BufferedImage.TYPE_INT_RGB, 250, true);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void endScreencastCapture() {
        LOGGER.info("ending screencast capture");
        try {
            close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (gifSequenceWriter != null) {
            gifSequenceWriter.close();
            gifSequenceWriter = null;
        }
        if (outputStream != null) {
            outputStream.close();
            outputStream = null;
        }
    }

    private InvocationHandler getInvocationHandler(WebDriver driver) {
        return (proxy, method, args) -> {
            switch (method.getName()) {
                case "getWrappedDriver":
                    return driver;
                case "endScreencastCapture":
                    capture(driver, method, args);
                    endScreencastCapture();
                    return null;
                case "startScreencastCapture":
                    startScreencastCapture((File) args[0]);
                    return null;
                default:
                    try {
                        return maybeWrap(driver, method.invoke(driver, args));
                    } catch (InvocationTargetException e) {
                        throw e.getCause();
                    } finally {
                        capture(driver, method, args);
                    }
            }
        };
    }

    private void capture(WebDriver driver, Method method, Object[] args) throws IOException {
        if (!CAPTURE_BLACKLIST.contains(method.getName()) && driver instanceof TakesScreenshot && gifSequenceWriter != null) {

            // taking screenshots appears to interfere with alerts
            boolean alertIsPresent = ExpectedConditions.alertIsPresent().apply(driver) != null;

            if (!alertIsPresent) {
                LOGGER.info("capturing method={}, args={}", method, args);
                try {
                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    gifSequenceWriter.write(ImageIO.read(screenshot));
                } catch (UnhandledAlertException | NoSuchWindowException ignored) {
                    LOGGER.warn("failed to capture due to " + ignored);
                }
            } else {
                LOGGER.info("not capturing screencast, alert is present");
            }
        } else {
            LOGGER.debug("ignoring method={}", method);
        }
    }

    private InvocationHandler getInvocationHandler(WebDriver driver, Object element) {
        return (proxy, method, args) -> {
            switch (method.getName()) {
                case "getWrappedElement":
                    return element;
                default:
                    try {
                        return maybeWrap(driver, method.invoke(element, args));
                    } catch (InvocationTargetException e) {
                        throw e.getCause();
                    } finally {
                        capture(driver, method, args);
                    }
            }
        };
    }

    private Object maybeWrap(WebDriver driver, Object invoke) {
        if (invoke != null && invoke instanceof WebElement) {
            return create(driver, (WebElement) invoke);
        } else if (invoke != null && (invoke instanceof Mouse || invoke instanceof Keyboard)) {
            return create(driver, invoke);
        } else {
            return invoke;
        }
    }

    public WebDriver createCapturingWebDriver(WebDriver driver) {

        Class<?> type = driver.getClass();

        Class[] interfaces = ArrayUtils.addAll(getInterfaces(type), WrapsDriver.class, CapturesScreencast.class);

        InvocationHandler invocationHandler = getInvocationHandler(driver);

        //noinspection unchecked
        return (WebDriver) Proxy.newProxyInstance(type.getClassLoader(), interfaces, invocationHandler);
    }

    private WebElement create(WebDriver driver, WebElement element) {

        Class<?> type = element.getClass();

        Class[] interfaces = ArrayUtils.add(getInterfaces(type), WrapsElement.class);

        InvocationHandler invocationHandler = getInvocationHandler(driver, element);

        //noinspection unchecked
        return (WebElement) Proxy.newProxyInstance(type.getClassLoader(), interfaces, invocationHandler);
    }

    private Object create(WebDriver driver, Object object) {

        Class<?> type = object.getClass();

        Class[] interfaces = getInterfaces(type);

        InvocationHandler invocationHandler = getInvocationHandler(driver, object);

        //noinspection unchecked
        return Proxy.newProxyInstance(type.getClassLoader(), interfaces, invocationHandler);
    }
}