package swb.ch15pageflow.domain;

import swb.framework.domain.DomainBase;

public class OtherInformation extends DomainBase {
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
}