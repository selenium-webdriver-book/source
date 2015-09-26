package swip.ch05pageobjects.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchForm {
    @FindBy(name = "q")
    private WebElement query;
    @FindBy(css = "input[type='submit']")
    private WebElement submit;

    public void searchFor(String query) {
        this.query.sendKeys(query);
        this.submit.click();
    }
}
