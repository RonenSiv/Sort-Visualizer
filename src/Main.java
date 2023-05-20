import Visualization.SortingFrame;

import javax.sound.sampled.LineUnavailableException;

public class Main {
    public static void main(String[] args) throws LineUnavailableException {
        SortingFrame sortingFrame = SortingFrame.getInstance();
        sortingFrame.init();
    }
}
