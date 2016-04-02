package swip.ch16table.v2.table;

import swip.ch15pageflow.v2.framework.Element;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static swip.ch15pageflow.locators.TagName.*;

public class Table<T> {

    private final Element table;
    private final Function<List<Element>, T> rowMapper;

    public Table(Element table, Function<List<Element>, T> rowMapper) {
        this.table = table;
        this.rowMapper = rowMapper;
    }

    private List<String> getHeaders() {
        return table.findElements(TH).map(Element::getText).collect(toList());
    }

    private List<T> getRows() {
        return table.untilFound(TBODY).findElements(TR)
            .map((tr) ->
                rowMapper.apply(tr.findElements(TD)
                    .collect(toList()))
            ).collect(toList());
    }

    public TableContents<T> getContents() {
        return new TableContents<>(getHeaders(), getRows());
    }
}