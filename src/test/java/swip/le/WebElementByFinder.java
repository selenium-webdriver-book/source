package swip.le;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class WebElementByFinder implements WebElementFinder<SearchContext, WebElement> {
    private final By by;

    public WebElementByFinder(By by) {
        this.by = by;
    }

    @Override
    public WebElement apply(SearchContext context) {
        return by.findElement(context);
    }
}
