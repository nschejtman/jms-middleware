package com.nschejtman.model;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Instatweet implements Comparable<Instatweet>, Serializable {
    private User user;
    private String text;
    private BufferedImage image;
    private String imageName;
    private DateTime dateTime;
    private static List<String> validExtensions = new ArrayList<String>() {{add("jpg"); add("gif"); }};

    public Instatweet() {
    }

    @Override
    public String toString() {
        return "Instatweet{" +
                "user=" + user +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    public Instatweet(User user, String text, String imagePath, DateTime dateTime) throws IOException, IllegalArgumentException {
        this.user = user;
        this.text = text;
        final File auxFile = new File(imagePath);
        final String ext = FilenameUtils.getExtension(imagePath);
        if (!validExtensions.contains(ext)){
            throw new IllegalArgumentException("File is not an image");
        }
        if(!auxFile.exists()){
            throw new IllegalArgumentException("Image path does not exist");
        } else {
            this.imageName = auxFile.getName();
            image = ImageIO.read(auxFile);
        }

        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int compareTo(Instatweet o) {
        return (-1) * dateTime.compareTo(o.dateTime);
    }

    public String getImageName() {
        return imageName;
    }
}
