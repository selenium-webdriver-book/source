package swip.ch13framework.v6;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Optional;

public interface SearchScope {

    Element findElement(By by);

    default Optional<Element> optionalElement(By by) {
        try {
            return Optional.of(findElement(by));
        } catch (NoSuchElementException ignored) {
            return Optional.empty();
        }
    }
}