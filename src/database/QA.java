package database;

import properties.GameProperties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class QA {
    private ArrayList<String[]> qaList;

    GameProperties props = new GameProperties();

    private final String[] categories = new String[]{"Historia", "Sport", "Musik", "Samhälle", "Vetenskap", "Geografi"};

    public QA() {qaList = new ArrayList<>();}

    public void loadQA(int category){
        qaList.clear();
        try(Stream<String> stream = Files.lines(Paths.get("src/resources/categories/" + categories[category] + ".txt"))){
            stream.forEach(line ->{
                qaList.add(line.split("/"));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(qaList);
    }

    public ArrayList<String[]> getQA(){
        ArrayList<String[]> QA = new ArrayList<>();
        for (int i = 0; i < props.getQuestions(); i++) {
            QA.add(qaList.get(i));
        }
        return QA;
    }
}

