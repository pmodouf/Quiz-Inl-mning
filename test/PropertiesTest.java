import org.junit.Test;
import server.properties;

import static org.junit.Assert.*;

public class PropertiesTest {

    properties t = new properties("src/resources/properties.xml");

    @Test
    public void testProperties(){
        assertEquals(t.getRounds(),2);
        assertEquals(t.getQuestions(), 2);
    }
}
