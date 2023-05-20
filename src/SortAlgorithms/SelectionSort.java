package SortAlgorithms;

import Visualization.SortVisualizer;

import java.awt.*;
import java.util.Arrays;

public class SelectionSort extends SortAlgo{
    public SelectionSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        for (int i = 0; i < items.length; i++) {
            int min = i;
            for (int j = i + 1; j < items.length; j++) {
                highlightBar(i, CompareColor);
                highlightBar(j, CompareColor);
                if (items[j] < items[min]) {
                    min = j;
                }
                highlightBar(min, Color.red);
                publish(Arrays.copyOf(items, items. length));
            }
            swap(items, i, min);
        }
    }
}
