package swb.bookstore.domain;

import java.math.BigDecimal;

public class Book {
    private final String id;
    private final String title;
    private final String authors;
    private final String description;
    private final BigDecimal price;

    public Book(String id, String title, String authors, String description, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
