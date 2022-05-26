import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    JLabel invalidText;
    JButton returnButton;
    JTextField firstUserHandleInput, secondUserHandleInput;

    HomePanel() {
        setPreferredSize(new Dimension(900, 600));
        setLayout(new GridBagLayout());

        addEmptyText();
        addInvalidText();
        addReturnButton();
        setInput();
    }

    private void addInvalidText() {
        invalidText = new JLabel("Invalid input, Please retry");
        invalidText.setFont(new Font(null, Font.ITALIC, 20));
        invalidText.setForeground(Color.red);
        GridBagConstraints invalidTextGbc = new GridBagConstraints();
        invalidTextGbc.gridx = 1;
        invalidTextGbc.gridy = 3;
        add(invalidText, invalidTextGbc);
        invalidText.setVisible(false);
    }

    private void addEmptyText() {
        JLabel emptyText1 = new JLabel("");
        emptyText1.setPreferredSize(new Dimension(230, 130));
        GridBagConstraints emptyText1gbc = new GridBagConstraints();
        emptyText1gbc.gridy = 0;
        emptyText1gbc.gridx = 1;
        add(emptyText1, emptyText1gbc);

        JLabel emptyText2 = new JLabel("");
        emptyText2.setPreferredSize(new Dimension(230, 100));
        GridBagConstraints emptyText2gbc = new GridBagConstraints();
        emptyText2gbc.gridy = 3;
        emptyText2gbc.gridx = 1;
        add(emptyText2, emptyText2gbc);
    }

    private void addReturnButton() {
        returnButton = new JButton("return");
        returnButton.setPreferredSize(new Dimension(100, 60));
        GridBagConstraints returnButtonGbc = new GridBagConstraints();
        returnButtonGbc.gridx = 1;
        returnButtonGbc.gridy = 2;
        add(returnButton, returnButtonGbc);
        returnButton.setVisible(true);
    }

    public void setInput() {
        try {
            remove(firstUserHandleInput);
            remove(secondUserHandleInput);
        } catch (NullPointerException ignored) {}

        Font font = new Font(null, Font.BOLD, 20);

        firstUserHandleInput = new JTextField("First User Handle");
        firstUserHandleInput.setFont(font);
        firstUserHandleInput.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints firstGbc = new GridBagConstraints();
        firstGbc.gridx = 0;
        firstGbc.gridy = 0;
        add(firstUserHandleInput, firstGbc);
        firstUserHandleInput.setVisible(true);

        secondUserHandleInput = new JTextField("Second User Handle");
        secondUserHandleInput.setFont(font);
        secondUserHandleInput.setPreferredSize(new Dimension(200, 30));
        GridBagConstraints secondGbc = new GridBagConstraints();
        secondGbc.gridx = 2;
        secondGbc.gridy = 0;
        add(secondUserHandleInput, secondGbc);
        secondUserHandleInput.setVisible(true);
    }

    public void checkHandle(String a, String b) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.showUser(a);
        twitter.showUser(b);
    }
}
