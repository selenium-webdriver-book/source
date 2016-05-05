package swip.ch16table.v2;

import swip.ch16table.domain.City;

import java.util.List;

public class CityTableContents extends AbstractTableContents {

    private final List<City> rows;

    public CityTableContents(List<String> headers, List<City> rows) {
        super(headers);
        this.rows = rows;
    }

    public String describeDiff(CityTableContents other) {
        return diffHeaders(other)
            + diff(this.rows, other.rows, "expected rows not found: ")
            + diff(other.rows, this.rows, "unexpected rows appeared: ");
    }
}
