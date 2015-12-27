package swip.bookstore.app;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CheckoutRequest {
    @NotNull
    @Size(min = 14, max = 19)
    private String cardNumber;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
