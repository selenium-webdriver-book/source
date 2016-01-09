package swip.framework;

import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import swip.ch07managingwebdriver.screenshot.ScreenshotTaker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If you are using a recent version of spring-test (e.g >= version 4), then you can use an annotation to rut tests.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@WebDriverTest
@TestExecutionListeners(listeners = {ScreenshotTaker.class, DependencyInjectionTestExecutionListener.class})
public @interface WebDriverTest {
}
