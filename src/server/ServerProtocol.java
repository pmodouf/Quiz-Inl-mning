package server;

import database.QA;
import gamepackage.GamePackage;
import properties.GameProperties;

import java.util.ArrayList;
import java.util.Random;

public class ServerProtocol {

    GamePackage player1 = new GamePackage();
    GamePackage player2 = new GamePackage();

    private static final int FIRST_INIT = 0;
    private static final int CATEGORY_STATE = 1;
    private static final int ROUND_STATE = 2;
    private static final int WAIT_STATE = 3;
    private static final int RESULT_STATE = 4;


    private boolean gameStarted = false;

    public boolean waitForCategory = false;

    public boolean categoryPicked = false;

    public boolean turnToPick = true;

    public boolean waitForResult = false;

    Random random = new Random();

    int id = 0;

    int round = 0;

    ArrayList<String[]> category;

    //QA obj
    private int categoryPick;

    //Räkna antal rundor servern har delat ut.
    int totalRounds = 0;

    GameProperties properties = new GameProperties();

    QA qa = new QA();

    public synchronized GamePackage update(GamePackage gp) {
        setGamePackage(gp);
        setOpponent(gp);

        switch (gp.getGameState()) {
            case FIRST_INIT -> {
                id++;
                if(id == 1){
                    gp.setID(id);
                    gp.choseCategory = true;
                    gp.setWaiting(false);
                    gp.setGameState(CATEGORY_STATE);
                }else{
                    gp.setID(id);
                    gp.setGameState(ROUND_STATE);
                    gp.choseCategory = false;
                    gp.setWaiting(true);
                    waitForCategory = true;
                }
            } case CATEGORY_STATE -> {
                qa.loadQA(gp.getCategoryID());
                category = qa.getList();
                gp.setQA(category);
                waitForCategory = false;
                gp.setWaiting(false);
                gp.setGameState(ROUND_STATE);
                gp.choseCategory = false;
            }case ROUND_STATE ->{
                if (round * 2 == properties.getRounds()){
                    gp.setGameState(RESULT_STATE);
                } else {
                    round++;
                    if(round % 2 != 0){
                        gp.choseCategory = true;
                        waitForCategory = true;
                        gp.setWaiting(false);
                        gp.setGameState(CATEGORY_STATE);
                    } else {
                        gp.setGameState(ROUND_STATE);
                        gp.setQA(category);
                        gp.setWaiting(false);
                        gp.choseCategory = true;
                        waitForCategory = true;
                    }
                }
            }
        }
        setGamePackage(gp);
        setOpponent(gp);
        return gp;
    }

    private void setOpponent(GamePackage gp){
        if (gp.getID() == 1){
            gp.getOpponent().setAll(player2.getName(), player2.getAnswersMap(), player2.getImage(), player2.getTotalScore());
        } else {
            gp.getOpponent().setAll(player1.getName(), player1.getAnswersMap(), player1.getImage(), player1.getTotalScore());
        }
    }

    private void setGamePackage(GamePackage gp){
        if (gp.getID() == 1){
            player1 = gp;
        } else {
            player2 = gp;
        }
    }

}
