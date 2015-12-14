package swip.ch16table.city;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class City {
    private final int id;
    private final String name;
    private final String stateName;

    public City(int id, String name, String stateName) {
        this.id = id;
        this.name = name;
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "new City(" + id +
                ",\"" + name + "\",\"" +
            stateName  + "\")\n";
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