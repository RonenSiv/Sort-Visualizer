package SortAlgorithms;

import Visualization.SortVisualizer;

public class CombSort extends SortAlgo{
    public CombSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }
    @Override
    protected void sort() {
        int n = items.length;
        int gap = n;
        boolean swapped = true;
        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / 1.3);
            }
            swapped = false;
            for (int i = 0; i < n - gap; i++) {
                if (items[i] > items[i + gap]) {
                    swap(items, i, i + gap);
                    swapped = true;
                }
            }
        }
    }
}
