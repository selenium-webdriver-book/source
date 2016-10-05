package swb.bookstore.app;

import org.hibernate.validator.constraints.NotEmpty;
import swb.bookstore.infrastructure.mvc.ParamName;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class CheckoutRequest {
    @NotEmpty
    private String billingFirstName;
    @NotEmpty
    private String billingLastName;
    @NotEmpty
    private String billingAddress1;

    private String billingAddress2;
    @NotEmpty
    private String billingCity;
    @NotEmpty
    private String billingState;
    @NotEmpty
    private String billingZip;
    @NotEmpty
    @ParamName("shippingAddressDS.shipping_ROW0_country")
    private String billingCountry;
    private String ccType;
    @Size(min = 19, max = 19)
    @ParamName("cardNumber")
    private String cardNumber;
    @Min(1)
    @Max(12)
    @ParamName("ccPaymentDS.ccpayment_ROW0_expMonth")
    private int expiryMonth;
    @Min(2015)
    @Max(9999)
    @ParamName("ccPaymentDS.ccpayment_ROW0_expYear")
    private int expiryYear;

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public String getBillingAddress1() {
        return billingAddress1;
    }

    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZip() {
        return billingZip;
    }

    public void setBillingZip(String billingZip) {
        this.billingZip = billingZip;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }
}
