package Visualization;

import SortAlgorithms.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

public class SortVisualizer extends JPanel {
    public static double WIN_WIDTH = 1280;
    public static double WIN_HEIGHT = 720;
    public static double ARRAY_SIZE = 1280;
    public static double ARRAY_WIDTH = WIN_WIDTH / ARRAY_SIZE;
    public static double ARRAY_HEIGHT = (WIN_HEIGHT - 100) / ARRAY_SIZE;
    public static double Y_GAP = largestDivisor((int) ARRAY_SIZE);
    public static final Color COMPARE_COLOR = Color.WHITE;

    private int delay;
    private int[] items = IntStream.range(1, (int) ARRAY_SIZE+1).toArray();
    private Color[] colorArr = IntStream.range(0, (int) ARRAY_SIZE + 1).mapToObj(i -> Color.WHITE).toArray(Color[]::new);
    private Color[] itemColorArr;
    private SortAlgo sortAlgo;
    private final ButtonCreator buttonCreator;

    public SortVisualizer() throws LineUnavailableException {
        this.setPreferredSize(new Dimension((int) WIN_WIDTH, (int) WIN_HEIGHT));
        this.setBackground(new Color(0, 0, 0));
        this.revalidate();
        this.buttonCreator = new ButtonCreator(this);
        this.buttonCreator.init();
        setRainbowColors();
        this.itemColorArr = Arrays.copyOf(colorArr, colorArr.length);
        this.delay = Integer.parseInt(buttonCreator.getDelayField().getText());
        this.sortAlgo = new BubbleSort(items, this);
    }

    public int getDelay() {
        return delay;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
        if (sortAlgo != null) {
            sortAlgo.reset();
        }
        repaint();
    }

    public void setDelay(int n) {
        this.delay = n;
    }

    public void setColorArr(int index, Color color) {
        this.itemColorArr[index] = color;
    }

    public void colorReset() {
        itemColorArr = Arrays.copyOf(colorArr, colorArr.length);
    }

    public void sort() {
        sortAlgo.execute();
        setAlgorithm(Objects.requireNonNull(buttonCreator.getAlgorithmBox().getSelectedItem()).toString());

    }

    public boolean isSorted() {
        for (int i = 0; i < items.length - 1; i++) {
            if (items[i] > items[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void enebleButtons(boolean b) {
        this.buttonCreator.enableButtons(b);
    }

    public void setAlgorithm(String algorithm) {
        switch (algorithm) {
            case "Merge Sort" -> sortAlgo = new MergeSort(items, SortVisualizer.this);
            case "Quick Sort" -> sortAlgo = new QuickSort(items, SortVisualizer.this);
            case "Insertion Sort" -> sortAlgo = new InsertionSort(items, SortVisualizer.this);
            case "Selection Sort" -> sortAlgo = new SelectionSort(items, SortVisualizer.this);
            case "Radix Sort" -> sortAlgo = new RadixSort(items, SortVisualizer.this);
            case "Count Sort" -> sortAlgo = new CountSort(items, SortVisualizer.this);
            case "Cocktail Sort" -> sortAlgo = new CocktailSort(items, SortVisualizer.this);
            case "Heap Sort" -> sortAlgo = new HeapSort(items, SortVisualizer.this);
            case "Shell Sort" -> sortAlgo = new ShellSort(items, SortVisualizer.this);
            case "Comb Sort" -> sortAlgo = new CombSort(items, SortVisualizer.this);
            case "Bucket Sort" -> sortAlgo = new BucketSort(items, SortVisualizer.this);
            case "Bogo Sort" -> sortAlgo = new BogoSort(items, SortVisualizer.this);
            case "Stooge Sort" -> sortAlgo = new StoogeSort(items, SortVisualizer.this);
            case "Gnome Sort" -> sortAlgo = new GnomeSort(items, SortVisualizer.this);
            case "Tim-sort" -> sortAlgo = new Timsort(items, SortVisualizer.this);
            case "Odd-Even Sort" -> sortAlgo = new OddEvenSort(items, SortVisualizer.this);
            case "Pancake Sort" -> sortAlgo = new PancakeSort(items, SortVisualizer.this);
            case "Pigeonhole Sort" -> sortAlgo = new PigeonholeSort(items, SortVisualizer.this);
            case "Tree Sort" -> sortAlgo = new TreeSort(items, SortVisualizer.this);
            default -> sortAlgo = new BubbleSort(items, SortVisualizer.this);
        }
    }

    public Color getCompareColor() {
        return COMPARE_COLOR;
    }

    private void setRainbowColors() {
        colorArr = IntStream.range(0, (int) ARRAY_SIZE + 1).mapToObj(i -> {
            float hue = (float) i / (float) ARRAY_SIZE + 1;
            return Color.getHSBColor(hue, 1.0f, 1.0f);
        }).toArray(Color[]::new);
    }

    private void setRedShift() {
        colorArr = IntStream.range(0, (int) ARRAY_SIZE).mapToObj(i -> {
            float hue = (float) i / (float) ARRAY_SIZE;
            return Color.getHSBColor(1f, 1f, hue);
        }).toArray(Color[]::new);
    }

    public int numbersInPlace() {
        int n = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] == i + 1) {
                n++;
            }
        }
        return n;
    }

    public void update(int[] items) {
        setItems(items);
        int n = numbersInPlace();
        buttonCreator.update(n, items);
        repaint();
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        colorReset();
    }

    public void componentResized() {
        WIN_WIDTH = getWidth();
        WIN_HEIGHT = getHeight();
        ARRAY_WIDTH = WIN_WIDTH / ARRAY_SIZE;
        ARRAY_HEIGHT = (WIN_HEIGHT - 100) / ARRAY_SIZE;
        Y_GAP = largestDivisor((int) ARRAY_SIZE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (ARRAY_WIDTH < 15) {
            drawGrid(g2d);
        } else {
            drawNumbers(g2d);
        }
        drawRectangles(g2d);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) WIN_WIDTH, (int) (WIN_HEIGHT));
    }

