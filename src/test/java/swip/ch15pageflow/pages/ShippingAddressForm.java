package swip.ch15pageflow.pages;

import swip.ch15pageflow.domain.Address;
import swip.ch15pageflow.domain.Countries;
import swip.ch15pageflow.domain.UnitedStates;
import swip.ch15pageflow.framework.Browser;

import static swip.ch15pageflow.locators.BookStoreId.*;

public class ShippingAddressForm {

    private Browser browser;

    public ShippingAddressForm(Browser browser) {
        this.browser = browser;
    }

    public void setShippingAddress(Address address) {
        browser.setInputText(SHIPPING_FIRST_NAME, address.firstName);
        browser.setInputText(SHIPPING_LAST_NAME, address.lastName);
        browser.setInputText(SHIPPING_ADDRESS1, address.street1);
        browser.setInputText(SHIPPING_ADDRESS2, address.street2);
        browser.setInputText(SHIPPING_CITY, address.city);
        browser.setInputText(SHIPPING_STATE, address.state.toString());
        browser.setInputText(SHIPPING_ZIP, address.zipcode);
        browser.select(SHIPPING_COUNTRY, address.country.toString());
    }

    public Address getShippingAddress() {
        return new Address(
                browser.getInputText(SHIPPING_ADDRESS1),
                browser.getInputText(SHIPPING_ADDRESS2),
                browser.getInputText(SHIPPING_CITY),
                browser.getInputText(SHIPPING_ZIP),
                UnitedStates.fromString(browser.getInputText(SHIPPING_STATE)),
                Countries.fromString(browser.getInputText(SHIPPING_COUNTRY)),
                browser.getInputText(SHIPPING_FIRST_NAME),
                browser.getInputText(SHIPPING_LAST_NAME));
    }

}
