package SortAlgorithms;

import Visualization.SortVisualizer;

import java.awt.*;
import java.util.Arrays;

public class MergeSort extends SortAlgo {
    public MergeSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        mergeSort(items, 0, items.length - 1);
        publish(Arrays.copyOf(items, items.length));
    }

    public void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public void merge(int arr[], int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int L[] = new int[n1];
        int M[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[p + i];
        }
        for (int j = 0; j < n2; j++) {
            M[j] = arr[q + 1 + j];
        }
        int i, j, k;
        i = 0;
        j = 0;
        k = p;
        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = M[j];
                j++;
            }
            k++;
            visualize(k - 1, p, q, r, n1, n2);
            publish(Arrays.copyOf(arr, arr.length));

        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            visualize(k - 1, p, q, r, n1, n2);
            publish(Arrays.copyOf(arr, arr.length));
        }

        while (j < n2) {
            arr[k] = M[j];
            j++;
            k++;
            visualize(k - 1, p, q, r, n1, n2);
            publish(Arrays.copyOf(arr, arr.length));
        }
    }
//
    public void visualize(int k, int p, int q, int r, int n1, int n2) {
        if (p >= 0 && p < items.length) {
            highlightBar(items[p], CompareColor);
        }
        if (q >= 0 && q < items.length) {
            highlightBar(items[q], CompareColor);
        }
        if (r >= 0 && r < items.length) {
            highlightBar(items[r], CompareColor);
        }
        if (k >= 0 && k < items.length) {
            highlightBar(items[k], CompareColor);
        }
    }
}
