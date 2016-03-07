package swip.ch15pageflow.domain;

public enum UnitedStates {
    Alabama("AL"),
    Montana("MT"),
    Alaska("AK"),
    Nebraska("NE"),
    Arizona("AZ"),
    Nevada("NV"),
    Arkansas("AR"),
    New_Hampshire("NH"),
    California("CA"),
    New_Jersey("NJ"),
    Colorado("CO"),
    New_Mexico("NM"),
    Connecticut("CT"),
    New_York("NY"),
    Delaware("DE"),
    North_Carolina("NC"),
    Florida("FL"),
    North_Dakota("ND"),
    Georgia("GA"),
    Ohio("OH"),
    Hawaii("HI"),
    Oklahoma("OK"),
    Idaho("ID"),
    Oregon("OR"),
    Illinois("IL"),
    Pennsylvania("PA"),
    Indiana("IN"),
    Rhode_Island("RI"),
    Iowa("IA"),
    South_Carolina("SC"),
    Kansas("KS"),
    South_Dakota("SD"),
    Kentucky("KY"),
    Tennessee("TN"),
    Louisiana("LA"),
    Texas("TX"),
    Maine("ME"),
    Utah("UT"),
    Maryland("MD"),
    Vermont("VT"),
    Massachusetts("MA"),
    Virginia("VA"),
    Michigan("MI"),
    Washington("WA"),
    Minnesota("MN"),
    West_Virginia("WV"),
    Mississippi("MS"),
    Wisconsin("WI"),
    Missouri("MO"),
    Wyoming("WY");

    private final String value;

    UnitedStates(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.name().replace(' ', ' ');
    }

    public static UnitedStates fromString(String string) {
        return valueOf(string.replace(' ', '_'));
    }

}