package swip.ch08unicorns.modals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(WebDriverRunner.class)
@Config(exclude = {"browserName=htmlunit"})
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
}
