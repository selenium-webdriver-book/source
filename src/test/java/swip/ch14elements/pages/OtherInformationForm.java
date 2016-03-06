package swip.ch14elements.pages;

import org.openqa.selenium.By;
import swip.ch14elements.domain.MailingOption;
import swip.ch14elements.domain.OtherInformation;
import swip.ch14elements.framework.Browser;

public class OtherInformationForm {
    private final Browser browser;

    public OtherInformationForm(Browser browser) {
        this.browser = browser;
    }

    public OtherInformation getOtherInformation() {
        String mailingOptionValue =
                browser.getRadio(By.name("customFieldDS.customfield_ROW0_value"));
        MailingOption mailingOption = MailingOption.from(mailingOptionValue);
        return new OtherInformation(
                browser.getInputText(By.id("gc-redemption-code")),
                browser.getInputText(By.id("billing-email")),
                browser.isChecked(By.id("confirm-email")),
                browser.isChecked(By.id("ratings")),
                mailingOption,
                browser.getInputText(By.id("comments"))
        );
    }

    public void setOtherInformation(OtherInformation otherInformation) {
        browser.setInputText(By.id("gc-redemption-code"),
                otherInformation.couponCode);
        browser.setInputText(By.id("billing-email"),
                otherInformation.email);
        browser.setCheckboxValue(By.id("confirm-email"),
                otherInformation.sendOrdersToEmail);
        browser.setCheckboxValue(By.id("ratings"),
                otherInformation.sendRatingEmail);
        browser.setInputText(By.id("comments"), otherInformation.comments);
    }
}
