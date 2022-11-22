package server;

import gamepackage.GamePackage;
import properties.Properties;

import java.util.Random;

public class ServerProtocol {

    GamePackage player1 = new GamePackage();
    GamePackage player2 = new GamePackage();

    private static final int FIRST_INIT = 0;
    private static final int CATEGORY_STATE = 1;
    private static final int END_GAME = 2;
    private static final int REPEAT_REQUEST = 3;

    private boolean gameStarted = false;

    public boolean waitForCategory = false;

    public boolean categoryPicked = false;

    public boolean turnToPick = true;

    public boolean waitForResult = false;

    Random random = new Random();

    //QA obj
    private int categoryPick;

    //Räkna antal rundor servern har delat ut.
    int totalRounds = 0;

    Properties properties = new Properties();

    //TEMPORÄRA KATEGORIER
    String[] categories = {"Sport","Fritid","Historia", "Samhälle"};

    //TEMPORÄRA FRÅGOR
    String[] category1 = {"category1 fråga","category1 svar1","category1 svar2","category1 svar3","category1 svar4","category1 svar2"};
    String[] category2 = {"category2 fråga","category2 svar1","category2 svar2","category2 svar3","category2 svar4","category2 svar3"};
    String[] category3 = {"category3 fråga","category3 svar1","category3 svar2","category3 svar3","category3 svar4","category3 svar1"};
    String[] category4 = {"category4 fråga","category4 svar1","category4 svar2","category4 svar3","category4 svar4","category4 svar1"};
    String[] category5 = {"category5 fråga","category5 svar1","category5 svar2","category5 svar3","category5 svar4","category5 svar4"};
    String[] category6 = {"category6 fråga","category6 svar1","category6 svar2","category6 svar3","category6 svar4","category6 svar2"};

    public GamePackage update(GamePackage gp) {
        setGamePackage(gp);
        setOpponent(gp);

        switch (gp.getGameState()) {
            case FIRST_INIT -> {
                if(properties.getRounds() >= totalRounds) {
                    categoryPicked = false;
                    gp.setCategoryID(0);
                    if (gp.getID() == 1 && turnToPick) {
                        //får välja categories varannan gång
                        gp.setQA(categories);
                        gp.setGameState(CATEGORY_STATE);
                    } else if (gp.getID() == 2 && (!turnToPick)) {
                        gp.setQA(categories);
                        gp.setGameState(CATEGORY_STATE);
                    } else {
                        //client som inte får välja kategori hamnar här istället.
                        gp.setWaiting(true);
                        waitForCategory = true;
                        gp.setGameState(CATEGORY_STATE);
                        turnToPick = !turnToPick;
                    }
                    totalRounds++;
                    if(totalRounds == properties.getRounds()){
                        gp.setLastRound(true);
                    }
                } else {
                    //körs när vi har kommit upp i antal rundor och ska då direkt vidare till visa resultat.
                    waitForResult = !waitForResult;
                    gp.setGameState(END_GAME);
                }
            } case CATEGORY_STATE -> {
                //När det har valts kategori så skickas man in här, och det inte valts kategori så väljs en random
                //kategori
                if(gp.getCategoryID() != 0 && !categoryPicked){
                    categoryPick = gp.getCategoryID();
                    categoryPicked = true;
                } else if (gp.getCategoryID() == 0 && !categoryPicked) {
                    categoryPick = random.nextInt(categories.length)+1;
                    gp.setCategoryID(categoryPick);
                    categoryPicked = true;
                }
                //skickar ut alla frågor och svar till clients så dem kan spela.
                if(categoryPicked){
                    gp.setCategoryID(categoryPick);
                    gp.setGameState(FIRST_INIT);
                    gp.setQA(setQuestions(categoryPick));
                    waitForCategory = false;
                }
                gp.setWaiting(false);
            }
        }
        setGamePackage(gp);
        setOpponent(gp);
        return gp;
    }

    private void setOpponent(GamePackage gp){
        if (gp.getID() == 1){
            gp.getOpponent().setAll(player2.getName(), player2.getAnswers(), player2.getImage(), player2.getTotalScore());
        } else {
            gp.getOpponent().setAll(player1.getName(), player1.getAnswers(), player1.getImage(), player1.getTotalScore());
        }
    }

    private void setGamePackage(GamePackage gp){
        if (gp.getID() == 1){
            player1 = gp;
        } else {
            player2 = gp;
        }
    }

    //TEMP METOD
    private String[] setQuestions(int category){
        if(category == 1){
            return category1;
        } else if (category == 2) {
            return category2;
        } else if (category == 3) {
            return category3;
        } else {
            return null;
        }
    }
}
