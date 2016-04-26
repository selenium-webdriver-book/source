package swip.ch13framework.v1_5_nonjava8;

import org.openqa.selenium.By;
import swip.ch13framework.v2.Element;

import java.util.Optional;

public interface SearchScope {
    Element findElement(By by);
    Optional<Element> optionalElement(By by);
}
