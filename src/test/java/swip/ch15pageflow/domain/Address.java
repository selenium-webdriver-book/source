package swip.ch15pageflow.domain;

public class Address {
    public final String firstName;
    public final String lastName;
    public final String street1;
    public final String street2;
    public final String city;
    public final UnitedStates state;
    public final String zipcode;
    public final Countries country;

    public Address(String street1,
                   String street2,
                   String city,
                   String zipcode,
                   UnitedStates state,
                   Countries country,
                   String firstName,
                   String lastName) {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
