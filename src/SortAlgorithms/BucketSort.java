package SortAlgorithms;

import Visualization.SortVisualizer;

import java.util.Arrays;

public class BucketSort extends SortAlgo {
    public BucketSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        int n = items.length;
        int max = items[0];
        for (int i = 1; i < n; i++) {
            if (items[i] > max) {
                max = items[i];
            }
        }
        int[] buckets = new int[max + 1];
        for (int i = 0; i < n; i++) {
            buckets[items[i]]++;
        }
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i] > 0) {
                items[k++] = i;
                buckets[i]--;
                highlightBar(k - 1, CompareColor);
                highlightBar(i, CompareColor);
                publish(Arrays.copyOf(items, items.length));
            }
        }
    }
}
