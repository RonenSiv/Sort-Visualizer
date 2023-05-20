package SortAlgorithms;

import Visualization.SortVisualizer;

public class ShellSort extends SortAlgo{
    public ShellSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        int n = items.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int j = i;
                while (j >= gap && items[j - gap] > items[j]) {
                    swap(items, j, j - gap);
                    j -= gap;
                }
            }
            gap /= 2;
        }
    }
}
