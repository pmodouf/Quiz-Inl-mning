package server;

import gamepackage.GamePackage;
import properties.Properties;

public class ServerProtocol {
    GamePackage player1;
    GamePackage player2;

    private static final int FIRST_INIT = 0;
    private static final int GAME_ACTIVE = 1;
    private static final int END_GAME = 2;
    private static final int REPEAT_REQUEST = 3;

    private boolean gameStarted = false;

    public boolean waitForCategory = false;

    //QA obj

    Properties properties = new Properties();

    private final int round1 = 1;
    private final int round2 = 2;
    private final int round3 = 3;

    //TEMPORÄRA FRÅGOR
    String[] QAs = new String[6];
    String[] category1 = {"category1 fråga","category1 svar1","category1 svar2","category1 svar3","category1 svar4","category1 svar2"};
    String[] category2 = {"category2 fråga","category2 svar1","category2 svar2","category2 svar3","category2 svar4","category2 svar3"};
    String[] category3 = {"category3 fråga","category3 svar1","category3 svar2","category3 svar3","category3 svar4","category3 svar1"};
    String[] category4 = {"category4 fråga","category4 svar1","category4 svar2","category4 svar3","category4 svar4","category4 svar1"};
    String[] category5 = {"category5 fråga","category5 svar1","category5 svar2","category5 svar3","category5 svar4","category5 svar4"};
    String[] category6 = {"category6 fråga","category6 svar1","category6 svar2","category6 svar3","category6 svar4","category6 svar2"};

    public ServerProtocol(){
        QAs[0] = "FRÅGAN HÄR";
        QAs[1] = "SVAR 1";
        QAs[2] = "SVAR 2";
        QAs[3] = "SVAR 3";
        QAs[4] = "SVAR 4";
        QAs[5] = "SVAR 3"; //Correct answer


    }


    public GamePackage update(GamePackage gp){
        setGamePackage(gp);
        setOpponent(gp);
        if(gp.getGameState() == FIRST_INIT){
           if(gp.getID() == 1){
               gp.setQA(category1);
               gp.setGameState(GAME_ACTIVE);
           }else{
               gp.setWaiting(true);
               waitForCategory = true;
               gp.setGameState(GAME_ACTIVE);
           }
        } else if (gp.getGameState() == GAME_ACTIVE) {
            gp.setMessage("TEST2");
            gp.setWaiting(false);
            //metod för att skicka tillbaka gp medans gamet är aktivt
            gp.setGameState(END_GAME);
        } else if (gp.getGameState() == END_GAME) {
            gp.setMessage("TEST3");
            //avsluta spelet?
            gp.setGameState(REPEAT_REQUEST);
        } else if (gp.getGameState() == REPEAT_REQUEST) {
            waitForCategory = false;
            //gp.setWaiting(true);
            //metod för att skicka tillbaka båda spelarna om båda svarat ja till en rematch.
            if(player1.getGameState() == REPEAT_REQUEST && player2.getGameState() == REPEAT_REQUEST){
                gp.setGameState(GAME_ACTIVE);
            }
        }

        //setGamePackage(gp);
        return gp;
    }

    private void setOpponent(GamePackage gp){
        if (gp.getID() == 1 && player2 != null){
            gp.getOpponent().setName(player2.getName());
            gp.getOpponent().setTotalScore(player2.getTotalScore());

            //TEMP?
            gp.getOpponent().setID(player2.getID());
        } else if (gp.getID() == 2 && player1 != null){
            gp.getOpponent().setName(player1.getName());
            gp.getOpponent().setTotalScore(player1.getTotalScore());

            //TEMP?
            gp.getOpponent().setID(player1.getID());
        }
    }

    private void setGamePackage(GamePackage gp){
        if (gp.getID() == 1){
            player1 = gp;
        } else {
            player2 = gp;
        }
    }

    private void setupBaseGamePackage(GamePackage gp){
        gp.setTotalScore(0);
        gp.setMessage("Hej och välkommen till spelet");
        gp.setQA(category1);
    }
}
