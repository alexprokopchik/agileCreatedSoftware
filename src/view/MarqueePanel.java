package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the panel that performs a scrolling Marquee that shows 
 * the user that is logged in, and the coference that he/she is currently logged into.
 * 
 * @author Alex
 */
@SuppressWarnings("serial")
public class MarqueePanel extends JPanel implements ActionListener {

    private static final int RATE = 12;
    private final Timer timer = new Timer(1000 / RATE, this);
    private final JLabel label = new JLabel();
    private final String s;
    private final int n;
    private int index;
    private final Color background;

    /**
     * Side-scrolls n characters of s from the right side to the left.
     * @param s String of the message
     * @param n int that determines where the scroll should start on the panel.
     */
    public MarqueePanel(String s, int n) {
    	background = new Color(0,51,102);
    	setBackground(background);
        if (s == null || n < 1) {
            throw new IllegalArgumentException("Null string or n < 1");
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        this.s = sb + s + sb;
        this.n = n;
        label.setFont(new Font("Serif", Font.BOLD, 14));
        label.setText(sb.toString());
        label.setForeground(Color.YELLOW);
        this.add(label);
    }

    /**
     * Starts the Marquee.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stops the Marquee.
     */
    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        index++;
        if (index > s.length() - n) {
            index = 0;
        }
        label.setText(s.substring(index, index + n));
    }
}
