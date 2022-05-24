import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.TwitterException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class twitterVersusGUI {
    String handle;
    Twitter twitter = TwitterFactory.getSingleton();
    twitterVersusGUI(String input) {
        handle = input;
    }

    //download user profile image
    public void urlToImage() throws TwitterException, IOException {
        User user = twitter.showUser(handle);
        BufferedImage img = ImageIO.read(new URL(user.get400x400ProfileImageURLHttps()));
        File file = new File("./profileImage.png");
        file.createNewFile();
        ImageIO.write(img, "png", file);
    }
}
