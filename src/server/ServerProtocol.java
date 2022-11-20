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
        //setOpponent(gp);
        if(gp.getGameState() == FIRST_INIT){
            waitCheck(gp);

            //metod för att fylla in all grundinfo i GamePackage
            //kanske kolla om det är en befintlig user eller en ny också
            gp.setGameState(GAME_ACTIVE);
        } else if (gp.getGameState() == GAME_ACTIVE) {
            //metod för att skicka tillbaka gp medans gamet är aktivt

        } else if (gp.getGameState() == END_GAME) {
            //avsluta spelet?
        } else if (gp.getGameState() == REPEAT_REQUEST) {
            //metod för att skicka tillbaka båda spelarna om båda svarat ja till en rematch.

            gp.setGameState(GAME_ACTIVE);
        }


        gp.setMessage("Hej det här är från protokollet");

        setGamePackage(gp);
        return gp;
    }

    private void setOpponent(GamePackage gp){
        if (gp.getID() == 1){
            gp.setOpponent(player2);
        } else {
            gp.setOpponent(player1);
        }
    }

    private void setGamePackage(GamePackage gp){
        if (gp.getID() == 1){
            player1 = gp;
        } else {
            player2 = gp;
        }
    }
    private GamePackage waitCheck(GamePackage gp){

        long startTime = System.currentTimeMillis();
        while(waitForCategory){
            if ((System.currentTimeMillis()-startTime) < 20000){
                break;
            }
        }
        return gp;
    }
}
