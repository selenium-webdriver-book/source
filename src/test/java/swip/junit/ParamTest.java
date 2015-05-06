package swip.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParamTest {


    private final int i;

    public ParamTest(int i) {
        this.i = i;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0},
                {1}
        });

    }

    @Test
    public void testName() throws Exception {


    }
}
