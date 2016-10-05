package swb.bookstore.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import swb.bookstore.domain.BookDao;
import swb.bookstore.domain.Cart;

@Scope("session")
@Controller
public class CartController {

    @Autowired private BookDao bookDao;
    @Autowired private Cart cart;

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String addToCart(@RequestParam("book") String book) {
        cart.add(bookDao.findBook(book));
        return "redirect:books/" + book;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getCart(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }
}
