package SortAlgorithms;

import Visualization.SortVisualizer;

import java.awt.*;
import java.util.Arrays;

public class RadixSort extends SortAlgo{
    public RadixSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }
    @Override
    protected void sort() {
        int max = getMax(items);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(items, exp);
        }
    }

    private void countSort(int[] items, int exp) {
        int[] count = new int[10];
        for (int i = 0; i < items.length; i++) {
            count[(items[i] / exp) % 10]++;
            highlightBar(i, Color.WHITE);
            publish(Arrays.copyOf(items, items.length));
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
            highlightBar(i, Color.WHITE);
            publish(Arrays.copyOf(items, items.length));
        }
        int[] output = new int[items.length];
        for (int i = items.length - 1; i >= 0; i--) {
            output[count[(items[i] / exp) % 10] - 1] = items[i];
            count[(items[i] / exp) % 10]--;
        }
        for (int i = 0; i < items.length; i++) {
            items[i] = output[i];
            highlightBar(i, Color.WHITE);
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
