package Visualization;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SortingFrame extends JFrame {
    private static SortingFrame instance;

    private SortingFrame() {
    }

    public static SortingFrame getInstance() {
        if(instance == null) {
            instance = new SortingFrame();
        }
        return instance;
    }

    public void init() throws LineUnavailableException {
        if(instance != null) {
            SortVisualizer sortVisualizer = new SortVisualizer();
            this.setTitle("Sorting Visualizer");
            this.getContentPane().add(sortVisualizer);
            this.pack();
            this.setVisible(true);
//            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    sortVisualizer.componentResized();
                }
            });
        }
    }
}
