package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientProtocol {

    Client client;

    int questionCount = 0;

    boolean opponentIsNotSet = true;

    private static final int FIRST_INIT = 0;
    private static final int CATEGORY_STATE = 1;
    private static final int GET_CATEGORY_STATE = 2;
    private static final int WAIT_STATE = 3;
    private static final int RESULT_STATE = 4;
    private static final int ROUND_STATE = 5;

    public ClientProtocol(Client client){
        this.client = client;
    }

    public void update() {

        if(client.gp.getOpponent().getName() != null && opponentIsNotSet){
            client.gf.setUpOpponentInfo(client.gp.getOpponent().getName(), client.gp.getOpponent().getImageID(), client.gp.getOpponent().getWins());
            opponentIsNotSet = false;
        }

        switch (client.gp.getGameState()){
            case CATEGORY_STATE ->{
                getRandomCategory();
                client.gf.GUIState(6);
                client.gf.toggleTimer();
            } case WAIT_STATE ->{
                client.gp.setGameState(GET_CATEGORY_STATE);
                client.sendAndReceive();
            }
            case ROUND_STATE ->{
                client.gp.setCategoryID(0);
                nextQuestion();
            } case RESULT_STATE ->{
                client.gf.GUIState(7);
            }
        }
    }

    private void getRandomCategory() {
        List<String> categoryList = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6"));
        Collections.shuffle(categoryList);
        client.gf.setCategories(categoryList.get(0), categoryList.get(1), categoryList.get(2));
    }

    public void nextQuestion(){
        if(questionCount == client.gp.getQA().size()){
            questionCount = 0;
            if(client.gp.isWaiting()){
                client.gf.GUIState(5);
                client.gp.setGameState(GET_CATEGORY_STATE);
            } else {
                client.gf.GUIState(5);
                client.gp.setGameState(CATEGORY_STATE);
            }
            client.sendAndReceive();
        } else{
            client.currentQuestion = client.gp.getQA().get(questionCount);
            client.gf.setUpQA(client.currentQuestion);
            client.gf.GUIState(4);
            client.gf.toggleTimer();
        }
        questionCount++;
    }
}






