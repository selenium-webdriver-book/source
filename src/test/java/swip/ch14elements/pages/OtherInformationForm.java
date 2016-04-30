package swip.ch14elements.pages;

import swip.ch14elements.domain.OtherInformation;
import swip.framework.Browser;

import static swip.locators.Id.*;
import static swip.locators.Name.MAILING_OPTION;

public class OtherInformationForm {
    private final Browser browser;

    public OtherInformationForm(Browser browser) {
        this.browser = browser;
    }

    public OtherInformation getOtherInformation() {

        return new OtherInformation(
                browser.getInputText(COUPON_CODE),
                browser.getInputText(BILLING_EMAIL),
                browser.isChecked(CONFIRM_EMAIL),
                browser.isChecked(RATINGS),
                browser.getRadio(MAILING_OPTION),
                browser.getInputText(COMMENTS)
        );
    }

    public void setOtherInformation(OtherInformation info) {     //<1>
        browser.setInputText(COUPON_CODE, info.couponCode);
        browser.setInputText(BILLING_EMAIL, info.email);
        browser.setInputText(COMMENTS, info.comments);
        browser.setCheckboxValue(CONFIRM_EMAIL, info.sendOrdersToEmail);
        browser.setCheckboxValue(RATINGS, info.sendRatingEmail);     //<2>
        browser.setRadio(MAILING_OPTION, info.mailingOption);
    }
}
