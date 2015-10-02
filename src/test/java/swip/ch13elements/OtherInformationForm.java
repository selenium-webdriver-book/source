package swip.ch13elements;

import org.openqa.selenium.By;
import swip.ch13elements.framework.Browser;

public class OtherInformationForm {
    private final Browser browser;

    public OtherInformationForm(Browser browser) {
        this.browser = browser;
    }

    public void setOtherInformation(OtherInformation otherInformation) throws Exception {
        browser.setInputText(By.id("gc-redemption-code"), otherInformation.couponCode);
        browser.setInputText(By.id("billing-email"), otherInformation.email);
        browser.setCheckboxValue(By.id("confirm-email"), otherInformation.sendOrdersToEmail);
        // ...
    }
}
