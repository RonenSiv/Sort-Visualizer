package SortAlgorithms;

import Visualization.SortVisualizer;

public class OddEvenSort extends SortAlgo {
    public OddEvenSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i <= items.length - 2; i = i + 2) {
                if (items[i] > items[i + 1]) {
                    swap(items, i, i + 1);
                    isSorted = false;
                }
            }
            for (int i = 0; i <= items.length - 2; i = i + 2) {
                if (items[i] > items[i + 1]) {
                    swap(items, i, i + 1);
                    isSorted = false;
                }
            }
        }
    }
}
