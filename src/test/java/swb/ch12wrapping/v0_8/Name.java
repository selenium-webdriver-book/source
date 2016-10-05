package swb.ch12wrapping.v0_8;

import org.openqa.selenium.By;

import java.util.function.Supplier;

public enum Name implements Supplier<By> {
    QUANTITY("cartDS.shoppingcart_ROW0_m_orderItemVector_ROW0_m_quantity");

    private final By by;

    Name(String by) {
        this.by = By.name(by);
    }

    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}
