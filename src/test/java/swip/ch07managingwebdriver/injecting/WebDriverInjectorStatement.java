package swip.ch07managingwebdriver.injecting;

import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swip.ch07managingwebdriver.ConfigFactory;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;

class WebDriverInjectorStatement extends Statement {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverInjectorStatement.class);
    private final WebDriver driver;
    private final Object target;
    private final Statement statement;

    WebDriverInjectorStatement(WebDriver driver, Object target, Statement statement) {
        this.driver = driver;
        this.target = target;
        this.statement = statement;
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
    public void evaluate() throws Throwable {
        LOGGER.info("injecting " + driver + " into " + target);

        inject(target, driver);
        inject(target, URI.create(ConfigFactory.BASE_URL));

        statement.evaluate();
    }
}
