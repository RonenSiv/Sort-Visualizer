package Visualization;

import util.Shuffle;
import util.SwitchesLabel;
import util.TimerLabel;

import javax.swing.*;
import java.awt.*;

import static Visualization.SortVisualizer.*;

public class ButtonCreator {
    private final SortVisualizer visualizer;
    private final JButton sortButton = new JButton("Sort");
    private final JButton shuffleButton = new JButton("Shuffle");
    private final String[] sortingAlgorithms = {"Bubble Sort", "Merge Sort", "Quick Sort", "Insertion Sort",
            "Selection Sort", "Count Sort", "Radix Sort", "Cocktail Sort", "Heap Sort", "Shell Sort", "Comb Sort",
            "Bucket Sort", "Bogo Sort", "Stooge Sort", "Gnome Sort", "Timsort**", "Odd-Even Sort",
            "Pancake Sort", "Pigeonhole Sort", "Tree Sort"
    };
    private final JComboBox<String> algorithmBox = new JComboBox<>(sortingAlgorithms);
    private final JTextField delayField = new JTextField("");
    private final JTextField arraySizeField = new JTextField("1280");
    private final JLabel arraySizeLabel = new JLabel("Size: ");
    private final JLabel delayLabel = new JLabel("Delay (ms)");
    private final JLabel sortedLabel = new JLabel("Array is sorted");
    private final TimerLabel timerLabel = new TimerLabel(new JLabel("Time"), System.currentTimeMillis());
    private final SwitchesLabel switchesLabel = new SwitchesLabel(new JLabel("Switches " + 0));
    private final JLabel infoLabel =
            new JLabel("<html>info: <br/>Array Size: " + (int) (ARRAY_SIZE - 1) + "<br/>" +
                    getTimerLabel().getLabel().getText() +
                    " | " + getSwitchesLabel().getLabel().getText() +
                    "</html>");

    public ButtonCreator(SortVisualizer sortVisualizer) {
        this.visualizer = sortVisualizer;
    }

    public void init() {
        createButtons();
        addButtonsToPanel();
        setButtonsListeners();
        visualizer.update(visualizer.getItems());
    }

    private void addButtonsToPanel() {
        visualizer.add(sortedLabel);
        visualizer.add(sortButton);
        visualizer.add(shuffleButton);
        visualizer.add(arraySizeLabel);
        visualizer.add(arraySizeField);
        visualizer.add(delayLabel);
        visualizer.add(delayField);
        visualizer.add(algorithmBox);
        visualizer.add(infoLabel);
    }

    private void createButtons() {
        this.sortedLabel.setForeground(Color.RED);
        this.sortedLabel.setHorizontalAlignment(JTextField.CENTER);
        this.sortButton.setBackground(Color.GREEN);
        this.sortButton.setForeground(Color.BLACK);
        this.sortButton.setFocusPainted(false);
        this.sortButton.setBorderPainted(false);
        this.shuffleButton.setBackground(Color.RED);
        this.shuffleButton.setForeground(Color.BLACK);
        this.shuffleButton.setFocusPainted(false);
        this.shuffleButton.setBorderPainted(false);
        this.arraySizeField.setText(String.valueOf((int) ARRAY_SIZE));
        this.arraySizeField.setHorizontalAlignment(JTextField.CENTER);
        this.arraySizeField.setBackground(Color.WHITE);
        this.arraySizeField.setPreferredSize(new Dimension(40, 20));
        this.arraySizeLabel.setForeground(Color.WHITE);
        this.delayField.setPreferredSize(new Dimension(40, 20));
        this.delayField.setHorizontalAlignment(JTextField.CENTER);
        this.delayField.setBackground(Color.WHITE);
        this.delayField.setText(String.valueOf((int) ARRAY_WIDTH));
        this.delayLabel.setForeground(Color.WHITE);
        this.algorithmBox.setBackground(Color.WHITE);
        this.algorithmBox.setPreferredSize(new Dimension(150, 20));
        this.algorithmBox.setFocusable(false);
        this.algorithmBox.setFocusTraversalKeysEnabled(false);
        this.infoLabel.setForeground(Color.WHITE);
        this.infoLabel.setHorizontalAlignment(JTextField.RIGHT);
        this.timerLabel.reset();
        this.switchesLabel.reset();
    }

