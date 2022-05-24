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
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        returnButton.addActionListener(this);
        returnButton.setPreferredSize(new Dimension(100, 60));
        GridBagConstraints returnButtonGbc = new GridBagConstraints();
        returnButtonGbc.gridx = 1;
        returnButtonGbc.gridy = 2;
        homePanel.add(returnButton, returnButtonGbc);
        returnButton.setVisible(true);

        //invalidText
        invalidText.setFont(invalidFont);
        invalidText.setForeground(Color.red);
        invalidText.setText("Invalid input, Please retry");
        GridBagConstraints invalidTextGbc = new GridBagConstraints();
        invalidTextGbc.gridx = 1;
        invalidTextGbc.gridy = 3;
        homePanel.add(invalidText, invalidTextGbc);
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
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(70, 30));
        GridBagConstraints backButtonGbc = new GridBagConstraints();
        backButtonGbc.insets = new Insets(5, 10, 0, 10);
        backButtonGbc.gridy = 0;
        backButtonGbc.gridx = 0;
        resultPanel.add(backButton, backButtonGbc);
        backButton.setVisible(true);

        //User declaration
        User firstUser = twitter.showUser(firstUserHandle);
        User secondUser = twitter.showUser(secondUserHandle);

        JLabel firstUserNameLabel = new JLabel(firstUser.getName());
        firstUserNameLabel.setFont(font);
        GridBagConstraints firstUserNameLabelGbc = new GridBagConstraints();
        firstUserNameLabelGbc.gridy = 0;
        firstUserNameLabelGbc.gridx = 1;
        resultPanel.add(firstUserNameLabel, firstUserNameLabelGbc);
        firstUserNameLabel.setVisible(true);

        JLabel secondUserNameLabel = new JLabel(secondUser.getName());
        secondUserNameLabel.setFont(font);
        GridBagConstraints secondUserNameLabelGbc = new GridBagConstraints();
        secondUserNameLabelGbc.gridy = 0;
        secondUserNameLabelGbc.gridx = 3;
        resultPanel.add(secondUserNameLabel, secondUserNameLabelGbc);
        secondUserNameLabel.setVisible(true);

        //vs
        JLabel vsText = new JLabel("VS");
        vsText.setFont(font);
        vsText.setHorizontalAlignment(JLabel.CENTER);
        vsText.setPreferredSize(new Dimension(100, 30));
        GridBagConstraints vsTextGbc = new GridBagConstraints();
        vsTextGbc.gridy = 0;
        vsTextGbc.gridx = 2;
        resultPanel.add(vsText, vsTextGbc);
        vsText.setVisible(true);

        //first user handle
        JLabel firstUserHandleLabel = new JLabel(firstUserHandle);
        firstUserHandleLabel.setFont(font);
        firstUserHandleLabel.setHorizontalAlignment(JLabel.CENTER);
        firstUserHandleLabel.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints firstUserHandleLabelGbc = new GridBagConstraints();
        firstUserHandleLabelGbc.insets = new Insets(5, 10, 0, 10);
        firstUserHandleLabelGbc.gridy = 1;
        firstUserHandleLabelGbc.gridx = 1;
        resultPanel.add(firstUserHandleLabel, firstUserHandleLabelGbc);
        firstUserHandleLabel.setVisible(true);

        //second user handle
        JLabel secondUserHandleLabel = new JLabel(secondUserHandle);
        secondUserHandleLabel.setFont(font);
        secondUserHandleLabel.setHorizontalAlignment(JLabel.CENTER);
        secondUserHandleLabel.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints secondUserHandleLabelGbc = new GridBagConstraints();
        secondUserHandleLabelGbc.insets = new Insets(5, 10, 0, 10);
        secondUserHandleLabelGbc.gridy = 1;
        secondUserHandleLabelGbc.gridx = 3;
        resultPanel.add(secondUserHandleLabel, secondUserHandleLabelGbc);
        secondUserHandleLabel.setVisible(true);

        JLabel row0padding = new JLabel();
        row0padding.setPreferredSize(backButton.getPreferredSize());
        GridBagConstraints row0paddingGbc = new GridBagConstraints();
        row0paddingGbc.gridy = 0;
        row0paddingGbc.gridx = 4;
        resultPanel.add(row0padding, row0paddingGbc);
        row0padding.setVisible(true);

        //user profile image
        BufferedImage firstUserProfileImage = ImageIO.read(new URL(firstUser.get400x400ProfileImageURLHttps()));
        Image resizedFirstUserProfileImage = firstUserProfileImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel firstUserProfileImageLabel = new JLabel(new ImageIcon(resizedFirstUserProfileImage));
        firstUserProfileImageLabel.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints firstUserProfileImageLabelGbc = new GridBagConstraints();
        firstUserProfileImageLabelGbc.gridy = 2;
        firstUserProfileImageLabelGbc.gridx = 1;
        resultPanel.add(firstUserProfileImageLabel, firstUserProfileImageLabelGbc);
        firstUserProfileImageLabel.setVisible(true);

        BufferedImage secondUserProfileImage = ImageIO.read(new URL(secondUser.get400x400ProfileImageURLHttps()));
        Image resizedSecondUserProfileImage = secondUserProfileImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel secondUserProfileImageLabel = new JLabel(new ImageIcon(resizedSecondUserProfileImage));
        secondUserProfileImageLabel.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints secondUserProfileImageLabelGbc = new GridBagConstraints();
        secondUserProfileImageLabelGbc.gridy = 2;
        secondUserProfileImageLabelGbc.gridx = 3;
        resultPanel.add(secondUserProfileImageLabel, secondUserProfileImageLabelGbc);
        secondUserProfileImageLabel.setVisible(true);

        //created date
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JLabel createdText = new JLabel("Created at");
        createdText.setFont(font);
        GridBagConstraints createdTextGbc = new GridBagConstraints();
        createdTextGbc.gridy = 3;
        createdTextGbc.gridx = 0;
        resultPanel.add(createdText, createdTextGbc);
        createdText.setVisible(true);

        Date firstUserCreated = firstUser.getCreatedAt();
        JLabel firstUserCreatedLabel = new JLabel(transFormat.format(firstUserCreated));
        firstUserCreatedLabel.setFont(font);
        GridBagConstraints firstUserCreatedLabelGbc = new GridBagConstraints();
        firstUserCreatedLabelGbc.gridy = 3;
        firstUserCreatedLabelGbc.gridx = 1;
        resultPanel.add(firstUserCreatedLabel, firstUserCreatedLabelGbc);
        firstUserCreatedLabel.setVisible(true);

        Date secondUserCreated = secondUser.getCreatedAt();
        JLabel secondUserCreatedLabel = new JLabel(transFormat.format(secondUserCreated));
        secondUserCreatedLabel.setFont(font);
        GridBagConstraints secondUserCreatedLabelGbc = new GridBagConstraints();
        secondUserCreatedLabelGbc.gridy = 3;
        secondUserCreatedLabelGbc.gridx = 3;
        resultPanel.add(secondUserCreatedLabel, secondUserCreatedLabelGbc);
        secondUserCreatedLabel.setVisible(true);

        //birthday, region

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
            String a, b;
            a = "Hoshino_Sleep";
            b = "KR_BlueArchive";
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
        GridBagConstraints firstGbc = new GridBagConstraints();
        firstGbc.gridx = 0;
        firstGbc.gridy = 0;
        homePanel.add(firstUserHandleInput, firstGbc);
        firstUserHandleInput.setVisible(true);

        secondUserHandleInput = new JTextField("Second User Handle");
        secondUserHandleInput.setFont(font);
        secondUserHandleInput.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints secondGbc = new GridBagConstraints();
        secondGbc.gridx = 2;
        secondGbc.gridy = 0;
        homePanel.add(secondUserHandleInput, secondGbc);
        secondUserHandleInput.setVisible(true);
    }
}
