package swip.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
public class WebElementByFactoryFinderIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/shopping-cart.html");
    }

    @Test
    public void byFactory() throws Exception {

        WebElement quantityInput = new WebElementByFactoryFinder(ShoppingCartBySupplier.QUANTITY)
                .apply(driver);

        assertEquals("1", quantityInput.getAttribute("value"));

    }
}
