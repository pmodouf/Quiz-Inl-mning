package database;

public class Guest {

    private String name;

    public Guest(String name) {
        this.name = name;
    }
    public Guest(int id){
     this.name = "Guest" + String.valueOf(id);
    }
}