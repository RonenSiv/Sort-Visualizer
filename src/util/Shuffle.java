package util;

import Visualization.SortVisualizer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Shuffle extends SwingWorker<Void, int[]> {
    int[] items;
    SortVisualizer visualizer;

    public Shuffle(int[] items, SortVisualizer sortVisualizer) {
        this.items = Arrays.copyOf(items, items.length);
        this.visualizer = sortVisualizer;
    }

    public void shuffle() {
        for (int i = 0, k = items.length - 1; i < items.length / 2 && k > items.length / 2; i++, k--) {
            int j = (int) (Math.random() * items.length);
            int m = (int) (Math.random() * items.length);
            swap(items, i, j, k, m);
        }
    }

    protected void swap(int[] arr, int i, int j, int k, int m) {
        swap(arr, i, j);
        swap(arr, k, m);
        publish(Arrays.copyOf(arr, arr.length));
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        visualizer.setColorArr(i, Color.WHITE);
        visualizer.setColorArr(j, Color.WHITE);
    }

    @Override
    protected Void doInBackground() {
        shuffle();
        return null;
    }

    @Override
    protected void process(List<int[]> list) {
        int[] items = list.get(list.size() - 1);
        visualizer.update(items);
    }

    @Override
    protected void done() {
        visualizer.colorReset();
        visualizer.enebleButtons(true);
        visualizer.update(items);
    }

    protected void publish(int[] items) {
        process(List.of(items));
    }
}
