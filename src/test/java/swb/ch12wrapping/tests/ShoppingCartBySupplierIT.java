package swb.ch12wrapping.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swb.ch12wrapping.v0_7.ShoppingCartBySupplier;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class ShoppingCartBySupplierIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/shopping-cart.html");
    }

    @Test
    public void bySupplierPattern() throws Exception {

        assertEquals("1",
                driver.findElement(ShoppingCartBySupplier.QUANTITY.get())
                        .getAttribute("value"));

    }
}
