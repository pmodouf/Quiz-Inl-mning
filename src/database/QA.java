package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.random;

public class QA {

    private final ArrayList<ArrayList<String[]>> qaList;

    public QA(){
        qaList = new ArrayList<>();
        /*
        qaList.add(loadFiles("historia"));
        qaList.add(loadFiles("sport"));
        qaList.add(loadFiles("musik"));
        qaList.add(loadFiles("samhälle"));
        qaList.add(loadFiles("vetenskap"));
        qaList.add(loadFiles("geografi"));

     */
    }


    public void loadQA(int i){
        switch (i){
            case 1 -> qaList.add(loadFiles("historia"));
            case 2 -> qaList.add(loadFiles("sport"));
            case 3 -> qaList.add(loadFiles("musik"));
            case 4 -> qaList.add(loadFiles("samhälle"));
            case 5 -> qaList.add(loadFiles("vetenskap"));
            case 6 -> qaList.add(loadFiles("geografi"));
        }
    }

    private ArrayList<String[]> loadFiles (String filename){
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader("src/resources/categories/" + filename + ".txt"))){
            ArrayList<String[]> temp = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                temp.add(line.split("/"));
            }
            //Kanske flytta shuffle till när man skickar ut frågorna istället
            //Collections.shuffle(temp);
            return temp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ArrayList<String[]>> getQaList() {
        return qaList;
    }



    public static void main(String[] args) {
        QA qa = new QA();
        qa.loadQA(2);
        System.out.println(Arrays.toString(qa.getQaList().get(0).get(0)));
        System.out.println(Arrays.toString(qa.getQaList().get(0).get(1)));
        System.out.println(Arrays.toString(qa.getQaList().get(0).get(2)));

        if(qa.getQaList().get(0).get(0)[2].equals(qa.getQaList().get(0).get(0)[5])){
            System.out.println("rätt svar");
        }
    }
}
