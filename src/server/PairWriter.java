package server;

import java.io.PrintWriter;
import java.util.ArrayList;

public class PairWriter {

    private ArrayList<PrintWriter> writers = new ArrayList<>();

    public void add(PrintWriter p){
        writers.add(p);
    }
    public ArrayList<PrintWriter> getWriters(){
        return writers;
    }
}
