package swip.framework;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URLEncoder;

public class ExtentReportsListener extends AbstractTestExecutionListener {

    private static final ExtentReports EXTENT = new ExtentReports("target/extent.html", true);
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtentReportsListener.class);
    private final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    static {
        EXTENT.loadConfig(ExtentReportsListener.class, "/extent-config.xml");
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        Method testMethod = testContext.getTestMethod();
        currentTest.set(EXTENT.startTest(String.format("%s#%s", testMethod.getDeclaringClass().getSimpleName(),
                testMethod.getName())));
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        ExtentTest extentTest = currentTest.get();

        @SuppressWarnings("ThrowableResultOfMethodCallIgnored") Throwable testException = testContext.getTestException();

        Method method = testContext.getTestMethod();
        String fileName = String.format("screenshots/%s#%s.png", method.getDeclaringClass().getName(), method.getName());

        boolean screenshotExists = new File(fileName).exists();

        LOGGER.info("fileName={}, screenshotExists={}" ,fileName, screenshotExists);

        String img = extentTest.addScreenCapture(URLEncoder.encode(fileName, "UTF-8"));
        LogStatus status = testException != null ? LogStatus.FAIL : LogStatus.PASS;
        switch (status) {
            case PASS:
                extentTest.log(status, img);
                break;
            case FAIL:
                extentTest.log(status, "<code>" + describe(testContext.getTestException()) + "</code>" + img);
        }

        EXTENT.endTest(extentTest);
    }

    private static String describe(Throwable exception) {
        if (exception == null) {
            return null;
        }
        StringWriter out = new StringWriter();
        //noinspection ThrowableResultOfMethodCallIgnored
        for (StackTraceElement element : exception.getStackTrace()) {
            out.append(element.toString()).append("\n");
        }
        return out.toString();
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        EXTENT.flush();
    }
}
