import org.junit.Test;
import properties.Properties;

import static org.junit.Assert.*;

public class PropertiesTest {

    Properties t = new Properties();

    @Test
    public void testProperties(){
        assertEquals(t.getRounds(),2);
        assertEquals(t.getQuestions(), 2);
    }
}
