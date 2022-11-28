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
                    if (g.isLastRound()) {
                        protocol.update(g);
                        while (protocol.waitForResult) {}
                        send.writeObject(waitCheck(g));
                        send.flush();
                    } else {
                        send.writeObject(waitCheck(g));
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
    private GamePackage waitCheck(GamePackage gp) {
        if (!gp.isWaiting()) {
            return protocol.update(gp);
        }
        while (protocol.waitForCategory){}
        return protocol.update(gp);
    }
}
