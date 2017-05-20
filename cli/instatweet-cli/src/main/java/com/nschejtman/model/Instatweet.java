package com.nschejtman.model;

import com.nschejtman.client.console.Logger;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Instatweet implements Comparable<Instatweet>, Serializable {
    private User user;
    private String text;
    private byte[] image;
    private String imageName;
    private DateTime dateTime;

    @Override
    public String toString() {
        return "Instatweet{" +
                "user=" + user +
                ", text='" + text + '\'' +
                ", imageName='" + imageName + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    public Instatweet(User user, String text, String imagePath, DateTime dateTime) throws IOException {
        this.user = user;
        this.text = text;
        this.imageName = FilenameUtils.getName(imagePath);
        final BufferedImage image = ImageIO.read(new File(imagePath));
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();
        this.image = baos.toByteArray();
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

    public byte[] getImage() {
        return image;
    }
}
