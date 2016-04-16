package swip.ch08windows.modals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

@RunWith(WebDriverRunner.class)
public class SyntheticModalIT {
    @Inject
    private WebDriver driver;

    @Test
    public void syntheticModal() throws Exception {
        driver.get("/popups.html");

        driver.findElement(By.linkText("Modal")).click();
        Alert modal = new WebDriverWait(driver, 2).until(Modals.modalIsDisplayed());

        modal.sendKeys("some information");
        assertEquals("Modal", modal.getText());
        modal.dismiss();

        assertFalse(driver.findElement(By.className("modal-dialog")).isDisplayed());

    }

    @Test
    public void demonstrateAccessToUnclickableElement() throws Exception {

        driver.get("/popups.html");

        driver.findElement(By.linkText("Modal")).click();

        new WebDriverWait(driver, 2).until(Modals.modalIsDisplayed());

        WebElement openModalButton = driver.findElement(By.linkText("Modal"));

        try {
            openModalButton.click();
            fail("should not be able to click when modal is displayed");
        } catch(WebDriverException e) {
            assertThat(e.getMessage(),
                    containsString("Other element would receive the click"));
        }
    }
}
