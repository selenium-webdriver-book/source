package swip.framework;

import org.openqa.selenium.By;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ElementNotVisible implements Predicate<SearchScope> {

    private final Supplier<By> by;

    public ElementNotVisible(Supplier<By> by) {
        this.by = by;
    }

    @Override
    public boolean test(SearchScope explicitWait) {
        Optional<Element> element = explicitWait.optionalElement(by);
        return !element.isPresent() || !element.get().isDisplayed();
    }
}
