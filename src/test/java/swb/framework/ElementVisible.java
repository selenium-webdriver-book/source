package swb.framework;

import org.openqa.selenium.By;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ElementVisible implements Predicate<SearchScope> {

    private final Supplier<By> by;

    public ElementVisible(Supplier<By> by) {
        this.by = by;
    }

    @Override
    public boolean test(SearchScope searchScope) {
        Optional<Element> element = searchScope.optionalElement(by);
        return element.isPresent() && element.get().isDisplayed();
    }
}
