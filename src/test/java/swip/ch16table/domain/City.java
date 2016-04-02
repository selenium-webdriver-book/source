package swip.ch16table.domain;

public class City extends DomainBase{
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
}