package database;

import java.io.Serializable;

public class Guest implements Serializable {

    private String name;

    public Guest(String name) {
        this.name = name;
    }
    public Guest(int id){
     this.name = "Guest" + String.valueOf(id);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
