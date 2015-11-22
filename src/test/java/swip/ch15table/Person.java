package swip.ch15table;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Person {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int points;

    public Person(int id, String firstName, String lastName, int points) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
    }

    @Override
    public String toString() {
        return "new Person(\"" + id +
                "\",\"" + firstName + "\",\"" +
                lastName + "\"," + points + ")\n";
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}