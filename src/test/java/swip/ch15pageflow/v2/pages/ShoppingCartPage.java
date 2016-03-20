package swip.ch15pageflow.v2.pages;

import swip.ch15pageflow.domain.Address;
import swip.ch15pageflow.domain.CreditCard;
import swip.ch15pageflow.domain.OtherInformation;
import swip.ch15pageflow.v2.framework.Browser;

import static swip.ch15pageflow.locators.ClassName.ORDER_NUMBER;
import static swip.ch15pageflow.locators.CssSelector.CONTINUE;
import static swip.ch15pageflow.locators.Id.ORDER_NUMBER_ID;

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

    public void continues() {                 //<1>
        browser.click(CONTINUE);
    }

    public String getOrderNumber() {
        return browser.untilFound(ORDER_NUMBER_ID)
            .getText(ORDER_NUMBER);    //<2>
    }
}