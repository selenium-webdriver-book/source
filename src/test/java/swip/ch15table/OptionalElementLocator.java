package swip.ch14table;

import org.openqa.selenium.By;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalElementLocator<T extends SearchScope>
        extends Locators<T, Optional<Element>> {

    public OptionalElementLocator(Supplier<By> selector) {
        super((T where) ->
                        where.optionalElement(selector)
        );
    }
}
