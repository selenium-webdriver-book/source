package swip.le;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

@FunctionalInterface
public interface WebElementFinder<C extends SearchContext, E extends WebElement>
        extends Function<C, E> {
}
