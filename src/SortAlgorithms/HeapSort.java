package SortAlgorithms;

import Visualization.SortVisualizer;

public class HeapSort extends SortAlgo{
    public HeapSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }
    @Override
    protected void sort() {
        int n = items.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(items, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(items, 0, i);
            heapify(items, i, 0);
        }
    }

    private void heapify(int[] items, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && items[l] > items[largest]) {
            largest = l;
        }
        if (r < n && items[r] > items[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(items, i, largest);
            heapify(items, n, largest);
        }
    }
}
