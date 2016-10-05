package swb.ch14elements.pages;

import swb.ch14elements.domain.OtherInformation;
import swb.framework.Browser;

public class ShoppingCartPage {

    private final OtherInformationForm otherInformationForm;

    public ShoppingCartPage(Browser browser) {
        this.otherInformationForm = new OtherInformationForm(browser);
    }

    public OtherInformation getOtherInformation() {
        return otherInformationForm.getOtherInformation();
    }

    public void setOtherInformation(OtherInformation otherInformation) {
        otherInformationForm.setOtherInformation(otherInformation);
    }
}