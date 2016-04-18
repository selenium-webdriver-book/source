package swip.ch14elements.pages;

import org.openqa.selenium.By;
import swip.ch14elements.framework.v4.Browser;

public class NaiveShoppingCartPage {

    private final Browser browser;

    public NaiveShoppingCartPage(Browser browser) {
        this.browser = browser;
    }

    public void setCoupon(String coupon) {
        browser.setInputText(By.id("gc-redemption-code"), coupon);
    }

    public String getCoupon() {
        return browser.getInputText(By.id("gc-redemption-code"));
    }

    public void setEmail(String email) {
        browser.setInputText(By.id("billing-email"), email);
    }

    public void setSendOrderMessages(boolean value) {
        browser.setCheckboxValue(By.id("confirm-email"), value);
    }

    public void setRating(boolean rating) {
        browser.setCheckboxValue(By.id("ratings"), rating);
    }

    public void setComment(String comments) {
        browser.setInputText(By.id("comments"), comments);
    }

    public String getComment() {
        return browser.getInputText(By.id("comments"));
    }

    public String getEmail() {
        return browser.getInputText(By.id("billing-email"));
    }

    public boolean isSendRatingEmail() {
        return browser.isChecked(By.id("ratings"));
    }

    public boolean isSendOrderMessages() {
        return browser.isChecked(By.id("confirm-email"));
    }
}