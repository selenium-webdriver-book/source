package swip.ch07managingwebdriver;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DirtyWebDriverStatement extends Statement {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirtyWebDriverStatement.class);
    private final WebDriver webDriver;
    private final FrameworkMethod method;
    private final Statement statement;

    DirtyWebDriverStatement(WebDriver webDriver, FrameworkMethod method, Statement statement) {
        this.webDriver = webDriver;
        this.method = method;
        this.statement = statement;
    }

    @Override
    public void evaluate() throws Throwable {
        try {
            statement.evaluate();
        } finally {
            if (method.getAnnotation(DirtiesDriver.class) != null) {
                LOGGER.info("closing dirty driver");
                webDriver.quit();
            }
        }
    }
}
