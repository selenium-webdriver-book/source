package swip.ch15pageflow.pages;


import swip.ch15pageflow.domain.Address;
import swip.framework.Browser;

import static swip.locators.Id.*;


public class BillingAddressForm {

    private Browser browser;

    public BillingAddressForm(Browser browser) {
        this.browser = browser;
    }

    public void setBillingAddress(Address address) {
        browser.setInputText(BILLING_FIRST_NAME, address.firstName);
        browser.setInputText(BILLING_LAST_NAME, address.lastName);
        browser.setInputText(BILLING_ADDRESS1, address.street1);
        browser.setInputText(BILLING_ADDRESS2, address.street2);
        browser.setInputText(BILLING_CITY, address.city);
        browser.setInputText(BILLING_STATE, address.state);
        browser.setInputText(BILLING_ZIP, address.zipcode);
        browser.select(BILLING_COUNTRY, address.country);
    }

}
