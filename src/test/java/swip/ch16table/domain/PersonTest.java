package swip.ch16table.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    private Person person = new Person(1, "John", "Doe", 44);

    @Test
    public void testToString() throws Exception {
        assertEquals("new Person(1, \"John\", \"Doe\", 44)\n", person.toString());
        System.out.println(person);
    }

}