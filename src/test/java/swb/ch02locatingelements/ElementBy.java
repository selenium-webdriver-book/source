package swb.ch02locatingelements;

import org.openqa.selenium.By;

public final class ElementBy {
    private ElementBy() {
    }

    public static By partialText(String text) {
        return By.xpath("//*[contains(normalize-space(.),'" + text + "')]");
    }

    public static By automationId(String id) {
        return By.className("wd-" + id);
    }
}
