package swip.ch15table;


import org.openqa.selenium.By;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Locators<T1 extends SearchScope, T2>
        implements Locator<T1, T2> {

    private final Locator<T1, T2> locator;

    public Locators(Locator<T1, T2> locator) {
        this.locator = locator;
    }

    public static <T extends SearchScope> Locator<T, Element> element(Supplier<By> selector) {
        return new ElementLocator<>(selector);
    }

    public static <T extends SearchScope> Locator<T, Stream<Element>> elements(Supplier<By> selector) {
        return new ElementsLocator<>(selector);
    }

    public static <T extends SearchScope> Locator<T, Optional<Element>> optionalElement(Supplier<By> selector) {
        return new OptionalElementLocator<>(selector);
    }

    @Override
    public T2 locate(T1 where) {
        return locator.locate(where);
    }
}