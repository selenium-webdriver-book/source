package swip.ch13framework.v3_nonjava8;

import org.openqa.selenium.By;

public interface ExplicitWait {
    Element findElement(By by); // <1>

    Element untilFound(By by);
}
