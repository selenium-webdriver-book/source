package swip.ch05pageobjects.loadablepagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Path("/search.html")
@Verify(title = "Search")
public class SearchForm {

    @FindBy(css = "input[name='q']")
    private WebElement query;
    @FindBy(css = "input[type='submit']")
    private WebElement submit;

    public void searchFor(String text) {
        query.sendKeys(text);
        submit.click();
    }
}
