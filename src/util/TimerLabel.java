package util;

import javax.swing.*;
import java.awt.*;

public class TimerLabel {
    public static long lastTime = 0;
    private JLabel label = null;
    private long startTime;

    public TimerLabel(JLabel label, long startTime) {
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JTextField.RIGHT);
        this.label = label;
        this.startTime = startTime;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - startTime;
        if(deltaTime > 0) {
            label.setText(String.format("%d", deltaTime) + " ms");
        } else {
            label.setText(lastTime + " ms");
        }
        lastTime = deltaTime;
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
    }

    public JLabel getLabel() {
        return label;
    }
}
