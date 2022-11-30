package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer extends Thread{
    final int port = 55544;
    int count = 0;
    PairWriter pw;
    public void run(){
        pw = new PairWriter();
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                connectClient(server);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void connectClient(ServerSocket server) throws IOException {
        count++;
        ChatHandler ch = new ChatHandler(server.accept(), pw);
        ch.start();
        if (count == 2){
            pw = new PairWriter();
        }
    }
}