    private void setButtonsListeners() {
        sortButton.addActionListener(e -> {
            if (visualizer.isSorted()) {
                JOptionPane.showMessageDialog(null, "Array is already sorted!");
            } else {
                if(checkCondition()) {
                    enableButtons(false);
                    visualizer.sort();
                } else {
                    doActionErrorMessage();
                }
            }
        });
        shuffleButton.addActionListener(e -> {
            if(checkCondition()) {
                enableButtons(false);
                new Shuffle(visualizer.getItems(), visualizer).execute();
            } else {
                doActionErrorMessage();
            }
        });
        arraySizeField.addActionListener(e -> {
            boolean isValid = checkField(arraySizeField, true);
            if (isValid) {
                int arraySize = Integer.parseInt(arraySizeField.getText());
                ARRAY_SIZE = arraySize;
                visualizer.arraySizeChanged(arraySize);
            } else {
                arraySizeField.setText(String.valueOf((int) ARRAY_SIZE));
            }
        });
        delayField.addActionListener(e -> {
            boolean b = checkField(delayField, true);
            if (b) {
                int delay = Integer.parseInt(delayField.getText());
                visualizer.setDelay(delay);
            } else {
                delayField.setText(visualizer.getDelay() + "");
            }
        });
        algorithmBox.addActionListener(e -> {
            String algorithm = algorithmBox.getSelectedItem().toString();
            visualizer.setAlgorithm(algorithm);
        });
    }

    private void doActionErrorMessage() {
        String Message = !checkField(arraySizeField, false) ? "Array size must be between " +
                0 + " and " + (int) WIN_WIDTH + "!" : "";
        String Message2 = !checkField(delayField, false) ? "Delay must be between " +
                0 + " and " + (int) WIN_WIDTH + "!" : "";
        JOptionPane.showMessageDialog(null, "<html>" + Message +"<br/>" + Message2 + "<html>");
    }

    private boolean checkField(JTextField field, boolean printMessage) {
        try {
            int value = Integer.parseInt(field.getText());
            if (value >= 0 && value <= WIN_WIDTH) {
                return true;
            } else if(value < 0) {
                if(printMessage) JOptionPane.showMessageDialog(null, "Value must be greater than 0!");
                return false;
            } else if(value > WIN_WIDTH) {
                if(printMessage) JOptionPane.showMessageDialog(null, "Value must be less than " + (int) WIN_WIDTH + "!");
                return false;
            } else {
                if(printMessage) JOptionPane.showMessageDialog(null, "Value must be a number!");
                return false;
            }
        } catch (NumberFormatException ex) {
            if(printMessage) JOptionPane.showMessageDialog(null, "Value must be a number!");
            return false;
        }
    }

    public void enableButtons(boolean enable) {
        sortButton.setEnabled(enable);
        shuffleButton.setEnabled(enable);
        delayField.setEnabled(enable);
        algorithmBox.setEnabled(enable);
        timerLabel.reset();
        switchesLabel.reset();
    }

    public void update(int n, int[] items) {
        if (n == ARRAY_SIZE) {
            getSortedLabel().setForeground(Color.GREEN);
            getSortedLabel().setText("Array is sorted!");
        } else {
            getSortedLabel().setForeground(Color.RED);
            getSortedLabel().setText(n + " / " + (int) (ARRAY_SIZE) + " numbers in place");
        }
        getTimerLabel().update();
        getSwitchesLabel().update(items);
        getInfoLabel().setText("<html>info: <br/>Array Size: " + (int) (ARRAY_SIZE) + "<br/>" +
                getTimerLabel().getLabel().getText() +
                " | " + getSwitchesLabel().getLabel().getText() +
                "</html>");
    }

    public JLabel getSortedLabel() {
        return sortedLabel;
    }

    public JButton getSortButton() {
        return sortButton;
    }

    public JButton getShuffleButton() {
        return shuffleButton;
    }

    public JTextField getDelayField() {
        return delayField;
    }

    public JComboBox<String> getAlgorithmBox() {
        return algorithmBox;
    }

    public String[] getSortingAlgorithms() {
        return sortingAlgorithms;
    }

    public TimerLabel getTimerLabel() {
        return timerLabel;
    }

    public SwitchesLabel getSwitchesLabel() {
        return switchesLabel;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }

    public boolean checkCondition() {
        return checkField(arraySizeField, false) && checkField(delayField, false);
    }
}