    private void drawRectangles(Graphics2D g2d) {
        for (int i = 0; i < this.items.length; i++) {
            g2d.setColor(itemColorArr[items[i]]);
            g2d.drawRect((int) (i * ARRAY_WIDTH), (int) Math.ceil(WIN_HEIGHT - this.items[i] * ARRAY_HEIGHT),
                    (int) ARRAY_WIDTH, (int) Math.ceil(this.items[i] * ARRAY_HEIGHT));
        }
    }

    private void drawGrid(Graphics2D g) {
        g.setColor(new Color(103, 103, 103));
        for (double i = 0; i < ARRAY_SIZE + 1; i += Y_GAP) {
            int height = (int) Math.ceil(ARRAY_SIZE * ARRAY_HEIGHT);
            g.drawLine((int) (i * ARRAY_WIDTH), (int) (WIN_HEIGHT - height), (int) (i * ARRAY_WIDTH), (int) WIN_HEIGHT);
        }
        for (double i = 0; i < ARRAY_SIZE + 1; i += Y_GAP) {
            g.drawLine(0, (int) (WIN_HEIGHT - i * ARRAY_HEIGHT),
                    (int) WIN_WIDTH, (int) (WIN_HEIGHT - i * ARRAY_HEIGHT));
            g.drawString(String.valueOf((int) i), 0, (int) (WIN_HEIGHT - i * ARRAY_HEIGHT));
        }
    }

    public void drawNumbers(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < this.items.length; i++) {
            g2d.drawString(String.valueOf(items[i]), (int) (i * ARRAY_WIDTH) - 1,
                    (int) (WIN_HEIGHT - this.items[i] * ARRAY_HEIGHT) - 1);
        }
    }

    private void resetItems() {
        items = IntStream.range(1, (int) ARRAY_SIZE + 1).toArray();
    }

    public void arraySizeChanged(int size) {
        changeArrayRepresentation(size);
        reset();
    }

    public void changeArrayRepresentation(int size) {
        ARRAY_SIZE = size;
        ARRAY_WIDTH = WIN_WIDTH / ARRAY_SIZE;
        ARRAY_HEIGHT = (WIN_HEIGHT - 100) / ARRAY_SIZE;
        Y_GAP = largestDivisor((int) ARRAY_SIZE);
    }

    public static int largestDivisor(int n) {
        for (int i = (int) Math.sqrt(n); i >= 2; i--) {
            if (n % i == 0) {
                return n / i;
            }
        }
        return (int) Math.ceil(n/Math.sqrt(n));
    }

    public void reset() {
        resetItems();
        setItems(items);
        setRainbowColors();
        colorReset();
        repaint();
    }
}