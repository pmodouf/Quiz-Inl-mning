package server;

import java.io.FileInputStream;

public class Properties {

    java.util.Properties properties;
    int rounds;
    int questions;

    public Properties(String file) {
        this.properties = new java.util.Properties();
        try{
            properties.loadFromXML(new FileInputStream(file));
        } catch (Exception e){
            e.printStackTrace();
        }
        rounds = Integer.parseInt(properties.getProperty("rounds"));
        questions = Integer.parseInt((properties.getProperty("questions")));
    }

    public int getRounds() {
        return rounds;
    }

    public int getQuestions() {
        return questions;
    }
}
