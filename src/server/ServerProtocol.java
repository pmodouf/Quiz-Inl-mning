package server;

import gamepackage.GamePackage;
import properties.Properties;

public class ServerProtocol {
    GamePackage player1;
    GamePackage player2;

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
//        System.out.println(player1.toString());
//        System.out.println(player2.toString());

        gp.setMessage("Hej det här är från protokollet");

//        System.out.println(player1.toString());
//        System.out.println(player2.toString());
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
}
