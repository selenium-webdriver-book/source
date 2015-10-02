package swip.ch13elements;

import org.openqa.selenium.By;
import swip.ch13elements.framework.Browser;

public class OtherInformationForm {
    private final Browser browser;

    public OtherInformationForm(Browser browser) {
        this.browser = browser;
    }

    public OtherInformation getOtherInformation() {
        return new OtherInformation(browser.getInputText(By.id("gc-redemption-code")),
                browser.getInputText(By.id("billing-email")),
                browser.isChecked(By.id("confirm-email")),
                browser.isChecked(By.id("ratings")),
                MailingOption.from(browser.getRadio(By.name("customFieldDS.customfield_ROW0_value"))),
                browser.getInputText(By.id("comments"))
        );
    }

    public void setOtherInformation(OtherInformation otherInformation) throws Exception {
        browser.setInputText(By.id("gc-redemption-code"), otherInformation.couponCode);
        browser.setInputText(By.id("billing-email"), otherInformation.email);
        browser.setCheckboxValue(By.id("confirm-email"), otherInformation.sendOrdersToEmail);
        browser.setCheckboxValue(By.id("ratings"), otherInformation.sendRatingEmail);
    }
}
