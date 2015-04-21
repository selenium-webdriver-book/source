package swip.junit;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.openqa.selenium.remote.DesiredCapabilities;
import swip.util.WebDriverSupplier;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.net.URI;

class DesiredCapabilitiesRunner extends BlockJUnit4ClassRunner {
    private final DesiredCapabilities desiredCapabilities;
    private final WebDriverSupplier webDriverSupplier;

    DesiredCapabilitiesRunner(DesiredCapabilities desiredCapabilities, Class<?> testClass,
                              WebDriverSupplier webDriverSupplier) throws InitializationError {
        super(testClass);
        this.desiredCapabilities = desiredCapabilities;
        this.webDriverSupplier = webDriverSupplier;
    }


    private static void inject(Object target, Object bean) {
        // # a very primitive form of injection, we simple find any field annotated
        //    with @Inject and if the target has the same class, set it using
        //    standard Java Reflection
        for (Field field : target.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Inject.class) != null && field.getType().isAssignableFrom(bean.getClass())) {
                try {
                    field.setAccessible(true); // # make sure the field is writable
                    field.set(target, bean);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        // # inject the web driver and the capabilities into the test class
        inject(target, webDriverSupplier.get(desiredCapabilities));
        inject(target, URI.create(System.getProperty("webdriver.baseurl", "http://localhost:8080")));
        return super.withBefores(method, target, statement);
    }

    @Override
    protected String getName() {
        // # to make sure that the class has a clean name in the IDE
        return String.format("%s - %s", super.getName(), desiredCapabilities.getBrowserName());
    }

    @Override
    protected String testName(final FrameworkMethod method) {
        // # as above, make sure that the class is clearly named in the IDE
        return String.format("%s - %s", method.getName(), desiredCapabilities.getBrowserName());
    }
}