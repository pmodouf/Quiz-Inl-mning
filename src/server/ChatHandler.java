package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ChatHandler extends Thread{

    private Socket socket;
    private PairWriter pairWriter;
    public ChatHandler(Socket socket, PairWriter pw) {
        this.socket = socket;
        this.pairWriter = pw;
    }

    public void run(){
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            pairWriter.add(out);

            String input;

            while((input = in.readLine()) != null){
                for (PrintWriter writer : pairWriter.getWriters()) {
                    writer.println(input);
                }
            }
        } catch (SocketException e){
            System.out.println("Chat-Client Disconnected");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
