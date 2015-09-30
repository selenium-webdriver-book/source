package swip.ch13elements.framework;

import java.util.concurrent.TimeUnit;

public class Retry {
    private final long interval;
    private final TimeUnit unit;
    private long count;

    public Retry(int count, int interval, TimeUnit unit) {
        this.count = count;
        this.interval = interval;
        this.unit = unit;
    }

    public void attempt(Attemptable task) throws Exception {
        for (int i = 0; i < count; i++) {
            try {
                task.attempt();
                return;
            } catch (Exception e) {
                if (i == count - 1) {
                    throw e;
                }
            }
            unit.sleep(interval);
        }
    }
}
