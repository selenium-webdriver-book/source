package swip.ch14elements.domain;

public class OtherInformation {
    public final String couponCode;
    public final String email;
    public final boolean sendOrdersToEmail;
    public final boolean sendRatingEmail;
    public final MailingOption mailingOption;
    public final String comments;

    public OtherInformation(String couponCode,
                            String email,
                            boolean sendOrdersToEmail,
                            boolean sendRatingEmail,
                            MailingOption mailingOption,
                            String comments) {
        this.couponCode = couponCode;
        this.email = email;
        this.sendOrdersToEmail = sendOrdersToEmail;
        this.sendRatingEmail = sendRatingEmail;
        this.mailingOption = mailingOption;
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OtherInformation that = (OtherInformation) o;

        if (sendOrdersToEmail != that.sendOrdersToEmail) return false;
        if (sendRatingEmail != that.sendRatingEmail) return false;
        if (couponCode != null ? !couponCode.equals(that.couponCode) : that.couponCode != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (mailingOption != that.mailingOption) return false;
        return !(comments != null ? !comments.equals(that.comments) : that.comments != null);

    }

    @Override
    public int hashCode() {
        int result = couponCode != null ? couponCode.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (sendOrdersToEmail ? 1 : 0);
        result = 31 * result + (sendRatingEmail ? 1 : 0);
        result = 31 * result + (mailingOption != null ? mailingOption.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}