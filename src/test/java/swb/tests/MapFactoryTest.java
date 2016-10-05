package swb.tests;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import swb.framework.MapFactory;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

public class MapFactoryTest {

    private final Map<String, String> properties = new HashMap<>();
    private final MapFactory factory = new MapFactory();

    @Test
    public void empty() throws Exception {
        assertEquals(emptyMap(), factory.create(properties));
    }

    @Test
    public void singleValue() throws Exception {
        properties.put("foo", "bar");
        assertEquals(singletonMap("foo", "bar"), factory.create(properties));
    }

    @Test
    public void integer() throws Exception {
        properties.put("foo", "1");
        assertEquals(singletonMap("foo", 1), factory.create(properties));
    }

    @Test
    public void aDouble() throws Exception {
        properties.put("foo", "1.1");
        assertEquals(singletonMap("foo", 1.1), factory.create(properties));
    }

    @Test
    public void aBoolean() throws Exception {
        properties.put("foo", "true");
        assertEquals(singletonMap("foo", true), factory.create(properties));
    }


    @Test
    public void subMap() throws Exception {
        properties.put("foo.bar", "baz");
        assertEquals(singletonMap("foo", singletonMap("bar", "baz")), factory.create(properties));
    }

    @Test
    public void subSubMap() throws Exception {
        properties.put("foo.bar.baz", "qux");
        assertEquals(singletonMap("foo", singletonMap("bar", singletonMap("baz", "qux"))), factory.create(properties));
    }


    @Test(expected = IllegalArgumentException.class)
    public void invalidInput() throws Exception {
        properties.put("foo", "bar");
        properties.put("foo.baz", "qux");
        assertEquals(singletonMap("foo", singletonMap("bar", singletonMap("baz", "qux"))), factory.create(properties));
    }

    @Test
    public void twoMaps() throws Exception {
        properties.put("foo.bar.qux", "baz");
        properties.put("foo.bar.baz", "qux");
        assertEquals(singletonMap("foo", singletonMap("bar", ImmutableMap.of("qux", "baz", "baz", "qux"))), factory.create(properties));
    }

}