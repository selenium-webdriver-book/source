package swip.ch15table;


import java.util.ArrayList;
import java.util.List;

public class TableContents<T> {

    // use list rather than set, element in table are ordered
    private final List<String> headers;
    private final List<T> rows;

    public TableContents(List<String> headers, List<T> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TableContents) {
            TableContents<T> actual = (TableContents<T>) other;

            // simplified comparison and kept to equals contract
            return headers.equals(actual.headers) && this.rows.equals(actual.rows);
        } else {
            return false;
        }
    }

    // single method to describe differences
    public String describeDiff(TableContents<T> other) {
        String diff = "";
        if (!headers.equals(other.headers)) {
            diff += "headers differ " + headers + " vs " + other.headers + "\n";
        }
        List<T> missingRows = new ArrayList<>(rows);
        missingRows.removeAll(other.rows);
        List<T> unexpectedRows = new ArrayList<>(other.rows);
        unexpectedRows.removeAll(rows);

        if (!unexpectedRows.isEmpty()) {
            diff += "unexpected rows appeared: " + unexpectedRows + "\n";
        }
        if (!missingRows.isEmpty()) {
            diff += "expected rows not found: " + missingRows + "\n";
        }
        return diff.trim();
    }
}
