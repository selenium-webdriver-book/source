package swip.ch15table.person;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class PersonTableContents {

    private final List<String> headers;
    private final List<Person> rows;

    public PersonTableContents(List<String> headers, List<Person> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PersonTableContents) {
            PersonTableContents actual = (PersonTableContents) other;

            return headers.equals(actual.headers) && this.rows.equals(actual.rows);
        } else {
            return false;
        }
    }

    public String describeDiff(PersonTableContents other) {
        String diff = "";
        if (!headers.equals(other.headers)) {
            diff += "headers differ " + headers + " vs " + other.headers + "\n";
        }
        List<Person> missingRows = new ArrayList<>(rows);
        missingRows.removeAll(other.rows);
        List<Person> unexpectedRows = new ArrayList<>(other.rows);
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
