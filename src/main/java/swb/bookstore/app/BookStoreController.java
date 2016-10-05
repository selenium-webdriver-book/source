package swb.bookstore.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swb.bookstore.domain.Book;
import swb.bookstore.domain.BookDao;

import java.util.List;

@Controller
public class BookStoreController {

    @Autowired private BookDao bookDao;

    @RequestMapping("/")
    public String homePage() {
        return "index";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        model.addAttribute("query", query);
        List<Book> results = bookDao.findBooks(query);
        model.addAttribute("results", results);
        model.addAttribute("numResults", results.size());
        return "search";
    }

    @RequestMapping("/books/{book}")
    public String books(@PathVariable("book") String book, Model model) {
        model.addAttribute("book", bookDao.findBook(book));
        return "book";
    }
}
