package swip.ch15pageflow.pages.v2;

import swip.ch15pageflow.domain.Address;
import swip.ch15pageflow.domain.CreditCard;
import swip.ch15pageflow.domain.OtherInformation;
import swip.ch15pageflow.framework.v2.Browser;
import swip.ch15pageflow.locators.CssSelector;

public class ShoppingCartPage {

    private final BillingAddressForm billingAddressForm;
    private final CreditCardForm creditCardForm;
    private final OtherInformationForm otherInformationForm;
    private final Browser browser;

    public ShoppingCartPage(Browser browser) {
        this.browser = browser;
        this.otherInformationForm = new OtherInformationForm(browser);
        this.billingAddressForm = new BillingAddressForm(browser);
        this.creditCardForm = new CreditCardForm(browser);
    }

    public void setOtherInformation(OtherInformation otherInformation) {
        otherInformationForm.setOtherInformation(otherInformation);
    }

    public void setBillingAddress(Address address) {
        billingAddressForm.setBillingAddress(address);
    }

    public void setCreditCard(CreditCard card) {
        creditCardForm.setCreditCard(card);
    }

    public void continues() {
        browser.untilFound(CssSelector.CONTINUE).click();
    }



}