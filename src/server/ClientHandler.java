package server;

import gamepackage.GamePackage;

import java.io.EOFException;
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
                    if (!gameStarted) {
                        g.setID(id);
                        gameStarted = true;
                    }
                    send.writeObject(waitCheck(g));
                    send.flush();
                    //TEMP
                    System.out.println(g);
                }
            }
        } catch (EOFException e){
            System.out.println("Abrupt end of package to " + getName() + " due to disconnect from " + socket.getInetAddress().getHostName());
        } catch (IOException e) {
            System.out.println("hejhopp!");
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
