package swb.locators;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

public enum Id implements Supplier<By> {

    BILLING_FIRST_NAME("billing-first-name"),
    BILLING_LAST_NAME("billing-last-name"),
    BILLING_ADDRESS1("billing-address1"),
    BILLING_ADDRESS2("billing-address2"),
    BILLING_CITY("billing-city"),
    BILLING_STATE("billing-state"),
    BILLING_COUNTRY("billing-country"),
    BILLING_ZIP("billing-zip"),
    BILLING_EMAIL("billing-email"),

    CARD_TYPE("card-type"),
    CARD_NUMBER("card-number"),
    CARD_EXP_MONTH("card-exp-month"),
    CARD_EXP_YEAR("card-exp-year"),

    SEARCH_INPUT("navbar-search"),
    RATINGS("ratings"),
    CONFIRM_EMAIL("confirm-email"),

    COUPON_CODE("gc-redemption-code"),
    COMMENTS("comments"),

    SECOND_NAVBAR("secondary-navbar"),
    TOP_NAV("primary-navbar"),
    ORDER_NUMBER_ID("orderNumber"),
    CARDNUMBER_ERROR("cardNumber.errors"),

    LOCATION("location"),
    LOGIN("login"),
    ;

    private final By by;

    Id(String id) {
        this.by = id(id);
    }

    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}