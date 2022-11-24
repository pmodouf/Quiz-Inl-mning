package database;

import properties.Properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QA {

    private final Random random = new Random();

    private ArrayList<String[]> qaList;

    private String category = "";

    private final String[] categories = {"Historia","Sport","Musik", "Samhälle", "Vetenskap", "Geografi"};

    public QA(){
        qaList = new ArrayList<>();
    }

    public void loadQA(int i){
        //qaList.removeAll(Collections.singleton(null));
        switch (i){
            case 1 -> qaList = (loadFiles(categories[0]));
            case 2 -> qaList = (loadFiles(categories[1]));
            case 3 -> qaList = (loadFiles(categories[2]));
            case 4 -> qaList = (loadFiles(categories[3]));
            case 5 -> qaList = (loadFiles(categories[4]));
            case 6 -> qaList = (loadFiles(categories[5]));
            default -> qaList = (loadFiles(categories[random.nextInt(categories.length)+1]));
        }
    }

    private ArrayList<String[]> loadFiles (String filename){
        String line;
        category = filename;
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

    public String getCategory() {
        return category;
    }

    public ArrayList<String[]> getList() {
        return qaList;
    }

    public static void main(String[] args) {
        QA qa = new QA();
        Properties properties = new Properties();

        //Exempel till koden
        //Första.
        //qa.loadQA(gp.getCategoryID);
        //gp.setQA(qa.getList);
        //Andra.
        //gp.setQA(qa.getList);
        //
        //ifall 0 eller högre än dem kategorierna som finns så blir det random, annars så väljer man

        for (int j = 0; j < properties.getRounds(); j++) {
            qa.loadQA(0);
            int score = 0;
            System.out.println(qa.getCategory());
            for (int i = 0; i < properties.getQuestions(); i++) {
                System.out.println(qa.getList().get(i)[0]);
                System.out.println(qa.getList().get(i)[1] + " eller " + qa.getList().get(i)[2] + " eller " + qa.getList().get(i)[3] + " eller " + qa.getList().get(i)[4]);
                Scanner scan = new Scanner(System.in);
                String answer = scan.nextLine();
                if (answer.equalsIgnoreCase(qa.getList().get(i)[5])) {
                    System.out.println("CORRECT");
                    score++;
                } else {
                    System.out.println("WRONG");
                }
            }
            System.out.println("TotalScore: " + score + " out of " + qa.getList().size());
        }
    }
}

