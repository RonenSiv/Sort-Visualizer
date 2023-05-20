package util;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SwitchesLabel {
    private static int lastNum = 0;
    private static int[] lastItems = null;
    private JLabel label = null;
    private int num;

    public SwitchesLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JTextField.RIGHT);
        this.label = label;
    }

    public void update(int[] items) {
        num = checkForDifference(items);
        if(num > 0) {
            label.setText(num + " switches");
        } else {
            label.setText("Switches: " + lastNum);
        }
        lastNum = num;
    }

    public void reset() {
        this.num = 0;
        this.lastItems = null;
    }

    public JLabel getLabel() {
        return label;
    }

    public int checkForDifference(int[] arr) {
        if(lastItems == null) {
            lastItems = Arrays.copyOf(arr, arr.length);
            return 0;
        }
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != lastItems[i]) {
                num++;
            }
        }
        lastItems = Arrays.copyOf(arr, arr.length);
        return num;
    }
}
