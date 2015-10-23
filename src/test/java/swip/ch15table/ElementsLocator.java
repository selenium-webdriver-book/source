package swip.ch14table;

import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ElementsLocator<T extends SearchScope>
        extends Locators<T, Stream<Element>> {

    public ElementsLocator(Supplier<By> selector) {
        super((T where)
                        -> where.findElements(selector)
        );
    }
}
