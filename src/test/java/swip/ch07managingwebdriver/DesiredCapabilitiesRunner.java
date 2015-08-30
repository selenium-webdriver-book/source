package swip.ch07managingwebdriver;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

class DesiredCapabilitiesRunner extends BlockJUnit4ClassRunner {
    private final DesiredCapabilities desiredCapabilities;
    private final WebDriverSupplier webDriverSupplier;

    DesiredCapabilitiesRunner(DesiredCapabilities desiredCapabilities, Class<?> testClass,
                              WebDriverSupplier webDriverSupplier) throws InitializationError {
        super(testClass);
        this.desiredCapabilities = desiredCapabilities;
        this.webDriverSupplier = webDriverSupplier;
    }

    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        return new WebDriverInjectorStatement(
                getDriver(),
                target,
                super.withBefores(method, target, statement)
        );
    }

    private WebDriver getDriver() {
        return webDriverSupplier.get(desiredCapabilities);
    }

    @Override
    protected Statement withAfters(FrameworkMethod method, Object target, Statement statement) {
        return super.withAfters(
                method,
                target,
                new DirtyWebDriverStatement(getDriver(), method, getTakeScreenshotStatement(method, statement))
        );
    }

    private TakeScreenshotStatement getTakeScreenshotStatement(FrameworkMethod method, Statement statement) {
        return new TakeScreenshotStatement(
                getDriver(),
                method.getDeclaringClass().getName() + "-" + testName(method),
                statement
        );
    }
}