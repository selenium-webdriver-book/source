package swip.ch08unicorns.modals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class Modals {
    public static ExpectedCondition<Alert> modalIsDisplayed() { // a static method that return as ExpectedCondition
        return driver -> {
            List<WebElement> bootstrapModal = driver.findElements(By.className("modal-dialog")); // it might not exist yet, so search for any
            List<WebElement> otherModal = driver.findElements(By.className("other-modal"));

            return !bootstrapModal.isEmpty() // only return a model if it is visible
                    ? new BootstrapModal(bootstrapModal.get(0))
                    : !otherModal.isEmpty()
                    ? new OtherModel(otherModal.get(0))
                    : null;
        };
    }
}
