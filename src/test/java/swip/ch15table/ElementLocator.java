package swip.ch15table;


import org.openqa.selenium.By;

import java.util.function.Supplier;

public class ElementLocator<T extends SearchScope>
        extends Locators<T, Element> {

    public ElementLocator(Supplier<By> selector) {
        super((T where) -> where.findElement(selector));
    }
}
