package server;

import gamepackage.GamePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler extends Thread {
    private final Socket socket;
    private ServerProtocol protocol;
    private GamePackage gp;

    private boolean gameStarted = false;


    public ServerHandler(Socket socket, ServerProtocol protocol, int id) {
        this.socket = socket;
        this.protocol = protocol;
        gp = new GamePackage();
        gp.setID(id);

    }

    public void run(){

        try (ObjectOutputStream send = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream receive = new ObjectInputStream(socket.getInputStream())) {

            Object clientRequest;

            while ((clientRequest = receive.readObject()) != null){
                if (clientRequest instanceof GamePackage g) {
                    if (gameStarted) {
                        gp = g;
                        send.writeObject(waitCheck(gp));
                        send.flush();
                        System.out.println(gp.toString());
                    } else {
                        gp.setName(g.getName());
                        gameStarted = true;
                        System.out.println(gp.toString());
                        send.writeObject(gp);
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
        while(protocol.waitForCategory || (System.currentTimeMillis()-startTime) < 20000);
        return protocol.update(gp);
    }
}
