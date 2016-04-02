package swip.ch16table.v1.city;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
        String diff = "";
        if (!headers.equals(other.headers)) {
            diff += "headers differ " + headers + " vs " + other.headers + "\n";
        }

        diff += diff(this.rows, other.rows, "expected rows not found: ");
        diff += diff(other.rows, this.rows, "unexpected rows appeared: ");
        return diff.trim();
    }

    private String diff(List<City> rows1, List<City> rows2, String s) {
        List<City> diff = new ArrayList<>(rows1);
        diff.removeAll(rows2);

        return diff.isEmpty() ? "" : s + diff + "\n";
    }
}
