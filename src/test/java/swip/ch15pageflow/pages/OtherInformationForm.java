package swip.ch15pageflow.pages;


import swip.ch15pageflow.domain.MailingOption;
import swip.ch15pageflow.domain.OtherInformation;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.locators.Name;


import static swip.ch15pageflow.locators.BookStoreId.*;

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
        browser.setRadio(Name.MAILING_OPTION, info.mailingOption.toString());
    }

    public OtherInformation getOtherInformation() {
        return new OtherInformation(
                browser.getInputText(COUPON_CODE),
                browser.getInputText(BILLING_EMAIL),
                browser.isChecked(CONFIRM_EMAIL),
                browser.isChecked(RATINGS),
                MailingOption.from(browser.getInputText(Name.MAILING_OPTION)),
                browser.getInputText(COMMENTS)
        );
    }

}
