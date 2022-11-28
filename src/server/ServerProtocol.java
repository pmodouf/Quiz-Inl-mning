package server;

import database.QA;
import gamepackage.GamePackage;
import properties.GameProperties;

import java.util.ArrayList;

public class ServerProtocol {

    GamePackage player1 = new GamePackage();
    GamePackage player2 = new GamePackage();

    private static final int FIRST_INIT = 0;
    private static final int SET_CATEGORY_STATE = 1;
    private static final int GET_CATEGORY_STATE = 2;
    private static final int WAIT_STATE = 3;
    private static final int RESULT_STATE = 4;
    private static final int ROUND_STATE = 5;
    private static final int AUTO_WIN_STATE = 6;
    private static final int GIVE_UP_STATE = 7;

    volatile public boolean waitForCategory = false;

    volatile public boolean waitForResult = false;

    boolean playerGivenUp = false;

    int id = 0;

    int rounds = 0;

    volatile ArrayList<String[]> category;

    GameProperties properties = new GameProperties();

    QA qa = new QA();

    public synchronized GamePackage update(GamePackage gp) {


        setGamePackage(gp);
        setOpponent(gp);

        if(playerGivenUp){
            gp.setGameState(AUTO_WIN_STATE);
        }


        switch (gp.getGameState()) {
            case FIRST_INIT -> {
                id++;
                if(id == 1){
                    gp.setID(id);
                    gp.choseCategory = true;
                    gp.setWaiting(false);
                    gp.setGameState(SET_CATEGORY_STATE);
                } else {
                    gp.setID(id);
                    gp.setGameState(WAIT_STATE);
                    gp.choseCategory = false;
                    gp.setWaiting(true);
                    waitForCategory = true;
                    id = 0;
                }
            }
            case SET_CATEGORY_STATE -> {
                qa.loadQA(gp.getCategoryID());
                category = qa.getQA();
                gp.setQA(category);
                gp.setWaiting(true);
                gp.setGameState(ROUND_STATE);
                gp.choseCategory = false;
                waitForCategory = false;
                rounds++;
            } case GET_CATEGORY_STATE -> {
                gp.setGameState(ROUND_STATE);
                gp.setQA(category);
                gp.setWaiting(false);
                gp.choseCategory = true;
                waitForCategory = true;
            } case RESULT_STATE ->{
                waitForCategory = false;
                id++;
                if(id == 1){
                    waitForResult = true;
                } else {
                    if(gp.getTotalScore() > gp.getOpponent().getTotalScore()){
                        gp.setIWon(1);
                    } else if (gp.getTotalScore() < gp.getOpponent().getTotalScore()){
                        gp.setIWon(3);
                    } else {
                        gp.setIWon(2);
                    }
                    waitForResult = false;
                }
            } case AUTO_WIN_STATE ->{
                gp.setIWon(1);
                waitForResult = false;
            } case GIVE_UP_STATE ->{
                waitForCategory = false;
                playerGivenUp = true;
            }
        }

        if(rounds == properties.getRounds()){
            waitForCategory = false;
            gp.setLastRound(true);
        }

        setGamePackage(gp);
        setOpponent(gp);
        return gp;
    }

    private void setOpponent(GamePackage gp){
        if (gp.getID() == 1){
            gp.getOpponent().setAll(player2.getName(), player2.getAnswersMap(), player2.getImageID(), player2.getTotalScore(), player2.getWins());
        } else if (gp.getID() == 2)  {
            gp.getOpponent().setAll(player1.getName(), player1.getAnswersMap(), player1.getImageID(), player1.getTotalScore(), player1.getWins());
        }
    }

    private void setGamePackage(GamePackage gp){
        if (gp.getID() == 1){
            player1 = gp;
        } else if (gp.getID() == 2) {
            player2 = gp;
        }
    }
}
