package properties;

import java.io.FileInputStream;
import java.util.Properties;

public class GameProperties {

    Properties properties;
    int rounds;
    int questions;

    public GameProperties() {
        String file = "src/resources/properties.xml";
        this.properties = new Properties();
        try{
            properties.loadFromXML(new FileInputStream(file));
        } catch (Exception e){
            e.printStackTrace();
        }
        this.rounds = Integer.parseInt(properties.getProperty("rounds"));
        this.questions = Integer.parseInt((properties.getProperty("questions")));
    }

    public int getRounds() {
        return rounds;
    }

    public int getQuestions() {
        return questions;
    }
}
