package swip.ch15pageflow.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

interface SearchScope {

    Element findElement(Supplier<By> by);

    Stream<Element> findElements(Supplier<By> by);

    default Optional<Element> optionalElement(Supplier<By> by) {
        try {
            return Optional.of(findElement(by));
        } catch (NoSuchElementException ignored) {
            return Optional.empty();
        }
    }
}
