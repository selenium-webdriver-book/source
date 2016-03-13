package swip.ch13framework.v6_nonjava8;

import org.openqa.selenium.By;

public interface ExplicitWait extends SearchScope {

    Element untilFound(By by);
}
