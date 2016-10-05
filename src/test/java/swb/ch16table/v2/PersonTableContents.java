package swb.ch16table.v2;

import swb.ch16table.domain.Person;

import java.util.List;

public class PersonTableContents extends AbstractTableContents {

    private final List<String> headers;
    private final List<Person> rows;

    public PersonTableContents(List<String> headers, List<Person> rows) {
        super(headers);
        this.headers = headers;
        this.rows = rows;
    }

    public String describeDiff(PersonTableContents other) {
        return diffHeaders(other)
            + diff(this.rows, other.rows, "expected rows not found: ")
            + diff(other.rows, this.rows, "unexpected rows appeared: ");
    }

}
