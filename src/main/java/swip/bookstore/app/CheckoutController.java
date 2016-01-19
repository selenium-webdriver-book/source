package swip.bookstore.app;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class CheckoutController {

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String addToCart(@Valid CheckoutRequest checkoutRequest, BindingResult result) {

        if (result.hasErrors()) {
            return "cart";
        }

        return "checkout";
    }
}
