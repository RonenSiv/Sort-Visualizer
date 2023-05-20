package SortAlgorithms;

import Visualization.SortVisualizer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public abstract class SortAlgo extends SwingWorker<Void, int[]> {
    protected SortVisualizer visualizer;
    protected Color CompareColor;
    protected int[] items;

    public SortAlgo(int[] unsortedItems, SortVisualizer sortVisualizer) {
        items = Arrays.copyOf(unsortedItems, unsortedItems.length);
        this.visualizer = sortVisualizer;
        CompareColor = visualizer.getCompareColor();
    }

    protected abstract void sort();

    protected void swap(int[] arr, int k, int j) {
        int temp = arr[k];
        arr[k] = arr[j];
        arr[j] = temp;
        highlightBar(arr[k], CompareColor);
        highlightBar(arr[j], CompareColor);
        publish(Arrays.copyOf(arr, arr.length));
    }

    protected void highlightBar(int i, Color color) {
        visualizer.setColorArr(i, color);
    }

    private void sweep() {
        for (int i = 0; i < items.length; i++) {
            visualizer.setColorArr(i, Color.WHITE);
            publish(Arrays.copyOf(items, items.length));
        }
    }

    public void reset() {
        items = Arrays.copyOf(visualizer.getItems(), visualizer.getItems().length);
    }

    @Override
    protected Void doInBackground() {
        sort();
        sweep();
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
        reset();
    }

    protected void publish(int[] items) {
        process(List.of(items));
    }
}
