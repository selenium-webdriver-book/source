package swip.ch16table.v1;


import swip.ch16table.domain.City;
import swip.ch16table.domain.DomainBase;

import java.util.ArrayList;
import java.util.List;

public class CityTableContents extends DomainBase {

    private final List<String> headers;
    private final List<City> rows;

    public CityTableContents(List<String> headers, List<City> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    public String describeDiff(CityTableContents other) {
        StringBuilder diff = new StringBuilder();
        if (!headers.equals(other.headers)) {
            diff.append("headers differ ")
                .append(headers).append(" vs ")
                .append(other.headers).append("\n");
        }

        diff.append(diff(this.rows, other.rows, "expected rows not found: "));
        diff.append(diff(other.rows, this.rows, "unexpected rows appeared: "));
        return diff.toString();
    }

    private <T> String diff(List<T> rows1, List<T> rows2, String s) {
        List<T> diff = new ArrayList<>(rows1);
        diff.removeAll(rows2);

        return diff.isEmpty() ? "" : s + diff + "\n";
    }
}
