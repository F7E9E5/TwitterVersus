import twitter4j.Status;
import twitter4j.TwitterException;
import javax.swing.*;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
//import.twitter4j.
import java.io.IOException;
import java.util.Scanner;

public class twitterVersus {
    public static void main (String[] args) throws TwitterException {
//        MyFrame frame = new MyFrame();

//        Scanner scanner = new Scanner(System.in);
//        String name = scanner.nextLine();

        Twitter twitter = TwitterFactory.getSingleton();
//        Twitterer me = new Twitterer(name);
        Status status = twitter.updateStatus("Successively tested Twitter4J");
        System.out.println("test end");
    }
}