package swb.ch14elements.pages;

import swb.framework.Browser;

import static swb.locators.Id.*;

public class NaiveShoppingCartPage {

    private final Browser browser;

    public NaiveShoppingCartPage(Browser browser) {
        this.browser = browser;
    }

    public void setCoupon(String coupon) {
        browser.setInputText(COUPON_CODE, coupon);
    }

    public String getCoupon() {
        return browser.getInputText(COUPON_CODE);
    }

    public void setEmail(String email) {
        browser.setInputText(BILLING_EMAIL, email);
    }

    public void setSendOrderMessages(boolean value) {
        browser.setCheckboxValue(CONFIRM_EMAIL, value);
    }

    public void setRating(boolean rating) {
        browser.setCheckboxValue(RATINGS, rating);
    }

    public void setComment(String comments) {
        browser.setInputText(COMMENTS, comments);
    }

    public String getComment() {
        return browser.getInputText(COMMENTS);
    }

    public String getEmail() {
        return browser.getInputText(BILLING_EMAIL);
    }

    public boolean isSendRatingEmail() {
        return browser.isChecked(RATINGS);
    }

    public boolean isSendOrderMessages() {
        return browser.isChecked(CONFIRM_EMAIL);
    }
}