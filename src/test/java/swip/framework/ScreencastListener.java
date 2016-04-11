package swip.framework;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class ScreencastListener extends AbstractTestExecutionListener {

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        Method testMethod = testContext.getTestMethod();
        Optional<CapturesScreencast> capturesScreencast = getCapturesScreencast(testContext);
        if (capturesScreencast.isPresent()) {
            capturesScreencast.get()
                    .startScreencastCapture(new File(String.format("target/screencasts/%s#%s.gif", testMethod.getDeclaringClass().getName(), testMethod.getName())));
        }
    }

    private static Optional<CapturesScreencast> getCapturesScreencast(TestContext testContext) {
        Object testInstance = testContext.getTestInstance();
        return Arrays.stream(testInstance.getClass().getDeclaredFields())
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(testInstance);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(value -> value != null)
                .filter(value-> CapturesScreencast.class.isAssignableFrom(value.getClass()))
                .map(value -> (CapturesScreencast) value)
                .findFirst();
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        Optional<CapturesScreencast> capturesScreencast = getCapturesScreencast(testContext);
        if (capturesScreencast.isPresent()) {
            capturesScreencast.get().endScreencastCapture();
        }
    }
}
