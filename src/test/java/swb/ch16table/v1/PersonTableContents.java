package swb.ch16table.v1;

import swb.framework.domain.DomainBase;
import swb.ch16table.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonTableContents extends DomainBase {

    private final List<String> headers;
    private final List<Person> rows;

    public PersonTableContents(List<String> headers, List<Person> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    public String describeDiff(PersonTableContents other) {
        return diffHeaders(other)
            + diff(this.rows, other.rows, "expected rows not found: ")
            + diff(other.rows, this.rows, "unexpected rows appeared: ");
    }

    public  String diffHeaders( PersonTableContents other) {
        StringBuilder diff = new StringBuilder();
        if (!headers.equals(other.headers)) {
            diff.append("headers differ ")
                .append(headers).append(" vs ")
                .append(other.headers).append("\n");
        }
        return diff.toString();
    }

    public  <T> String diff(List<T> rows1, List<T> rows2, String s) {
        List<T> diff = new ArrayList<>(rows1);
        diff.removeAll(rows2);

        return diff.isEmpty() ? "" : s + diff + "\n";
    }
}
