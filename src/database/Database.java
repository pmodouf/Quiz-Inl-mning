package database;

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
import java.util.stream.Stream;

public class Database {

    public User getUser(String name) {
        String userLine = "";
        try (Stream<String> stream = Files.lines(Paths.get("src/resources/users/users.txt"))) {
            userLine = stream.filter(line -> line.startsWith(name)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (userLine.equals("")) {
            return null;
        }
        String[] userSplit = userLine.split("/");
        return new User(userSplit[0], Integer.parseInt(userSplit[2]),
                LocalDate.parse(userSplit[3]),
                loadImage(userSplit[4]));
    }

    public boolean validateUser(String name, String password) {
        String userLine = "";
        try (Stream<String> stream = Files.lines(Paths.get("src/resources/users/users.txt"))) {
            userLine = stream.filter(line -> line.startsWith(name)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (userLine.equals("")) {
            return false;
        }
        String[] userSplit = userLine.split("/");
        return userSplit[1].equals(password);
    }

    public boolean createUser(String name, String password, String avatarID) {
        if (userDoesNotExist(name)) {
            String userLine = name + "/" + password + "/" + LocalDate.now() + "/" + avatarID;
            addUserToFile(userLine);
            return true;
        }
        return false;
    }

    public boolean createUser(String name, String password, BufferedImage image) {
        if (userDoesNotExist(name)) {
            String pngID = newPngID(image);
            String userLine = name + "/" + password + "/" + LocalDate.now() + "/" + pngID;
            addUserToFile(userLine);
            return true;
        }
        return false;
    }

    public void updateWins(String name) {
        List<String> lines = Collections.emptyList();
        try (Stream<String> stream = Files.lines(Paths.get("src/resources/users/users.txt"))) {
            lines = processLines(stream, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> processLines(Stream<String> lines, String name) {

        return lines
                .filter(line -> !line.isBlank())
                .<String>mapMulti((line, consumer) -> {
                    if (!line.startsWith(name)) consumer.accept(line);
                    else {
                        String[] parts = line.split("/");
                        parts[2] = String.valueOf(Integer.parseInt(parts[2]) + 1);
                        consumer.accept(String.join("/", parts));
                    }
                })
                .toList();
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
            userLine = stream.filter(line -> line.startsWith(name)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userLine.equals("");
    }

    private String newPngID(BufferedImage image) {
        int fileCount = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("src/resources/images/profile-pictures"), "*.{png}")) {
            for (Path ignored : stream) {
                fileCount++;
            }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
        }
        fileCount++;
        String pngID = String.valueOf(fileCount);
        try {
            File newFile = File.createTempFile(pngID, "png");
            ImageIO.write(image, "png", newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(fileCount);
    }

    private BufferedImage loadImage(String pngID) {

        BufferedImage bi;
        if (pngID.startsWith("p")) {
            try {
                bi = ImageIO.read(new File("src/resources/images/profile-pictures/" + pngID + ".png"));
                return scaleImage(bi);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        try {
            bi = ImageIO.read(new File("src/resources/images/avatar/" + pngID + ".png"));
            return bi;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage scaleImage(BufferedImage original) {
        BufferedImage scaledImage = new BufferedImage(96, 96, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, 96, 96, null);
        g2.dispose();
        return scaledImage;
    }
}
