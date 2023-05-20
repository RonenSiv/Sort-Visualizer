package SortAlgorithms;

import Visualization.SortVisualizer;

import java.awt.*;
import java.util.Arrays;

public class CountSort extends SortAlgo{
    public CountSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }
    @Override
    protected void sort() {
        int max = getMax(items);
        int[] count = new int[max + 1];
        for (int i = 0; i < items.length; i++) {
            count[items[i]]++;
            highlightBar(i, CompareColor);
            publish(Arrays.copyOf(items, items.length));
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        int[] output = new int[items.length];
        for (int i = items.length - 1; i >= 0; i--) {
            output[count[items[i]] - 1] = items[i];
            count[items[i]]--;
            highlightBar(i, CompareColor);
            publish(Arrays.copyOf(items, items.length));
        }
        for (int i = 0; i < items.length; i++) {
            items[i] = output[i];
            highlightBar(i, CompareColor);
            publish(Arrays.copyOf(items, items.length));
        }
        publish(Arrays.copyOf(items, items.length));
    }

    private int getMax(int[] items) {
        int max = items[0];
        for (int i = 1; i < items.length; i++) {
            if (items[i] > max) {
                max = items[i];
            }
        }
        return max;
    }
}
