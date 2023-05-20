package SortAlgorithms;

import Visualization.SortVisualizer;

public class CocktailSort extends SortAlgo{
    public CocktailSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }
    @Override
    protected void sort() {
        int left = 0;
        int right = items.length - 1;
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (items[i] > items[i + 1]) {
                    swap(items, i, i + 1);
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (items[i] < items[i - 1]) {
                    swap(items, i, i - 1);
                }
            }
            left++;
        }
    }
}
