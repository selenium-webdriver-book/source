package swip.ch14elements.domain;

public enum MailingOption {

    WEEKLY_NEWSLETTER("Weekly newsletter--" +
            "New books, updates, news, and special offers"),
    DEAL_OF_THE_DAY("Deal of the Day--" +
            "These amazing special offers last just 24 hours!"),
    BOTH("Both"),
    NO_PROMOTION_MAILERS("No promotional mailers. " +
            "I will still receive updates on my MEAPs and other books."),
    KEEP_ME("Keep me on the lists I'm already on.");

    private final String string;

    MailingOption(String string) {
        this.string = string;
    }

    public static MailingOption from(String string) {
        for (MailingOption o : values()) {
            if (o.string.equals(string)) {
                return o;
            }
        }
        throw new IllegalArgumentException(
            "Can't find an enum with this string " + string);
    }
}