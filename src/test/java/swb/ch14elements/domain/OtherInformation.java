package swb.ch14elements.domain;

import swb.framework.domain.DomainBase;

public class OtherInformation extends DomainBase {
    public final String couponCode;
    public final String email;
    public final boolean sendOrdersToEmail;
    public final boolean sendRatingEmail;
    public final String mailingOption;
    public final String comments;

    public OtherInformation(String couponCode,
                            String email,
                            boolean sendOrdersToEmail,
                            boolean sendRatingEmail,
                            String mailingOption,
                            String comments) {
        this.couponCode = couponCode;
        this.email = email;
        this.sendOrdersToEmail = sendOrdersToEmail;
        this.sendRatingEmail = sendRatingEmail;
        this.mailingOption = mailingOption;
        this.comments = comments;
    }
}