package swb.bookstore.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Cart {

    private final Map<Book, Integer> books = new HashMap<>();

    public void add(Book book) {
        books.put(book, 1);
    }

    public List<Item> getItems() {
        return books.entrySet().stream()
                .map(e -> new Item(
                        e.getKey().getTitle(),
                        e.getKey().getPrice(),
                        e.getValue(),
                        e.getKey().getPrice().multiply(BigDecimal.valueOf(e.getValue()))
                ))
                .collect(Collectors.toList());
    }

    public BigDecimal getTotal() {
        return books.entrySet().stream()
                .map(e -> e.getKey().getPrice().multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
