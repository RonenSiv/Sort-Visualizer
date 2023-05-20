package SortAlgorithms;

import Visualization.SortVisualizer;

public class BogoSort extends SortAlgo{
    public BogoSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }
    @Override
    protected void sort() {
        while (!isSorted()) {
            shuffle();
        }
    }

    private boolean isSorted() {
        for (int i = 0; i < items.length - 1; i++) {
            if (items[i] > items[i + 1]) {
                return false;
            }
        }
        return true;
    }

    protected void shuffle() {
        for (int i = 0; i < items.length; i++) {
            int j = (int) (Math.random() * items.length);
            swap(items, i, j);
        }
    }
}
