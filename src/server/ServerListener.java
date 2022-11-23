package server;

import java.io.*;
import java.net.ServerSocket;

public class ServerListener {

    final private int port = 12345;
    ServerProtocol spHolder;

    ServerListener() {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                connectClient(server);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void connectClient(ServerSocket server) throws IOException {
        spHolder = new ServerProtocol();
        ClientHandler client1 = new ClientHandler(server.accept(), spHolder, 1);
        ClientHandler client2 = new ClientHandler(server.accept(), spHolder, 2);
        client1.start();
        client2.start();
    }
    public static void main(String[] args) {
      new ServerListener();
    }
}









