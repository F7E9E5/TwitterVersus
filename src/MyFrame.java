import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MyFrame extends Component implements ActionListener {
    JFrame mainFrame = new JFrame("Twitter Versus");
    JPanel homePanel = new JPanel();
    JPanel resultPanel = new JPanel();
    JTextField firstUserHandleInput;
    JTextField secondUserHandleInput;
    JButton returnButton;
    JButton backButton = new JButton("back");
    JLabel invalidText = new JLabel();
    Font font = new Font(null, Font.BOLD, 20);
    Font invalidFont = new Font(null, Font.ITALIC, 20);
    GridBagLayout homeLayout = new GridBagLayout();
    GridBagLayout resultLayout = new GridBagLayout();

    public MyFrame() {
        mainFrame.setVisible(true);
        mainFrame.setPreferredSize(new Dimension(900, 600));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(homePanel);

        homePanel.setLayout(homeLayout);
        homePanel.setPreferredSize(new Dimension(900, 600));

        setInput();

        JLabel emptyText1 = new JLabel("");
        emptyText1.setPreferredSize(new Dimension(230, 130));
        GridBagConstraints emptyText1gbc = new GridBagConstraints();
        emptyText1gbc.gridy = 0;
        emptyText1gbc.gridx = 1;
        homePanel.add(emptyText1, emptyText1gbc);

        JLabel emptyText2 = new JLabel("");
        emptyText2.setPreferredSize(new Dimension(230, 100));
        GridBagConstraints emptyText2gbc = new GridBagConstraints();
        emptyText2gbc.gridy = 3;
        emptyText2gbc.gridx = 1;
        homePanel.add(emptyText2, emptyText2gbc);

        //returnButton
        returnButton = new JButton("return");
        returnButton.addActionListener(this::actionPerformed);
        returnButton.setPreferredSize(new Dimension(100, 60));
        GridBagConstraints returnButtongbc = new GridBagConstraints();
        returnButtongbc.gridx = 1;
        returnButtongbc.gridy = 2;
        homePanel.add(returnButton, returnButtongbc);
        returnButton.setVisible(true);

        //invalidText
        invalidText.setFont(invalidFont);
        invalidText.setForeground(Color.red);
        invalidText.setText("Invalid input, Please retry");
        GridBagConstraints invalidTextgbc = new GridBagConstraints();
        invalidTextgbc.gridx = 1;
        invalidTextgbc.gridy = 3;
        homePanel.add(invalidText, invalidTextgbc);
        invalidText.setVisible(false);


        homePanel.setVisible(true);
        mainFrame.pack();
    }

    public void displayResult(String firstUserHandle, String secondUserHandle) throws IOException, TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();

        homePanel.setVisible(false);

        resultPanel = new JPanel();
        resultPanel.setPreferredSize(new Dimension(900, 600));
        resultPanel.setLayout(resultLayout);

        mainFrame.add(resultPanel);

        //back button
        backButton.addActionListener(this::actionPerformed);
        backButton.setPreferredSize(new Dimension(70, 30));
        GridBagConstraints backButtongbc = new GridBagConstraints();
        backButtongbc.gridy = 0;
        backButtongbc.gridx = 0;
        resultPanel.add(backButton, backButtongbc);
        backButton.setVisible(true);

        //first user handle
        JLabel firstUserHandleLabel = new JLabel(firstUserHandle);
        firstUserHandleLabel.setFont(font);
        firstUserHandleLabel.setHorizontalAlignment(JLabel.CENTER);
        firstUserHandleLabel.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints firstUserHandleLabelgbc = new GridBagConstraints();
        firstUserHandleLabelgbc.gridy = 0;
        firstUserHandleLabelgbc.gridx = 1;
        resultPanel.add(firstUserHandleLabel, firstUserHandleLabelgbc);
        firstUserHandleLabel.setVisible(true);

        JLabel vsText = new JLabel("VS");
        vsText.setFont(font);
        vsText.setHorizontalAlignment(JLabel.CENTER);
        vsText.setPreferredSize(new Dimension(100, 30));
        GridBagConstraints vsTextgbc = new GridBagConstraints();
        vsTextgbc.gridy = 0;
        vsTextgbc.gridx = 2;
        resultPanel.add(vsText, vsTextgbc);
        vsText.setVisible(true);

        //second user handle
        JLabel secondUserHandleLabel = new JLabel(secondUserHandle);
        int secondUserHandleLength = secondUserHandle.length();
        secondUserHandleLabel.setFont(font);
        secondUserHandleLabel.setHorizontalAlignment(JLabel.CENTER);
        secondUserHandleLabel.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints secondUserHandleLabelgbc = new GridBagConstraints();
        secondUserHandleLabelgbc.gridy = 0;
        secondUserHandleLabelgbc.gridx = 3;
        resultPanel.add(secondUserHandleLabel, secondUserHandleLabelgbc);
        secondUserHandleLabel.setVisible(true);

        //user profile image
        User firstUser = twitter.showUser(firstUserHandle);
        BufferedImage firstUserProfileImage = ImageIO.read(new URL(firstUser.get400x400ProfileImageURLHttps()));
        Image resizedFirstUserProfileImage = firstUserProfileImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel firstUserProfileImageLabel = new JLabel(new ImageIcon(resizedFirstUserProfileImage));
        firstUserProfileImageLabel.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints firstUserProfileImageLabelgbc = new GridBagConstraints();
        firstUserProfileImageLabelgbc.gridy = 1;
        firstUserProfileImageLabelgbc.gridx = 1;
        resultPanel.add(firstUserProfileImageLabel, firstUserProfileImageLabelgbc);
        firstUserProfileImageLabel.setVisible(true);

        User secondUser = twitter.showUser(secondUserHandle);
        BufferedImage secondUserProfileImage = ImageIO.read(new URL(secondUser.get400x400ProfileImageURLHttps()));
        Image resizedSecondUserProfileImage = secondUserProfileImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel secondUserProfileImageLabel = new JLabel(new ImageIcon(resizedSecondUserProfileImage));
        secondUserProfileImageLabel.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints secondUserProfileImageLabelgbc = new GridBagConstraints();
        secondUserProfileImageLabelgbc.gridy = 1;
        secondUserProfileImageLabelgbc.gridx = 3;
        resultPanel.add(secondUserProfileImageLabel, secondUserProfileImageLabelgbc);
        secondUserProfileImageLabel.setVisible(true);

        //user twitter domain

        //make day, birthday, region

        //follower graph

        //# of tweet graph

        resultPanel.setVisible(true);
        mainFrame.pack();
    }

    public boolean checkHandle(String a, String b) throws TwitterException {
        System.out.println("checking " + a + "  " + b);
        Twitter twitter = TwitterFactory.getSingleton();
        User userA = twitter.showUser(a);
        User userB = twitter.showUser(b);
        return (userA.getStatus() != null && userB.getStatus() != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton) {
            String a = "sontaeho15", b = "KR_BlueArchive";
//            try {
//                a = firstUserHandleInput.getText();
//                b = secondUserHandleInput.getText();
//            } catch (NullPointerException ex) {
//                a = b = "";
//            }

            try {
                if (checkHandle(a, b)) {
                    invalidText.setVisible(false);
                    displayResult(a, b);
                }
            } catch (TwitterException ex) {
                invalidText.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == backButton) {
            resultPanel.setVisible(false);
            setInput();
            homePanel.setVisible(true);
        }
    }

    public void setInput() {
        try {
            homePanel.remove(firstUserHandleInput);
            homePanel.remove(secondUserHandleInput);
        } catch (NullPointerException ignored) {}

        firstUserHandleInput = new JTextField("First User Handle");
        firstUserHandleInput.setFont(font);
        firstUserHandleInput.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints firstgbc = new GridBagConstraints();
        firstgbc.gridx = 0;
        firstgbc.gridy = 0;
        homePanel.add(firstUserHandleInput, firstgbc);
        firstUserHandleInput.setVisible(true);

        secondUserHandleInput = new JTextField("Second User Handle");
        secondUserHandleInput.setFont(font);
        secondUserHandleInput.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints secondgbc = new GridBagConstraints();
        secondgbc.gridx = 2;
        secondgbc.gridy = 0;
        homePanel.add(secondUserHandleInput, secondgbc);
        secondUserHandleInput.setVisible(true);
    }
}
