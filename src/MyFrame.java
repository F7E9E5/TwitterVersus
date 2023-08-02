import twitter4j.TwitterException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyFrame extends Component implements ActionListener {
    JFrame mainFrame = new JFrame("Twitter Versus");
    ResultPanel resultPanel;
    HomePanel homePanel = new HomePanel();

    public MyFrame() {
        mainFrame.setVisible(true);
        mainFrame.setPreferredSize(new Dimension(900, 600));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(homePanel);
        homePanel.returnButton.addActionListener(this);
        homePanel.setVisible(true);
        mainFrame.pack();
    }

    public void displayResult(String firstUserHandle, String secondUserHandle) throws IOException, TwitterException {
        resultPanel = new ResultPanel(firstUserHandle, secondUserHandle);
        resultPanel.backButton.addActionListener(this);
        mainFrame.add(resultPanel);
        resultPanel.setVisible(true);
        mainFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homePanel.returnButton) {
            String a, b;
            try {
                a = homePanel.firstUserHandleInput.getText();
                b = homePanel.secondUserHandleInput.getText();
            } catch (NullPointerException ex) {
                a = b = "";
            }

            try {
                homePanel.checkHandle(a, b);
                homePanel.setVisible(false);
                homePanel.invalidText.setVisible(false);
                displayResult(a, b);
            } catch (TwitterException ex) {
                homePanel.invalidText.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == resultPanel.backButton) {
            resultPanel.setVisible(false);
            homePanel.setInput();
            homePanel.setVisible(true);
        }
    }
}
