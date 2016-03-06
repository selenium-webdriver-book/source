package swip.ch15pageflow.pages.v2;


import swip.ch15pageflow.domain.OtherInformation;
import swip.ch15pageflow.framework.v2.Browser;
import swip.ch15pageflow.locators.Name;

import static swip.ch15pageflow.locators.Id.*;
import static swip.ch15pageflow.locators.Name.MAILING_OPTION;

public class OtherInformationForm {

    private Browser browser;

    public OtherInformationForm(Browser browser) {

        this.browser = browser;
    }

    public void setOtherInformation(OtherInformation info) {
        browser.setInputText(COUPON_CODE, info.couponCode);
        browser.setInputText(BILLING_EMAIL, info.email);
        browser.setInputText(COMMENTS, info.comments);
        browser.setCheckboxValue(CONFIRM_EMAIL, info.sendOrdersToEmail);
        browser.setCheckboxValue(RATINGS, info.sendRatingEmail);
        browser.setRadio(MAILING_OPTION, info.mailingOption);
    }
}
