package server;

import gamepackage.GamePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final ServerProtocol protocol;
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

            while ((clientRequest = receive.readObject()) != null){
                if (clientRequest instanceof GamePackage g) {
                    send.writeObject(protocol.update(g));
                    send.flush();


                    /*
                    if (gameStarted) {
                        send.writeObject(waitCheck(g));
                        send.flush();
                        System.out.println(g.toString());
                    } else {
                        g.setID(id);
                        gameStarted = true;
                        System.out.println(g.toString());
                        //send.writeObject(g);
                        send.writeObject(waitCheck(g));
                        send.flush();
                    }

                     */
                }
            }
        } catch (IOException e) {
            System.out.println("hejhopp!");
            System.out.println(e.getClass());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
