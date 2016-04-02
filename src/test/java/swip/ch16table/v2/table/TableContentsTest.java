package swip.ch16table.v2.table;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TableContentsTest {

    private final TableContents<String> expected, actual;
    private final boolean equal;
    private final String diff;

    public TableContentsTest(TableContents<String> expected, TableContents<String> actual, boolean equal, String diff) {
        this.expected = expected;
        this.actual = actual;
        this.equal = equal;
        this.diff = diff;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                datum(new TableContents<>(list(), list()), new TableContents<>(list(), list()), true, ""),
                datum(new TableContents<>(list("x"), list("x")), new TableContents<>(list("x"), list("x")), true, ""),
                datum(new TableContents<>(list("x"), list("x")), new TableContents<>(list("x"), list()), false, "expected rows not found: [x]"),
                datum(new TableContents<>(list("x"), list()), new TableContents<>(list("x"), list("x")), false, "unexpected rows appeared: [x]"),
                datum(new TableContents<>(list("x"), list("x")), new TableContents<>(list(""), list("x")), false, "headers differ [x] vs []")
        );
    }

    private static Object[] datum(TableContents<String> a, TableContents<String> b, boolean equal, String diff) {
        return new Object[]{a, b, equal, diff};
    }

    private static List<String> list(String... items) {
        return Arrays.asList(items);
    }

    @Test
    public void equality() throws Exception {
        assertEquals(equal, expected.equals(actual));
    }

    @Test
    public void diffDescription() throws Exception {
        assertEquals(diff, expected.describeDiff(actual));
    }
}