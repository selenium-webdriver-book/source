package swip.ch07managingwebdriver;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;

class DesiredCapabilitiesRunner extends BlockJUnit4ClassRunner {
    private final DesiredCapabilities desiredCapabilities;
    private final WebDriverSupplier webDriverSupplier;
    private WebDriver driver;

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
        for (Method method : target.getClass().getDeclaredMethods()) {
            if (method.getAnnotation(Inject.class) != null && method.getParameterTypes()[0].isAssignableFrom(bean.getClass())) {
                try {
                    method.setAccessible(true);
                    method.invoke(target, bean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        // # inject the web driver and the capabilities into the test class
        driver = webDriverSupplier.get(desiredCapabilities);
        inject(target, driver);
        inject(target, URI.create(ConfigFactory.BASE_URL));
        return super.withBefores(method, target, statement);
    }

    @Override
    protected Statement withAfters(
            FrameworkMethod method,
            Object target,
            Statement statement
    ) {
        return super.withAfters(
                method,
                target,
                new TakeScreenshotStatement(
                        driver,
                        method.getDeclaringClass().getName() + "-" + testName(method),
                        statement
                )
        );
    }

    @Override
    protected String getName() {
        // # to make sure that the class has a clean name in the IDE
        return String.format("[%s]", desiredCapabilities.getBrowserName());
    }

    @Override
    protected String testName(final FrameworkMethod method) {
        // # as above, make sure that the class is clearly named in the IDE
        return String.format("%s[%s]", method.getName(), desiredCapabilities.getBrowserName());
    }
}