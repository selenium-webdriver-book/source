package swip.ch14table;


import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class SetDiff<T> {

    private String diff;
    private final Set<T> expected;
    private final Set<T> actual;

    public SetDiff(String info, Set<T> expected, Set<T> actual) {
        this.diff = info;
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public String toString() {
        if (actual.size() > 0) {
            diff += "\nactual results,\n" + actual + "\n";
        }
        Set<T> onlyInExpected = newHashSet(expected);
        onlyInExpected.removeAll(actual);
        actual.removeAll(expected);

        if (actual.size() > 0) {
            diff += "\nunexpected results appeared in actual,\n" +
                    actual + "\n";
        }
        if (onlyInExpected.size() > 0) {
            diff += "\nexpected results not found in actual,\n" +
                    onlyInExpected + "\n";
        }
        return diff;
    }
}

