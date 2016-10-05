package swb.locators;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

public enum ClassName implements Supplier<By> {  //<5>

    CART_BUTTON("cart-button"),         //<1>
    SEARCH_BUTTON("btn-default"),      // <2>
    ORDER_NUMBER("order-number");

    private final By by;

    ClassName(String id) {
        this.by = className(id); // <3>
    }

    @Override
    public By get() {
        return by;     //<4>
    }

    @Override
    public String toString() {
        return by.toString();
    }
}