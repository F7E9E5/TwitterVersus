
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.max;

public class myGraph extends JLabel {
    private int firstValue, secondValue;
    myGraph(int first, int second) {
        firstValue = first;
        secondValue = second;
        setPreferredSize(new Dimension(500, 50));
    }

    public void paint(Graphics g) {
        g.setColor(Color.CYAN);
        drawFollowerGraph(g, firstValue, secondValue);
    }

    public void drawFollowerGraph(Graphics g, int a, int b) {
        int cnt = 0;
        while (Math.pow(10, cnt) < Math.max(a, b)) {
            cnt++;
        }
        double aa = max(0.006, (double)a/Math.pow(10, cnt));
        double bb = max(0.006, (double)b/Math.pow(10, cnt));
        g.fillRect((int)(200*(1 - aa)), 15, (int)(200*aa), 20);
        g.fillRect(300, 15, (int)(200*bb), 20);
    }
}
