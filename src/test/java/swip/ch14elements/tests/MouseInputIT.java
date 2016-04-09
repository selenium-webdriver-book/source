package swip.ch14elements.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import swip.ch14elements.framework.Browser;
import swip.ch14elements.framework.BrowserRunner;

import javax.inject.Inject;

@RunWith(BrowserRunner.class)
public class MouseInputIT {

    @Inject private Browser driver;

    @Test public void completeAFormUsingBothMouseAndKeyboard() throws Exception {
        driver.get("/mailing-list.html");
        driver.setInputText((By.name("email")), "john.doe@swip.com");
        driver.untilFound(By.name("terms")).click();
        driver.doubleClick(By.tagName("button"));
    }
}
