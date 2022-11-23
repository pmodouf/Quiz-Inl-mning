package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class QA {

    private final ArrayList<ArrayList<String[]>> qaList;

    public QA(){
        qaList = new ArrayList<>();
    }

    public void loadQA(int i){
        qaList.removeAll(Collections.singleton(null));
        switch (i){
            case 1 -> qaList.add(loadFiles("Historia"));
            case 2 -> qaList.add(loadFiles("Sport"));
            case 3 -> qaList.add(loadFiles("Musik"));
            case 4 -> qaList.add(loadFiles("Samhälle"));
            case 5 -> qaList.add(loadFiles("Vetenskap"));
            case 6 -> qaList.add(loadFiles("Geografi"));
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
            Collections.shuffle(temp);
            return temp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String[]> getList() {
        return qaList.get(0);
    }

    public static void main(String[] args) {
        QA qa = new QA();
        Random random = new Random();
        qa.loadQA(random.nextInt(6)+1);
        System.out.println(qa.getList().get(0)[0]);
        System.out.println(qa.getList().get(0)[1] + " eller " + qa.getList().get(0)[2] + " eller " + qa.getList().get(0)[3] + " eller " + qa.getList().get(0)[4]);
        Scanner scan = new Scanner(System.in);
        boolean test = true;
        while(test) {
            String answer = scan.nextLine();
            if (answer.equalsIgnoreCase(qa.getList().get(0)[5])) {
                System.out.println("CORRECT");
                test = false;
            } else {
                System.out.println("WRONG");
            }
        }
    }
}
