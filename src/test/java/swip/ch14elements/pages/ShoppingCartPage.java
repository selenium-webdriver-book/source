package swip.ch14elements.pages;

import swip.ch14elements.domain.OtherInformation;
import swip.ch14elements.framework.v5.Browser;

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