package SortAlgorithms;

import Visualization.SortVisualizer;

import java.awt.*;
import java.util.Arrays;

public class PigeonholeSort extends SortAlgo{
    public PigeonholeSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }
    @Override
    protected void sort() {
        int n = items.length;
        int min = items[0];
        int max = items[0];
        for (int i = 1; i < n; i++) {
            if (items[i] < min) {
                min = items[i];
            }
            if (items[i] > max) {
                max = items[i];
            }
            highlightBar(i, Color.GREEN);
            publish(Arrays.copyOf(items, items.length));
        }
        int[] holes = new int[max - min + 1];
        for (int i = 0; i < holes.length; i++) {
            holes[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            holes[items[i] - min] += 1;
        }
        int index = 0;
        for (int i = 0; i < holes.length; i++) {
            for (int j = 0; j < holes[i]; j++) {
                items[index++] = i + min;
                highlightBar(i, Color.WHITE);
                highlightBar(min, Color.WHITE);
                highlightBar(index - 1, Color.WHITE);
                publish(Arrays.copyOf(items, items.length));
                visualizer.colorReset();
            }
        }
    }
}
