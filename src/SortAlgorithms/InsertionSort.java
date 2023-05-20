package SortAlgorithms;

import Visualization.SortVisualizer;

import java.awt.*;
import java.util.Arrays;

public class InsertionSort extends SortAlgo {
    public InsertionSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        for (int i = 1; i < items.length; ++i) {
            int key = items[i];
            int j = i - 1;
            while (j >= 0 && items[j] > key) {
                items[j + 1] = items[j];
                highlightBar(j + 1, CompareColor);
                highlightBar(j, CompareColor);
                highlightBar(i, CompareColor);
                publish(Arrays.copyOf(items, items.length));
                j = j - 1;
            }
            items[j + 1] = key;
            highlightBar(j + 1, Color.WHITE);
            publish(Arrays.copyOf(items, items.length));
        }
        publish(Arrays.copyOf(items, items.length));
    }
}
