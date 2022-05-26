import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultPanel extends JPanel {
    JButton backButton;
    final private Font font = new Font(null, Font.BOLD, 20);
//    final private String firstUserHandle, secondUserHandle;
    final private User firstUser, secondUser;

    ResultPanel(String first, String second) throws TwitterException, IOException {
        Twitter twitter = TwitterFactory.getSingleton();
//        firstUserHandle = first;
//        secondUserHandle = second;
        firstUser = twitter.showUser(first);
        secondUser = twitter.showUser(second);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(900, 600));

        addBackButton();
        addName();
        addVs();

        addHandle();

        addProfileImage();

        addCreated();

        addGraph();

        addPadding();
    }

    private void addBackButton() {
        backButton = new JButton("back");
        backButton.setPreferredSize(new Dimension(70, 30));
        GridBagConstraints backButtonGbc = new GridBagConstraints();
        backButtonGbc.insets = new Insets(5, 10, 0, 10);
        backButtonGbc.gridy = 0;
        backButtonGbc.gridx = 0;
        add(backButton, backButtonGbc);
        backButton.setVisible(true);
    }

    private void addName() {
        JLabel firstUserNameLabel = new JLabel(firstUser.getName());
        firstUserNameLabel.setFont(font);
        GridBagConstraints firstUserNameLabelGbc = new GridBagConstraints();
        firstUserNameLabelGbc.gridy = 0;
        firstUserNameLabelGbc.gridx = 1;
        add(firstUserNameLabel, firstUserNameLabelGbc);
        firstUserNameLabel.setVisible(true);

        JLabel secondUserNameLabel = new JLabel(secondUser.getName());
        secondUserNameLabel.setFont(font);
        GridBagConstraints secondUserNameLabelGbc = new GridBagConstraints();
        secondUserNameLabelGbc.gridy = 0;
        secondUserNameLabelGbc.gridx = 3;
        add(secondUserNameLabel, secondUserNameLabelGbc);
        secondUserNameLabel.setVisible(true);
    }

    private void addVs() {
        JLabel vsText = new JLabel("VS");
        vsText.setFont(font);
        vsText.setHorizontalAlignment(JLabel.CENTER);
        vsText.setPreferredSize(new Dimension(100, 30));
        GridBagConstraints vsTextGbc = new GridBagConstraints();
        vsTextGbc.gridy = 0;
        vsTextGbc.gridx = 2;
        add(vsText, vsTextGbc);
        vsText.setVisible(true);
    }

    private void addHandle() {
        JLabel firstUserHandleLabel = new JLabel("@" + firstUser.getScreenName());
        firstUserHandleLabel.setFont(font);
        firstUserHandleLabel.setHorizontalAlignment(JLabel.CENTER);
        firstUserHandleLabel.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints firstUserHandleLabelGbc = new GridBagConstraints();
        firstUserHandleLabelGbc.insets = new Insets(5, 10, 0, 10);
        firstUserHandleLabelGbc.gridy = 1;
        firstUserHandleLabelGbc.gridx = 1;
        add(firstUserHandleLabel, firstUserHandleLabelGbc);
        firstUserHandleLabel.setVisible(true);

        String firstUserUrl = "https://twitter.com/" + firstUser.getScreenName();
        firstUserHandleLabel.setForeground(Color.BLUE.darker());
        firstUserHandleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        firstUserHandleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(firstUserUrl));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel secondUserHandleLabel = new JLabel("@" + secondUser.getScreenName());
        secondUserHandleLabel.setFont(font);
        secondUserHandleLabel.setHorizontalAlignment(JLabel.CENTER);
        secondUserHandleLabel.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints secondUserHandleLabelGbc = new GridBagConstraints();
        secondUserHandleLabelGbc.insets = new Insets(5, 10, 0, 10);
        secondUserHandleLabelGbc.gridy = 1;
        secondUserHandleLabelGbc.gridx = 3;
        add(secondUserHandleLabel, secondUserHandleLabelGbc);
        secondUserHandleLabel.setVisible(true);

        String secondUserUrl = "https://twitter.com/" + secondUser.getScreenName();
        secondUserHandleLabel.setForeground(Color.BLUE.darker());
        secondUserHandleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        secondUserHandleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(secondUserUrl));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void addProfileImage() throws IOException {
        BufferedImage firstUserProfileImage = ImageIO.read(new URL(firstUser.get400x400ProfileImageURLHttps()));
        Image resizedFirstUserProfileImage = firstUserProfileImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel firstUserProfileImageLabel = new JLabel(new ImageIcon(resizedFirstUserProfileImage));
        firstUserProfileImageLabel.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints firstUserProfileImageLabelGbc = new GridBagConstraints();
        firstUserProfileImageLabelGbc.gridy = 3;
        firstUserProfileImageLabelGbc.gridx = 1;
        add(firstUserProfileImageLabel, firstUserProfileImageLabelGbc);
        firstUserProfileImageLabel.setVisible(true);

        BufferedImage secondUserProfileImage = ImageIO.read(new URL(secondUser.get400x400ProfileImageURLHttps()));
        Image resizedSecondUserProfileImage = secondUserProfileImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel secondUserProfileImageLabel = new JLabel(new ImageIcon(resizedSecondUserProfileImage));
        secondUserProfileImageLabel.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints secondUserProfileImageLabelGbc = new GridBagConstraints();
        secondUserProfileImageLabelGbc.gridy = 3;
        secondUserProfileImageLabelGbc.gridx = 3;
        add(secondUserProfileImageLabel, secondUserProfileImageLabelGbc);
        secondUserProfileImageLabel.setVisible(true);
    }

    private void addPadding() {
        JLabel row0padding = new JLabel();
        row0padding.setPreferredSize(backButton.getPreferredSize());
        GridBagConstraints row0paddingGbc = new GridBagConstraints();
        row0paddingGbc.gridy = 0;
        row0paddingGbc.gridx = 4;
        add(row0padding, row0paddingGbc);
        row0padding.setVisible(true);
    }

    private void addCreated() {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JLabel createdText = new JLabel("Created at");
        createdText.setFont(font);
        GridBagConstraints createdTextGbc = new GridBagConstraints();
        createdTextGbc.gridy = 4;
        createdTextGbc.gridx = 0;
        add(createdText, createdTextGbc);
        createdText.setVisible(true);

        Date firstUserCreated = firstUser.getCreatedAt();
        JLabel firstUserCreatedLabel = new JLabel(transFormat.format(firstUserCreated));
        firstUserCreatedLabel.setFont(font);
        GridBagConstraints firstUserCreatedLabelGbc = new GridBagConstraints();
        firstUserCreatedLabelGbc.gridy = 4;
        firstUserCreatedLabelGbc.gridx = 1;
        add(firstUserCreatedLabel, firstUserCreatedLabelGbc);
        firstUserCreatedLabel.setVisible(true);

        Date secondUserCreated = secondUser.getCreatedAt();
        JLabel secondUserCreatedLabel = new JLabel(transFormat.format(secondUserCreated));
        secondUserCreatedLabel.setFont(font);
        GridBagConstraints secondUserCreatedLabelGbc = new GridBagConstraints();
        secondUserCreatedLabelGbc.gridy = 4;
        secondUserCreatedLabelGbc.gridx = 3;
        add(secondUserCreatedLabel, secondUserCreatedLabelGbc);
        secondUserCreatedLabel.setVisible(true);
    }

    private void addGraph() {
        int firstUserFollowerCount = firstUser.getFollowersCount();
        int secondUserFollowerCount = secondUser.getFollowersCount();
        myGraph followerGraph = new myGraph(firstUserFollowerCount, secondUserFollowerCount);
        GridBagConstraints followerGraphGbc = new GridBagConstraints();
        followerGraphGbc.gridy = 5;
        followerGraphGbc.gridx = 1;
        followerGraphGbc.gridwidth = 3;
        followerGraphGbc.gridheight = 1;
        add(followerGraph, followerGraphGbc);
        followerGraph.setVisible(true);

//        Query query = new Query(search);
//        ArrayList tweets = (ArrayList)re
//        int firstUserTweetCount = firstUser.get
    }
}
