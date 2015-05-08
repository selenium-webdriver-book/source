package swip.ch02locatingelements;

import com.google.common.base.Supplier;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class WebElementByFactoryFinder implements WebElementFinder<SearchContext, WebElement> {
    private final Supplier<By> byFactory;

    public WebElementByFactoryFinder(Supplier<By> byFactory) {
        this.byFactory = byFactory;
    }

    @Override
    public WebElement apply(SearchContext context) {
        return byFactory.get().findElement(context);
    }
}
