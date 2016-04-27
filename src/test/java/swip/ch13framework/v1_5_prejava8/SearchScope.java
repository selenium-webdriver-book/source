package swip.ch13framework.v1_5_prejava8;

import org.openqa.selenium.By;
import swip.ch13framework.v2.Element;

import java.util.Optional;
import java.util.function.Supplier;

public interface SearchScope {
    Element findElement(Supplier<By> by);
    Optional<Element> optionalElement(Supplier<By> by);
}
