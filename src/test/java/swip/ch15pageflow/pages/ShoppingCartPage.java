package swip.ch15pageflow.pages;

import swip.ch15pageflow.domain.Address;
import swip.ch15pageflow.domain.CreditCard;
import swip.ch15pageflow.domain.OtherInformation;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.locators.CssSelector;
import swip.ch15pageflow.locators.Xpath;

public class ShoppingCartPage {

    private final BillingAddressForm billingAddressForm;
    private final CreditCardForm creditCardForm;
    private final ShippingAddressForm shippingAddressForm;
    private final OtherInformationForm otherInformationForm;
    private final Browser browser;

    public ShoppingCartPage(Browser browser) {
        this.browser = browser;
        this.otherInformationForm = new OtherInformationForm(browser);
        this.billingAddressForm = new BillingAddressForm(browser);
        this.creditCardForm = new CreditCardForm(browser);
        this.shippingAddressForm = new ShippingAddressForm(browser);
    }

    public OtherInformation getOtherInformation() {
        return otherInformationForm.getOtherInformation();
    }

    public void setOtherInformation(OtherInformation otherInformation) {
        otherInformationForm.setOtherInformation(otherInformation);
    }

    public void setBillingAddress(Address address) {
        billingAddressForm.setBillingAddress(address);
    }

    public void getBillingAddress() {
        billingAddressForm.getBillingAddress();
    }

    public void setCreditCard(CreditCard card) {
        creditCardForm.setCreditCard(card);
    }

    public CreditCard getCreditCard() {
        return creditCardForm.getCreditCard();
    }

    public void setQuantity(int quantity) {
        browser.setInputText(Xpath.QUANTITY, String.valueOf(quantity));
        browser.untilFound2(CssSelector.UPDATE).click();
    }

    public void continues() {
        browser.untilFound2(CssSelector.CONTINUE).click();
    }

    public ErrorMessages getErrorMessages() {
        return new ErrorMessages(browser);
    }

    public void setShippingAddress(Address shippingAddress) {
        shippingAddressForm.setShippingAddress(shippingAddress);
    }

    public void getShippingAddress() {
        shippingAddressForm.getShippingAddress();
    }

}