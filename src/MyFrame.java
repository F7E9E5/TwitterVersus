import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends Component implements ActionListener {
    JFrame mainFrame = new JFrame("Twitter Versus");
    JPanel homePanel = new JPanel();
    JPanel resultPanel = new JPanel();
    JTextField firstUserHandleInput;
    JTextField secondUserHandleInput;
    JButton returnButton = new JButton("return");
    JButton backButton = new JButton("back");
    JLabel invalidText = new JLabel();
    Font font = new Font(null, Font.BOLD, 20);
    Font invalideFont = new Font(null, Font.ITALIC, 20);

    public MyFrame() {
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setSize(900, 600);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.add(homePanel);

        invalidText.setText("Invalid input, Please retry");
        returnButton.addActionListener(this::actionPerformed);
        backButton.addActionListener(this::actionPerformed);

        homePanel.setLayout(null);
        homePanel.setSize(900, 600);
        homePanel.add(invalidText);
        homePanel.add(returnButton);
        invalidText.setBounds(335, 450, 300, 30);
        returnButton.setBounds(400, 300, 100, 50);
        invalidText.setVisible(false);
        setInput();

        homePanel.setVisible(true);

        invalidText.setFont(invalideFont);
        invalidText.setForeground(Color.red);
    }

    public void displayResult(String firstUserHandle, String secondUserHandle) {
        homePanel.setVisible(false);

        resultPanel = new JPanel();
        mainFrame.add(resultPanel);

        resultPanel.setSize(900, 600);
        resultPanel.setLayout(null);

        resultPanel.add(backButton);
        backButton.setBounds(830, 0, 60, 70);
        backButton.setVisible(true);

        //user handle, id

        //user profile picture

        //user twitter domain

        //make day, birthday, region

        //follower graph

        //# of tweet graph

        resultPanel.setVisible(true);
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
            try {
                a = firstUserHandleInput.getText();
                b = secondUserHandleInput.getText();
            } catch (NullPointerException ex) {
                a = b = "";
            }

            try {
                if (checkHandle(a, b)) {
                    invalidText.setVisible(false);
                    displayResult(a, b);
                }
            } catch (TwitterException ex) {
                invalidText.setVisible(true);
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

        firstUserHandleInput = new JTextField("First User Handle", 20);
        secondUserHandleInput = new JTextField("Second User Handle", 20);

        firstUserHandleInput.setFont(font);
        secondUserHandleInput.setFont(font);

        firstUserHandleInput.setSize(20, 25);
        secondUserHandleInput.setSize(20, 25);
        homePanel.add(firstUserHandleInput);
        homePanel.add(secondUserHandleInput);
        firstUserHandleInput.setBounds(200, 175, 200, 25);
        secondUserHandleInput.setBounds(500, 175, 200, 25);
    }
}
