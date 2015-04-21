package swip.le;

import com.google.common.base.Supplier;
import org.openqa.selenium.By;

public enum ShoppingCartBySupplier implements Supplier<By> {
    QUANTITY(By.name("cartDS.shoppingcart_ROW0_m_orderItemVector_ROW0_m_quantity"));

    private final By by;

    ShoppingCartBySupplier(By by) {
        this.by = by;
    }

    @Override
    public By get() {
        return by;
    }
}
