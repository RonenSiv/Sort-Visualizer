package SortAlgorithms;

import Visualization.SortVisualizer;

public class PancakeSort extends SortAlgo {
    public PancakeSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        for (int curr_size = items.length; curr_size > 1; --curr_size) {
            int mi = findMax(items, curr_size);
            if (mi != curr_size - 1) {
                flip(items, mi);
                flip(items, curr_size - 1);
            }
        }
    }

    void flip(int arr[], int i) {
        int start = 0;
        while (start < i) {
            swap(arr, start, i);
            start++;
            i--;
        }
    }

    int findMax(int arr[], int n) {
        int mi, i;
        for (mi = 0, i = 0; i < n; ++i) {
            if (arr[i] > arr[mi]) {
                mi = i;
            }
        }
        return mi;
    }
}
