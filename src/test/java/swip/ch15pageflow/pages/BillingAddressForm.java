package swip.ch15pageflow.pages;


import swip.ch15pageflow.domain.UnitedStates;
import swip.ch15pageflow.domain.Address;
import swip.ch15pageflow.domain.Countries;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.locators.BookStoreId;

import static swip.ch15pageflow.locators.BookStoreId.*;


public class BillingAddressForm  {


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
        browser.setInputText(BILLING_STATE, address.state.toString()) ;
        browser.setInputText(BILLING_ZIP, address.zipcode);
        browser.setInputText(BILLING_COUNTRY, address.country.name().replace('_', ' '));
    }

    public Address getBillingAddress() {
        return new Address(
                browser.getInputText(BILLING_ADDRESS1),
                browser.getInputText(BILLING_ADDRESS2),
                browser.getInputText(BILLING_CITY),
                browser.getInputText(BILLING_ZIP),
                UnitedStates.fromString(browser.getInputText(BILLING_STATE)),
                Countries.fromString(browser.getInputText(BILLING_COUNTRY)),
                browser.getInputText(BILLING_FIRST_NAME),
                browser.getInputText(BILLING_LAST_NAME));
    }

}
