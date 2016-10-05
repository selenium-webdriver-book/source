package swb.ch12wrapping.v0_7;

import org.openqa.selenium.By;

public enum ShoppingCartBySupplier {
    QUANTITY(By.name("cartDS.shoppingcart_ROW0_m_orderItemVector_ROW0_m_quantity"));

    private final By by;

    ShoppingCartBySupplier(By by) {
        this.by = by;
    }

    public By get() {
        return by;
    }
}
