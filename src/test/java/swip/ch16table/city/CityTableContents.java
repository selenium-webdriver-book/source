package swip.ch16table.city;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class CityTableContents {

    private final List<String> headers;
    private final List<City> rows;

    public CityTableContents(List<String> headers, List<City> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof CityTableContents) {
            CityTableContents actual = (CityTableContents) other;

            return headers.equals(actual.headers) && this.rows.equals(actual.rows);
        } else {
            return false;
        }
    }

    public String describeDiff(CityTableContents other) {
        String diff = "";
        if (!headers.equals(other.headers)) {
            diff += "headers differ " + headers + " vs " + other.headers + "\n";
        }
        List<City> missingRows = new ArrayList<>(rows);
        missingRows.removeAll(other.rows);
        List<City> unexpectedRows = new ArrayList<>(other.rows);
        unexpectedRows.removeAll(rows);

        if (!unexpectedRows.isEmpty()) {
            diff += "unexpected rows appeared: " + unexpectedRows + "\n";
        }
        if (!missingRows.isEmpty()) {
            diff += "expected rows not found: " + missingRows + "\n";
        }
        return diff.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
