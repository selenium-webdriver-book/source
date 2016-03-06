package swip.ch15pageflow.domain;

import java.time.Month;

public class CreditCard {

    public final String cardNumber;
    public final String cardCvv;
    public final String expirationMonth;
    public final int expirationYear;
    public final CreditCardType cardType;

    public CreditCard(CreditCardType cardType,
                      String cardNumber,
                      String cardCvv,
                      Month expirationMonth,
                      int expirationYear) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
        this.expirationMonth = expirationMonth.toString().charAt(0) +
            expirationMonth.toString().substring(1, 3).toLowerCase() +
            " (" + (expirationMonth.ordinal() + 1) + ")";
        this.expirationYear = expirationYear;
    }
}
