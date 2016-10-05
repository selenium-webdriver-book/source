package swb.ch16table.v2;

import swb.framework.domain.DomainBase;

import java.util.ArrayList;
import java.util.List;

public class AbstractTableContents extends DomainBase {

    private final List<String> headers;

    public AbstractTableContents(List<String> headers) {
        this.headers = headers;
    }

    public String diffHeaders(AbstractTableContents other) {
        StringBuilder diff = new StringBuilder();
        if (!headers.equals(other.headers)) {
            diff.append("headers differ ")
                .append(headers).append(" vs ")
                .append(other.headers).append("\n");
        }
        return diff.toString();
    }

    public <T> String diff(List<T> rows1, List<T> rows2, String s) {
        List<T> diff = new ArrayList<>(rows1);
        diff.removeAll(rows2);

        return diff.isEmpty() ? "" : s + diff + "\n";
    }
}
