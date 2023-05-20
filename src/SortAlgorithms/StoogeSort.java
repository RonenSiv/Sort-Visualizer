package SortAlgorithms;

import Visualization.SortVisualizer;

public class StoogeSort extends SortAlgo{
    public StoogeSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }
    @Override
    protected void sort() {
        stoogesort(items, 0, items.length - 1);
    }

    private void stoogesort(int arr[], int l, int h)
    {
        if (l >= h)
            return;

        if (arr[l] > arr[h]) {
            swap(arr, l, h);
        }

        if (h - l + 1 > 2) {
            int t = (h - l + 1) / 3;
            stoogesort(arr, l, h - t);
            stoogesort(arr, l + t, h);
            stoogesort(arr, l, h - t);
        }
    }
}
