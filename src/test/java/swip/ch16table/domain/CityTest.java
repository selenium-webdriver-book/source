package swip.ch16table.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CityTest {

    private City city = new City(2, "Edison", "New Jersey");

    @Test
    public void testToString() throws Exception {
        assertEquals("new City(2, \"Edison\", \"New Jersey\")\n", city.toString());
        System.out.println(city);
    }

}