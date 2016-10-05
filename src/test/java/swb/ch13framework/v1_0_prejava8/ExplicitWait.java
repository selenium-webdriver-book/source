package swb.ch13framework.v1_0_prejava8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public interface ExplicitWait {
    WebElement findElement(Supplier<By> by); // <1>
    WebElement await(Supplier<By> by);
}
