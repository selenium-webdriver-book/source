package swip.junit;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.remote.DesiredCapabilities;
import swip.util.WebDriverSupplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeleniumWebDriverRunner extends Suite {

    private static final WebDriverSupplier WEB_DRIVER_SUPPLIER = new WebDriverSupplier(); // # static to ensure all test share the same drivers

    public SeleniumWebDriverRunner(Class<?> testClass) throws InitializationError {
        super(testClass, createRunners(testClass));
    }

    // # this method creates one runner per configuration
    private static List<Runner> createRunners(Class<?> testClass) throws InitializationError {
        List<Runner> runners = new ArrayList<>();
        for (DesiredCapabilities desiredCapabilities : new DesiredCapabilities[]{
                DesiredCapabilities.htmlUnit(),
                DesiredCapabilities.safari(),
                DesiredCapabilities.chrome(),
                DesiredCapabilities.firefox(),
        }) {
            if (!excluded(testClass, desiredCapabilities)
                    && included(desiredCapabilities)) {
                // # the actual running of the test is delegate to a DesiredCapabilitiesRunner
                //    telling it the capabilities we need
                runners.add(new DesiredCapabilitiesRunner(desiredCapabilities, testClass, WEB_DRIVER_SUPPLIER));
            }
        }
        return runners;
    }

    /**
     * Allow the required capabilities to change from the command line.
     * <p/>
     * {@code webdriver.capabilities.browserName=firefox}
     */
    private static boolean included(DesiredCapabilities desiredCapabilities) {
        String prefix = "webdriver.capabilities";
        return System.getProperties()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().toString().startsWith(prefix)) // # ignore properties that do not have our prefix
                .allMatch(entry -> desiredCapabilities // # try and find any match for you property
                                .asMap()
                                .get(entry.getKey().toString().substring(prefix.length() + 1)) // # strip the prefix from the property name
                                .toString()
                                .equals(entry.getValue())
                );
    }

    private static boolean excluded(Class<?> testClass, DesiredCapabilities desiredCapabilities) {
        Config config = testClass.getAnnotation(Config.class);
        return config != null // # if the @Config annotation is missing, this cannot be excluded
                &&
                desiredCapabilities
                        .asMap()
                        .entrySet()
                        .stream()
                        .anyMatch(entry -> Arrays.stream(config.exclude()) // # excluded if any of the properties match
                                .anyMatch(c -> c.equals(entry.getKey() + "=" + entry.getValue())));
    }
}