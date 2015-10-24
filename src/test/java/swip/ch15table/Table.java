package swip.ch15table;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static swip.ch15table.Locators.elements;

public class Table<T, Where extends SearchScope> {


    private final Where where;
    private final Locator<Where, Element> locator;
    private final Locator<Stream<Element>, T> mapper;

    public Table(Where where,
                 Locator<Where, Element> locator,
                 Locator<Stream<Element>, T> mapper) {
        this.where = where;
        this.locator = locator;
        this.mapper = mapper;
    }

    public Stream<String> getHeader() {
        return locator.andThen(elements(TagName.TH)).locate(where).map(Element::getText);
    }

    public Stream<T> getRows() {
        return locator.andThen(elements(TagName.TR)).locate(where)
                .filter(e ->
                        Locators.<Element>optionalElement(TagName.TD)
                                .locate(e)
                                .isPresent())
                .map(elements(TagName.TD))
                .map(mapper);
    }

    public TableContents<T> getContents() {
        return new TableContents<T>(
                this.getHeader().collect(toSet()),
                this.getRows().collect(Collectors.<T>toSet())
        );
    }
}

