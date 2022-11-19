package server;

import gamepackage.GamePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private ServerProtocol protocol;
    //private GamePackage gp;

    int id;

    private boolean gameStarted = false;


    public ClientHandler(Socket socket, ServerProtocol protocol, int id) {
        this.socket = socket;
        this.protocol = protocol;
        this.id = id;
    }

    public void run(){

        try (ObjectOutputStream send = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream receive = new ObjectInputStream(socket.getInputStream())) {

            Object clientRequest;

            while ((clientRequest = receive.readObject()) != null) {
                if (clientRequest instanceof GamePackage g) {
                    if (gameStarted) {
                        send.writeObject(waitCheck(g));
                        send.flush();
                        System.out.println(g);
                    } else {
                        g.setID(id);
                        gameStarted = true;
                        System.out.println(g);
                        send.writeObject(g);
                        send.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getClass());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private GamePackage waitCheck(GamePackage gp){

        long startTime = System.currentTimeMillis();
        while(protocol.waitForCategory){
            if ((System.currentTimeMillis()-startTime) < 20000){
                break;
            }
        }
        return protocol.update(gp);
    }
}
