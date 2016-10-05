package swb.bookstore.domain;

import java.math.BigDecimal;

public class Item {
    private final String title;
    private final BigDecimal price;
    private final int quantity;
    private final BigDecimal total;

    public Item(String title, BigDecimal price, int quantity, BigDecimal total) {

        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
