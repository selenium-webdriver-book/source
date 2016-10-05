package swb.bookstore.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class CheckoutController {

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String addToCart(@Valid CheckoutRequest checkoutRequest, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("request", checkoutRequest);
            return "cart";
        }

        model.addAttribute("orderNumber", String.format("%08d", System.currentTimeMillis() / 1000000));

        return "checkout";
    }
}
