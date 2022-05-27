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
    final private User firstUser, secondUser;

    ResultPanel(String first, String second) throws TwitterException, IOException {
        Twitter twitter = TwitterFactory.getSingleton();
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
        backButtonGbc.insets = new Insets(5, 5, 5, 5);
        backButtonGbc.gridy = 0;
        backButtonGbc.gridx = 0;
        add(backButton, backButtonGbc);
        backButton.setVisible(true);
    }

    private void addName() {
        JLabel firstUserNameLabel = new JLabel(firstUser.getName());
        firstUserNameLabel.setFont(font);
        GridBagConstraints firstUserNameLabelGbc = new GridBagConstraints();
        firstUserNameLabelGbc.insets = new Insets(5, 5, 5, 5);
        firstUserNameLabelGbc.gridy = 0;
        firstUserNameLabelGbc.gridx = 1;
        add(firstUserNameLabel, firstUserNameLabelGbc);
        firstUserNameLabel.setVisible(true);

        JLabel secondUserNameLabel = new JLabel(secondUser.getName());
        secondUserNameLabel.setFont(font);
        GridBagConstraints secondUserNameLabelGbc = new GridBagConstraints();
        secondUserNameLabelGbc.insets = new Insets(5, 5, 5, 5);
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
        vsTextGbc.insets = new Insets(5, 5, 5, 5);
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
        firstUserHandleLabelGbc.insets = new Insets(5, 5, 5, 5);
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
        secondUserHandleLabelGbc.insets = new Insets(5, 5, 5, 5);
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
        firstUserProfileImageLabelGbc.insets = new Insets(5, 5, 5, 5);
        firstUserProfileImageLabelGbc.gridy = 3;
        firstUserProfileImageLabelGbc.gridx = 1;
        add(firstUserProfileImageLabel, firstUserProfileImageLabelGbc);
        firstUserProfileImageLabel.setVisible(true);

        BufferedImage secondUserProfileImage = ImageIO.read(new URL(secondUser.get400x400ProfileImageURLHttps()));
        Image resizedSecondUserProfileImage = secondUserProfileImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel secondUserProfileImageLabel = new JLabel(new ImageIcon(resizedSecondUserProfileImage));
        secondUserProfileImageLabel.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints secondUserProfileImageLabelGbc = new GridBagConstraints();
        secondUserProfileImageLabelGbc.insets = new Insets(5, 5, 5, 5);
        secondUserProfileImageLabelGbc.gridy = 3;
        secondUserProfileImageLabelGbc.gridx = 3;
        add(secondUserProfileImageLabel, secondUserProfileImageLabelGbc);
        secondUserProfileImageLabel.setVisible(true);
    }

    private void addPadding() {
        JLabel row0padding = new JLabel();
        row0padding.setPreferredSize(backButton.getPreferredSize());
        GridBagConstraints row0paddingGbc = new GridBagConstraints();
        row0paddingGbc.insets = new Insets(5, 5, 5, 5);
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
        createdTextGbc.insets = new Insets(5, 5, 5, 5);
        createdTextGbc.gridy = 4;
        createdTextGbc.gridx = 0;
        add(createdText, createdTextGbc);
        createdText.setVisible(true);

        Date firstUserCreated = firstUser.getCreatedAt();
        JLabel firstUserCreatedLabel = new JLabel(transFormat.format(firstUserCreated));
        firstUserCreatedLabel.setFont(font);
        GridBagConstraints firstUserCreatedLabelGbc = new GridBagConstraints();
        firstUserCreatedLabelGbc.insets = new Insets(5, 5, 5, 5);
        firstUserCreatedLabelGbc.gridy = 4;
        firstUserCreatedLabelGbc.gridx = 1;
        add(firstUserCreatedLabel, firstUserCreatedLabelGbc);
        firstUserCreatedLabel.setVisible(true);

        Date secondUserCreated = secondUser.getCreatedAt();
        JLabel secondUserCreatedLabel = new JLabel(transFormat.format(secondUserCreated));
        secondUserCreatedLabel.setFont(font);
        GridBagConstraints secondUserCreatedLabelGbc = new GridBagConstraints();
        secondUserCreatedLabelGbc.insets = new Insets(5, 5, 5, 5);
        secondUserCreatedLabelGbc.gridy = 4;
        secondUserCreatedLabelGbc.gridx = 3;
        add(secondUserCreatedLabel, secondUserCreatedLabelGbc);
        secondUserCreatedLabel.setVisible(true);
    }

    private void addGraph() {
        JLabel followerText = new JLabel("Followers");
        followerText.setFont(font);
        GridBagConstraints followerTextGbc = new GridBagConstraints();
        followerTextGbc.insets = new Insets(5, 5, 5, 5);
        followerTextGbc.gridy = 5;
        followerTextGbc.gridx = 0;
        add(followerText, followerTextGbc);
        followerText.setVisible(true);

        int firstUserFollowerCount = firstUser.getFollowersCount();
        int secondUserFollowerCount = secondUser.getFollowersCount();

        JLabel firstUserFollowerLabel = new JLabel(Integer.toString(firstUserFollowerCount));
        GridBagConstraints firstUserFollowerLabelGbc = new GridBagConstraints();
        firstUserFollowerLabelGbc.insets = new Insets(5, 5, 5, 5);
        firstUserFollowerLabelGbc.gridy = 5;
        firstUserFollowerLabelGbc.gridx = 1;
        add(firstUserFollowerLabel, firstUserFollowerLabelGbc);
        firstUserFollowerLabel.setHorizontalAlignment(JLabel.LEFT);
        firstUserFollowerLabel.setVisible(true);

        JLabel secondUserFollowerLabel = new JLabel(Integer.toString(secondUserFollowerCount));
        GridBagConstraints secondUserFollowerLabelGbc = new GridBagConstraints();
        secondUserFollowerLabelGbc.insets = new Insets(5, 5, 5, 5);
        secondUserFollowerLabelGbc.gridy = 5;
        secondUserFollowerLabelGbc.gridx = 3;
        add(secondUserFollowerLabel, secondUserFollowerLabelGbc);
        secondUserFollowerLabel.setHorizontalAlignment(JLabel.RIGHT);
        secondUserFollowerLabel.setVisible(true);

        myGraph followerGraph = new myGraph(firstUserFollowerCount, secondUserFollowerCount);
        GridBagConstraints followerGraphGbc = new GridBagConstraints();
        followerGraphGbc.insets = new Insets(5, 5, 5, 5);
        followerGraphGbc.gridy = 5;
        followerGraphGbc.gridx = 1;
        followerGraphGbc.gridwidth = 3;
        followerGraphGbc.gridheight = 1;
        add(followerGraph, followerGraphGbc);
        followerGraph.setVisible(true);

        JLabel followingText = new JLabel("Following");
        followingText.setFont(font);
        GridBagConstraints followingTextGbc = new GridBagConstraints();
        followingTextGbc.insets = new Insets(5, 5, 5, 5);
        followingTextGbc.gridy = 6;
        followingTextGbc.gridx = 0;
        add(followingText, followingTextGbc);
        followingText.setVisible(true);

        int firstUserFollowingCount = firstUser.getFriendsCount();
        int secondUserFollowingCount = secondUser.getFriendsCount();

        JLabel firstUserFollowingLabel = new JLabel(Integer.toString(firstUserFollowingCount));
        GridBagConstraints firstUserFollowingLabelGbc = new GridBagConstraints();
        firstUserFollowingLabelGbc.insets = new Insets(5, 5, 5, 5);
        firstUserFollowingLabelGbc.gridy = 6;
        firstUserFollowingLabelGbc.gridx = 1;
        add(firstUserFollowingLabel, firstUserFollowingLabelGbc);
        firstUserFollowingLabel.setHorizontalAlignment(JLabel.LEFT);
        firstUserFollowingLabel.setVisible(true);

        JLabel secondUserFollowingLabel = new JLabel(Integer.toString(secondUserFollowingCount));
        GridBagConstraints secondUserFollowingLabelGbc = new GridBagConstraints();
        secondUserFollowingLabelGbc.insets = new Insets(5, 5, 5, 5);
        secondUserFollowingLabelGbc.gridy = 6;
        secondUserFollowingLabelGbc.gridx = 3;
        add(secondUserFollowingLabel, secondUserFollowingLabelGbc);
        secondUserFollowingLabel.setHorizontalAlignment(JLabel.RIGHT);
        secondUserFollowingLabel.setVisible(true);

        myGraph followingGraph = new myGraph(firstUserFollowingCount, secondUserFollowingCount);
        GridBagConstraints followingGraphGbc = new GridBagConstraints();
        followingGraphGbc.insets = new Insets(5, 5, 5, 5);
        followingGraphGbc.gridy = 6;
        followingGraphGbc.gridx = 1;
        followingGraphGbc.gridwidth = 3;
        add(followingGraph, followingGraphGbc);
        followerGraph.setVisible(true);

        JLabel statusText = new JLabel("Tweets");
        statusText.setFont(font);
        GridBagConstraints statusTextGbc = new GridBagConstraints();
        statusTextGbc.insets = new Insets(5, 5, 5, 5);
        statusTextGbc.gridy = 7;
        statusTextGbc.gridx = 0;
        add(statusText, statusTextGbc);
        statusText.setVisible(true);

        int firstUserStatusCount = firstUser.getStatusesCount();
        int secondUserStatusCount = secondUser.getStatusesCount();

        JLabel firstUserStatusLabel = new JLabel(Integer.toString(firstUserStatusCount));
        GridBagConstraints firstUserStatusLabelGbc = new GridBagConstraints();
        firstUserStatusLabelGbc.insets = new Insets(5, 5, 5, 5);
        firstUserStatusLabelGbc.gridy = 7;
        firstUserStatusLabelGbc.gridx = 1;
        add(firstUserStatusLabel, firstUserStatusLabelGbc);
        firstUserStatusLabel.setHorizontalAlignment(JLabel.LEFT);
        firstUserStatusLabel.setVisible(true);

        JLabel secondUserStatusLabel = new JLabel(Integer.toString(secondUserStatusCount));
        GridBagConstraints secondUserStatusLabelGbc = new GridBagConstraints();
        secondUserStatusLabelGbc.insets = new Insets(5, 5, 5, 5);
        secondUserStatusLabelGbc.gridy = 7;
        secondUserStatusLabelGbc.gridx = 3;
        add(secondUserStatusLabel, secondUserStatusLabelGbc);
        secondUserStatusLabel.setHorizontalAlignment(JLabel.RIGHT);
        secondUserStatusLabel.setVisible(true);

        myGraph statusGraph = new myGraph(firstUserStatusCount, secondUserStatusCount);
        GridBagConstraints statusGraphGbc = new GridBagConstraints();
        statusGraphGbc.insets = new Insets(5, 5, 5, 5);
        statusGraphGbc.gridy = 7;
        statusGraphGbc.gridx = 1;
        statusGraphGbc.gridwidth = 3;
        add(statusGraph, statusGraphGbc);
        statusGraph.setVisible(true);
    }
}
