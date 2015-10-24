package swip.ch14elements;

import swip.ch14elements.framework.Browser;

public class ShoppingCartPage {

    private final OtherInformationForm otherInformationForm;

    public ShoppingCartPage(Browser browser) {
        this.otherInformationForm = new OtherInformationForm(browser);
    }

    public OtherInformation getOtherInformation() {
        return otherInformationForm.getOtherInformation();
    }

    public void setOtherInformation(OtherInformation otherInformation) throws Exception {
        otherInformationForm.setOtherInformation(otherInformation);
    }
}