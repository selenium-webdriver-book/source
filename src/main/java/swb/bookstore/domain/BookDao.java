package swb.bookstore.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDao {

    private final List<Book> books = Collections.singletonList(
            new Book(
                    "selenium-webdriver-book",
                    "Selenium WebDriver Book",
                    "Yujun Liang and Alex Collins",
                    "Selenium WebDriver Book is a hands-on guide to dozens of specific techniques you can use to get the most of WebDriver in your test automation development. Following a cookbook-style Problem/Solution/Discussion format, this practical handbook gives you instantly-useful solutions for important areas like interacting with and testing web applications and using the WebDriver APIs. As you read, you'll graduate from WebDriver fundamentals to must-have practices ranging from how to interact with, control and verify web pages and exception handling, to more complex interactions like page objects, alerts, and JavaScript, as well as integrating with Continuous Integration tools, mobile testing, and much more. By the end of the book, you’ll be confident and skilled at testing your web applications with WebDriver.",
                    new BigDecimal("44.00"))
    );

    public Book findBook(String id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().get();
    }

    public List<Book> findBooks(String query) {
        return books.stream().filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }
}
