package swb.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface SearchScope extends SearchContext {

    default Element findElement(Supplier<By> by) {
        return new Element(findElement(by.get()));
    }

    default Stream<Element> findElements(Supplier<By> by) {
        return findElements(by.get()).stream().map(Element::new);
    }

    default Optional<Element> optionalElement(Supplier<By> by) {
        try {
            return Optional.of(findElement(by));
        } catch (NoSuchElementException ignored) {
            return Optional.empty();
        }
    }

    default boolean isPresent(Supplier<By> by) {
        return optionalElement(by).isPresent();
    }
}
