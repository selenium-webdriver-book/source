package swip.ch16table.v2;

import swip.ch15pageflow.framework.Element;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static swip.ch15pageflow.locators.TagName.*;

public class Table<T> {

    private final Element table;
    private final Function<List<Element>, T> rowMapper;  //<1>

    public Table(Element table, Function<List<Element>, T> rowMapper) {
        this.table = table;
        this.rowMapper = rowMapper;
    }

    private List<String> getHeaders() {
        return table.findElements(TH) //<3>
            .map(Element::getText) //<4>
            .collect(Collectors.toList());    //<5>
    }

    private List<T> getRows() {           //<2>
        return table.untilFound(TBODY)  //<6>
            .findElements(TR)         //<7>
            .map(                   //<8>
                tr ->
                    rowMapper.apply(  //<9>
                        tr.findElements(TD)    //<10>
                            .collect(Collectors.toList()) //<11>
                    )
            ).collect(toList());   //<12>
    }

    public TableContents<T> getContents() {
        return new TableContents<>(getHeaders(), getRows());
    }
}