package SortAlgorithms;

import Visualization.SortVisualizer;

public class Timsort extends SortAlgo {
    final static int MIN_MERGE = 32;

    public Timsort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        int minRun = minRunLength(MIN_MERGE);
        for (int i = 0; i < items.length; i += minRun) {
            insertionSort(items, i, Math.min((i + MIN_MERGE - 1), (items.length - 1)));
        }
        for (int size = minRun; size < items.length; size = 2 * size) {
            for (int left = 0; left < items.length;
                 left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                        (items.length - 1));

                if (mid < right) {
                    merge(items, left, mid, right);
                }
            }
        }
    }

    private void merge(int[] items, int left, int mid, int right) {
        MergeSort mergeSort = new MergeSort(items, visualizer);
        mergeSort.merge(items, left, mid, right);
    }

    private void insertionSort(int[] items, int i, int min) {
        new InsertionSort(items, visualizer).sort();
    }

    public static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }
}
