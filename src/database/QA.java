package database;

import properties.GameProperties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QA {

    private final Random random = new Random();
    private ArrayList<String[]> qaList;
    private String category = "";
    private final String[] categories = {"Historia","Sport","Musik", "Samhälle", "Vetenskap", "Geografi"};
    private final GameProperties properties = new GameProperties();

    public QA(){
        qaList = new ArrayList<>();
    }

    public void loadQA(int i){
        switch (i){
            case 1 -> qaList = (loadFiles(categories[0]));
            case 2 -> qaList = (loadFiles(categories[1]));
            case 3 -> qaList = (loadFiles(categories[2]));
            case 4 -> qaList = (loadFiles(categories[3]));
            case 5 -> qaList = (loadFiles(categories[4]));
            case 6 -> qaList = (loadFiles(categories[5]));
            default -> qaList = (loadFiles(categories[random.nextInt(categories.length)]));
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
            Collections.shuffle(temp);
            ArrayList<String[]> temp2 = new ArrayList<>();
            for (int i = 0; i < properties.getQuestions(); i++) {
                temp2.add(temp.get(i));
            }
            return temp2;
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
        GameProperties properties = new GameProperties();

        //Exempel till koden
        //Första.
        //qa.loadQA(gp.getCategoryID);
        //gp.setQA(qa.getList);
        //Andra.
        //gp.setQA(qa.getList);
        //
        //ifall 0 eller högre än dem kategorierna som finns så blir det random, annars så väljer man


        int totalScore = 0;
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
            System.out.println("Score: " + score + " out of " + properties.getQuestions());
            totalScore += score;
        }
        System.out.println("TotalScore: " + totalScore + " out of " + properties.getQuestions()*properties.getRounds());
    }
}

