package swip.ch07managingwebdriver;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.List;

public class WebDriverRunner extends Suite {

    private static final WebDriverSupplier WEB_DRIVER_SUPPLIER = new WebDriverSupplier(); // # static to ensure all test share the same drivers

    public WebDriverRunner(Class<?> testClass) throws InitializationError {
        super(testClass, createRunners(testClass));
    }

    // # this method creates one runner per configuration
    private static List<Runner> createRunners(Class<?> testClass) throws InitializationError {
        DesiredCapabilities capabilities = getDesiredCapabilities();

        return Collections.singletonList(new DesiredCapabilitiesRunner(capabilities, testClass, WEB_DRIVER_SUPPLIER));
    }

    private static DesiredCapabilities getDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setBrowserName(System.getProperty("webdriver.capabilities.browserName", "firefox"));
        return capabilities;
    }
}