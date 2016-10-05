package swb.ch15pageflow.domain;

public enum CreditCardType {

    American_Express,
    JCB,
    MasterCard,
    Visa,
    Discover;

    @Override
    public String toString() {
        return this.name().replace(' ', ' ');
    }

    public static CreditCardType fromString(String string) {
        return valueOf(string.replace(' ', '_'));
    }

}
