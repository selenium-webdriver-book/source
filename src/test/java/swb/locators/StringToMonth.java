package swb.locators;

import java.time.Month;
import java.util.function.Function;

public enum StringToMonth implements Function<String, Month> {

    TO_MONTH;

    public Month apply(String key) {

        Month month = null;
        try {
            month = Month.valueOf(key.toUpperCase());
        } catch (IllegalArgumentException e) {
            String newKey;
            if (key.contains(".")) {
                newKey = key.replace(".", "");
            } else {
                newKey = key;
            }
            for (Month m : Month.values()) {
                if (m.name().contains(newKey)) {
                    return m;
                }
            }
        }
        return month;
    }

}
