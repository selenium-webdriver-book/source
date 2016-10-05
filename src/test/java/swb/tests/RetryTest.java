package swb.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import swb.framework.robust.Retry;

import java.util.concurrent.TimeUnit;

public class RetryTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private final Retry retry = new Retry(2, 1, TimeUnit.MILLISECONDS);
    private int i = 0;

    @Test
    public void failureAfterCountShouldThrowException() throws Exception {
        expectedException.expect(IllegalStateException.class);

        retry.attempt(() -> {
            throw new IllegalStateException();
        });
    }

    @Test
    public void failureTheSuccessBeforeCountShouldNotThrowsException() throws Exception {
        retry.attempt(() -> {
            if (i++ == 0) {
                throw new IllegalStateException();
            }
        });
    }
}