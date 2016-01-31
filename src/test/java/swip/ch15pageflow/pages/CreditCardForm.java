package swip.ch15pageflow.pages;


import swip.ch15pageflow.domain.CreditCard;
import swip.ch15pageflow.domain.CreditCardType;
import swip.ch15pageflow.framework.Browser;

import java.time.Month;

import static swip.ch15pageflow.locators.BookStoreId.*;

public class CreditCardForm {

    private Browser browser;

    public CreditCardForm(Browser browser) {
        this.browser = browser;
    }

    public void setCreditCard(CreditCard card) {
//        browser.setInputText(CARD_CVV, card.cardCvv);
        browser.setInputText(CARD_NUMBER, card.cardNumber);
        browser.select(CARD_TYPE, card.cardType);
        browser.select(CARD_EXP_MONTH, card.expirationMonth);
        browser.select(CARD_EXP_YEAR, card.expirationYear);
    }

    public CreditCard getCreditCard() {
        return new CreditCard(
            CreditCardType.fromString(browser.getInputText(CARD_TYPE)),
            browser.getInputText(CARD_NUMBER),
            browser.getInputText(CARD_CVV),
            Month.valueOf(browser.getInputText(CARD_EXP_MONTH)),
            Integer.parseInt(browser.getInputText(CARD_EXP_YEAR)));

    }

}

