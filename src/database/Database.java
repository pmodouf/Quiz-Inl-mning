package database;

import utility.StaticImageHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Database {

    public User getUser(String name) {
        String userLine = "";
        try (Stream<String> stream = Files.lines(Paths.get("src/resources/users/users.txt"))) {
            userLine = stream.filter(line -> line.startsWith(name))
                    .collect(Collectors.joining());;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (userLine.equals("")) {
            return null;
        }
        String[] userSplit = userLine.split("/");
        User user = new User();
        user.setName(userSplit[0]);
        user.setWins(Integer.parseInt(userSplit[2]));
        user.setCreated(LocalDate.parse(userSplit[3]));
        user.setImage(userSplit[4]);

        return user;
    }

    public boolean validateUser(String name, String password) {
        String userLine = "";
        try (Stream<String> stream = Files.lines(Paths.get("src/resources/users/users.txt"))) {
            userLine = stream.filter(line -> line.startsWith(name))
                    .collect(Collectors.joining());

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (userLine.equals("")) {
            return false;
        }
        System.out.println(userLine);
        String[] userSplit = userLine.split("/");
        return userSplit[1].equals(password);
    }

    public boolean createUser(String name, String password, String avatarID) {
        if (userDoesNotExist(name)) {
            String userLine = name + "/" + password + "/0/" + LocalDate.now() + "/" + avatarID;
            addUserToFile(userLine);
            return true;
        }
        return false;
    }

    public void updateUser(User user){
        List<String> lines = Collections.emptyList();
        try (Stream<String> stream = Files.lines(Paths.get("src/resources/users/users.txt"))) {
            lines = stream
                    .filter(line -> !line.isBlank())
                    .<String>mapMulti((line, consumer) -> {
                        if (!line.startsWith(user.getName())) consumer.accept(line);
                        else {
                            String[] parts = line.split("/");
                            parts[2] = String.valueOf(user.getWins());
                            parts[4] = user.getImage();
                            consumer.accept(String.join("/", parts));
                        }
                    })
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter fw = new FileWriter(Paths.get("src/resources/users/users.txt").toFile());
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String s :
                    lines) {
                bw.write(s + "\n");
            }
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addUserToFile(String userLine) {
        try (FileWriter fw = new FileWriter(Paths.get("src/resources/users/users.txt").toFile(), true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(userLine + "\n");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean userDoesNotExist(String name) {
        String userLine = "";
        try (Stream<String> stream = Files.lines(Paths.get("src/resources/users/users.txt"))) {
            userLine = stream.filter(line -> line.startsWith(name))
                    .collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userLine.equals("");
    }
}
