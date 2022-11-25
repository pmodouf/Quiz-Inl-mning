package database;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {

    String name;
    int wins;
    LocalDate created;
    String avatarID = "old1";

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getImage() {
        return avatarID;
    }

    public void setImage(String image) {
        this.avatarID = avatarID;
    }
}
