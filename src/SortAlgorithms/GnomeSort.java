package SortAlgorithms;

import Visualization.SortVisualizer;

public class GnomeSort extends SortAlgo {
    public GnomeSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        int n = items.length;
        int i = 0;
        while (i < n) {
            if (i == 0 || items[i - 1] <= items[i]) {
                i++;
            } else {
                swap(items, i, i - 1);
                i--;
            }
        }
    }
}
