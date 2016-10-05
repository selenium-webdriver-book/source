package swb.framework;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class BrowserRunner extends SpringJUnit4ClassRunner {
    public BrowserRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected TestContextManager createTestContextManager(Class<?> clazz) {
        return super.createTestContextManager(ConfigShim.class);
    }

    @ContextConfiguration(classes = BrowserConfig.class)
    public static class ConfigShim {
    }
}
