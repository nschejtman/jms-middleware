package com.nschejtman.jms;

import com.nschejtman.client.console.Logger;
import com.nschejtman.model.Instatweet;
import com.nschejtman.model.User;
import com.nschejtman.utils.IdGenerator;
import com.nschejtman.utils.UserDao;

import javax.imageio.ImageIO;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class JMSTweetListener implements MessageListener {
    private User receiver;

    JMSTweetListener(User receiver) {
        this.receiver = receiver;
    }

    public void onMessage(Message message) {
        try {
            //Save the image file
            final Instatweet tweet = message.getBody(Instatweet.class);
            final byte[] image = tweet.getImage();
            final File uploadDir = new File("/Users/nicolas/projects/jms-middleware/uploads");
            //noinspection StatementWithEmptyBody
            if (!uploadDir.exists()) {
                //create it
            } else {
                final String newImagePath = uploadDir.getAbsolutePath() + "/" + IdGenerator.next("image") + ".png";
                Logger.info(newImagePath);
                final File targetImageFile = new File(newImagePath);
                final ByteArrayInputStream bais = new ByteArrayInputStream(image);
                final BufferedImage bufferedImage = ImageIO.read(bais);
                ImageIO.write(bufferedImage, "png", targetImageFile);
            }

            //Application part
            receiver.addToTimeline(tweet);
            UserDao.update(receiver);

        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }

    }
}
