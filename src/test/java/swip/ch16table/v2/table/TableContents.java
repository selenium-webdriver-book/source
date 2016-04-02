package swip.ch16table.v2.table;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class TableContents<T> {

    private final List<String> headers;
    private final List<T> rows;

    public TableContents(List<String> headers, List<T> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public String describeDiff(TableContents<T> other) {
        String diff = "";
        if (!headers.equals(other.headers)) {
            diff += "headers differ " + headers + " vs " + other.headers + "\n";
        }

        diff += diff(this.rows, other.rows, "expected rows not found: ");
        diff += diff(other.rows, this.rows, "unexpected rows appeared: ");
        return diff.trim();
    }

    private String diff(List<T> rows1, List<T> rows2, String s) {
        List<T> diff = new ArrayList<>(rows1);
        diff.removeAll(rows2);

        return diff.isEmpty() ? "" : s + diff + "\n";
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
