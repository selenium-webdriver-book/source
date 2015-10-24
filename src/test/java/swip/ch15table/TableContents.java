package swip.ch15table;


import java.util.Set;

public class TableContents<T> {

    private final Set<String> headers;
    private final Set<T> rows;
    private SetDiff<?> diff;

    public TableContents(Set<String> headers, Set<T> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        if (other instanceof TableContents) {
            TableContents<T> actual = (TableContents) other;

            if (!headers.equals(actual.headers)) {
                diff = new SetDiff<>("headers are different,",
                        headers,
                        actual.headers);
                return false;
            } else {
                if (!this.rows.equals(actual.rows)) {
                    diff = new SetDiff<>("rows are different,",
                            this.rows,
                            actual.rows);
                    return false;
                }
                return true;
            }
        } else {
            diff = new SetDiff<>("not a TableContents,", headers, null);
            return false;
        }
    }

    @Override
    public String toString() {
        return diff.toString();
    }
}
