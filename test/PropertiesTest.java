import org.junit.Test;
import server.Properties;

import static org.junit.Assert.*;

public class PropertiesTest {

    Properties t = new Properties("src/resources/properties.xml");

    @Test
    public void testProperties(){
        assertEquals(t.getRounds(),2);
        assertEquals(t.getQuestions(), 2);
    }
}
