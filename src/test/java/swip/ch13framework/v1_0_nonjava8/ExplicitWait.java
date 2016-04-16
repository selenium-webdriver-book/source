package swip.ch13framework.v1_0_nonjava8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ExplicitWait {
    WebElement findElement(By by); // <1>

    WebElement untilFound(By by);
}
