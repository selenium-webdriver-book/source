package swip.framework;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.io.StringWriter;
import java.lang.reflect.Method;

public class ExtentReportsListener implements TestExecutionListener {

    private final ExtentReports extent = new ExtentReports("target/extent.html", true);
    private final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        extent.loadConfig(ExtentReportsListener.class, "/extent-config.xml");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {

    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        Method testMethod = testContext.getTestMethod();
        currentTest.set(extent.startTest(String.format("%s#%s", testMethod.getDeclaringClass().getSimpleName(),
                testMethod.getName())));
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        ExtentTest extentTest = currentTest.get();

        @SuppressWarnings("ThrowableResultOfMethodCallIgnored") Throwable testException = testContext.getTestException();
        extentTest.log(testException != null ? LogStatus.ERROR : LogStatus.PASS, describe(testContext.getTestException()));

        extent.endTest(extentTest);
    }

    private String describe(Throwable exception) {
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
        extent.flush();
    }
}
