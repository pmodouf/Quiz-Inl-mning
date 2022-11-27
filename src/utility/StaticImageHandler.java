package utility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class StaticImageHandler {

    static public BufferedImage scoreImage(int row){

        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File("src/resources/images/score/score.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return bi.getSubimage(0, (row * 30) - 30, 144, 30);
    }

    static public BufferedImage scoreSubImage(BufferedImage image, int row){
        return image.getSubimage(0, (row * 30) - 30, 144, 30);
    }

    static public ImageIcon getScoreSubImageByID(int ID){
        switch (ID){
            case 212 ->{
                return new ImageIcon(scoreImage(1));
            }case 121 ->{
                return new ImageIcon(scoreImage(2));
            }case 211 ->{
                return new ImageIcon(scoreImage(3));
            }case 221 ->{
                return new ImageIcon(scoreImage(4));
            }case 222 ->{
                return new ImageIcon(scoreImage(5));
            }case 111 ->{
                return new ImageIcon(scoreImage(6));
            }case 112 ->{
                return new ImageIcon(scoreImage(7));
            }case 122 ->{
                return new ImageIcon(scoreImage(8));
            }default -> {
                return null;
            }
        }
    }

    static public BufferedImage scaleImage(BufferedImage original) {
        BufferedImage scaledImage = new BufferedImage(96, 96, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, 96, 96, null);
        g2.dispose();
        return scaledImage;
    }

    static public String newImage(BufferedImage image, String path, String suffix) {
        int fileCount = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path), "*.{" + suffix + "}")) {
            for (Path ignored : stream) {
                fileCount++;
            }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
        }
        fileCount++;
        String pngID = String.valueOf(fileCount);
        try {
            File temp = File.createTempFile(path + "/", suffix);
            File newFile = new File(temp, pngID);
            ImageIO.write(image, suffix, newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pngID;
    }

    static public BufferedImage loadImage(String imageID) {
        BufferedImage bi;
        try {
            bi = ImageIO.read(new File("src/resources/images/avatar/" + imageID + ".png"));
            return StaticImageHandler.scaleImage(bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ImageIcon getIcon(String imageID) {
        return new ImageIcon(loadImage(imageID));
    }
    public static ImageIcon getIconNonScaled(String pngID){
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File("src/resources/images/avatar/" + pngID + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon(bi);
    }
}
