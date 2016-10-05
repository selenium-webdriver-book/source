package swb.framework;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import swb.ch07managingwebdriver.screenshot.ScreenshotTaker;

/**
 * This class allows you to use the same configuration for all your tests.
 */
public class WebDriverRunner extends SpringJUnit4ClassRunner {

    public WebDriverRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected TestContextManager createTestContextManager(Class<?> clazz) {
        return super.createTestContextManager(ConfigShim.class);
    }

    @ContextConfiguration(classes = WebDriverConfig.class)
    @TestExecutionListeners(listeners = {ScreenshotTaker.class, DependencyInjectionTestExecutionListener.class})
    public static class ConfigShim {

    }
}