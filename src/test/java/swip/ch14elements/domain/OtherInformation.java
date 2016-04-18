package swip.ch14elements.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}