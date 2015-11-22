package swip.ch15table;


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
        if (other instanceof TableContents) {
            @SuppressWarnings("unchecked") TableContents<T> actual = (TableContents<T>) other;

            return headers.equals(actual.headers) && this.rows.equals(actual.rows);
        } else {
            return false;
        }
    }

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
