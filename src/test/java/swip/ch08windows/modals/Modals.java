package swip.ch08windows.modals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class Modals {
    public static ExpectedCondition<Alert> modalIsDisplayed() {
        return new ExpectedCondition<Alert>() {

            @Override
            public Alert apply(WebDriver driver) {
                List<WebElement> bootstrapModal = driver.findElements(By.className("modal-dialog"));
                List<WebElement> otherModal = driver.findElements(By.className("other-modal"));

                return !bootstrapModal.isEmpty()
                        ? new BootstrapModal(bootstrapModal.get(0))
                        : !otherModal.isEmpty()
                        ? new OtherModel(otherModal.get(0))
                        : null;
            }
        };
    }
}
