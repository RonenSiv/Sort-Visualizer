package SortAlgorithms;

import Visualization.SortVisualizer;
import java.util.Arrays;

public class QuickSort extends SortAlgo{

    public QuickSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    public void sort() {
        quickSort(items, 0, items.length - 1);
    }

    private void quickSort(int[] arr, int i, int i1) {
        if (i < i1) {
            int j = partition(arr, i, i1);
            quickSort(arr, i, j - 1);
            quickSort(arr, j + 1, i1);
            publish(Arrays.copyOf(arr, arr.length));
        }
    }

    private int partition(int[] arr, int i, int i1) {
        int pivot = arr[i];
        int j = i;

        for (int k = i + 1; k <= i1; k++) {
            if (arr[k] < pivot) {
                j++;
                highlightBar(pivot, CompareColor);
                swap(arr, j, k);
            }
        }
        highlightBar(pivot, CompareColor);
        swap(arr, i, j);
        return j;
    }
}
