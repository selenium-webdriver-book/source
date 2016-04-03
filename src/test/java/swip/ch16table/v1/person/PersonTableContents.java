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
