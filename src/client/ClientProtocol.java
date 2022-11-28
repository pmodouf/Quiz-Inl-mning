package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientProtocol {

    Client client;

    int questionCount = 0;

    boolean opponentIsNotSet = true;

    private static final int SET_CATEGORY_STATE = 1;
    private static final int GET_CATEGORY_STATE = 2;
    private static final int WAIT_STATE = 3;
    private static final int RESULT_STATE = 4;
    private static final int ROUND_STATE = 5;
    private static final int AUTO_WIN_STATE = 6;
    private static final int GIVE_UP_STATE = 7;

    public ClientProtocol(Client client){
        this.client = client;
    }

    public void update() {

        if(client.gp.getOpponent().getName() != null && opponentIsNotSet){
            client.gf.setUpOpponentInfo(client.gp.getOpponent().getName(), client.gp.getOpponent().getImageID(), client.gp.getOpponent().getWins());
            opponentIsNotSet = false;
        }

        switch (client.gp.getGameState()){
            case SET_CATEGORY_STATE ->{
                getRandomCategory();
                client.gf.GUIState(6);
                client.gf.toggleTimer();
            } case WAIT_STATE ->{
                client.gp.setGameState(GET_CATEGORY_STATE);
                client.sendAndReceive();
            } case ROUND_STATE ->{
                client.gp.setCategoryID(0);
                nextQuestion();
            } case RESULT_STATE ->{
                client.gf.setScore(client.gp.getAnswersMap(), client.gp.getOpponent().getScoreMap());
                client.gf.GUIState(7);
            } case AUTO_WIN_STATE -> {
                client.gf.lbWaitMessage.setText("Opponent gave up");
                client.gf.btBack.setVisible(true);
                client.gf.GUIState(5);
            } case GIVE_UP_STATE -> {
                client.gf.GUIState(3);
            }
        }
    }

    private void getRandomCategory() {
        List<String> categoryList = new ArrayList<>(List.of("0", "1", "2", "3", "4", "5"));
        Collections.shuffle(categoryList);
        client.gf.setCategories(categoryList.get(0), categoryList.get(1), categoryList.get(2));
    }

    public void nextQuestion(){
        if(client.gp.lastRound){
            client.gp.setGameState(RESULT_STATE);
            client.gp.setWaiting(false);
            client.sendAndReceive();
        } else if(questionCount == client.gp.getQA().size()){
            questionCount = 0;
            nextCategory();
        } else {
            client.currentQuestion = client.gp.getQA().get(questionCount);
            client.gf.setUpQA(client.currentQuestion);
            client.gf.GUIState(4);
            client.gf.toggleTimer();
            questionCount++;
        }
    }
    private void nextCategory() {
        if (client.gp.choseCategory){
            client.gp.setGameState(SET_CATEGORY_STATE);
            client.gp.setWaiting(false);
            getRandomCategory();
            client.gf.GUIState(6);
            client.gf.toggleTimer();
        } else {
            client.gp.setGameState(GET_CATEGORY_STATE);
            client.gf.GUIState(5);
            client.sendAndReceive();
        }
    }
}






