package swip.ch16table.v1.person;


import swip.ch16table.domain.DomainBase;
import swip.ch16table.domain.Person;

import java.util.ArrayList;
import java.util.List;

class PersonTableContents extends DomainBase {

    private final List<String> headers;
    private final List<Person> rows;

    PersonTableContents(List<String> headers, List<Person> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    public String describeDiff(PersonTableContents other) {
        String diff = "";
        if (!headers.equals(other.headers)) {
            diff += "headers differ " + headers + " vs " + other.headers + "\n";
        }

        diff += diff(this.rows, other.rows, "expected rows not found: ");
        diff += diff(other.rows, this.rows, "unexpected rows appeared: ");
        return diff.trim();
    }

    private String diff(List<Person> rows1, List<Person> rows2, String s) {
        List<Person> diff = new ArrayList<>(rows1);
        diff.removeAll(rows2);

        return diff.isEmpty() ? "" : s + diff + "\n";
    }
}
