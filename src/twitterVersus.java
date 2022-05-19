import twitter4j.*;

import javax.swing.*;

//import.twitter4j.
import java.io.IOException;
import java.util.Scanner;

public class twitterVersus {
    public static void main (String[] args) throws TwitterException {
//        MyFrame frame = new MyFrame();

        Twitter twitter = TwitterFactory.getSingleton();

//        String name = scanner.nextLine();
//        Status status = twitter.updateStatus("Successively tested Twitter4J 3");
//        status = twitter.updateStatus(String.valueOf(status.getCreatedAt()));
//        System.out.println("test end");

//        String ba = "KR_BlueArchive";
//        User user = twitter.showUser(ba);
//        if (user.getStatus() != null) {
//            int follower = user.getFollowersCount();
//            Status status = twitter.updateStatus("Number of followers of " + ba + " is " + follower);
//        }

        Scanner scanner = new Scanner(System.in);
        User testUser = twitter.showUser(scanner.nextLine());
        
    }
}