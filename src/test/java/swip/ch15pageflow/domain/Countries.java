package swip.ch15pageflow.domain;


public enum Countries {
    Australia,
    China,
    India,
    United_Kindon,
    United_States;

    @Override
    public String toString() {
        return this.name().replace('_', ' ');
    }

    public static Countries fromString(String string) {
        return valueOf(string.replace(' ', '_'));
    }

}
