package SortAlgorithms;

import Visualization.SortVisualizer;

public class BubbleSort extends SortAlgo {
    public BubbleSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    protected void sort(){
        int n = items.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (items[j - 1] > items[j]) {
                    swap(items, j - 1, j);
                }
            }
        }
    }
}
