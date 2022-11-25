package server;

import gamepackage.GamePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final ServerProtocol protocol;

    int id;

    private boolean gameStarted = false;


    public ClientHandler(Socket socket, ServerProtocol protocol) {
        this.socket = socket;
        this.protocol = protocol;
    }

    public void run(){

        try (ObjectOutputStream send = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream receive = new ObjectInputStream(socket.getInputStream())) {

            Object clientRequest;

            while ((clientRequest = receive.readObject()) != null){
                if (clientRequest instanceof GamePackage g) {
                    if (gameStarted) {
                        if(g.isLastRound()) {
                            protocol.update(g);
                            while (protocol.waitForResult){
                                //WOOPTIE DOOO
                            }
                            send.writeObject(waitCheck(g));
                            send.flush();
                        } else {
                            send.writeObject(waitCheck(g));
                            send.flush();
                            //TEMP
                            System.out.println(getName() + " " + g);
                        }
                    }else {
                        g.setID(id);
                        gameStarted = true;
                        if(id == 1){
                            g.choseCategory = true;
                        }
                        send.writeObject(g);
                        send.flush();
                    }
                }
            }
        } catch (SocketException e){
            System.out.println("Client disconnected from " + socket.getInetAddress().getHostName());
        } catch (IOException e) {
            System.out.println(e.getClass());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private GamePackage waitCheck(GamePackage gp){
        if(!gp.isWaiting()) {
            return protocol.update(gp);
        }
        long startTime = System.currentTimeMillis();
            while (protocol.waitForCategory) {
                if ((System.currentTimeMillis() - startTime) > 20000) {
                    break;
                }
            }
        return protocol.update(gp);
    }
}
