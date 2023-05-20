package SortAlgorithms;

import Visualization.SortVisualizer;
import java.util.Arrays;

/**
 * The type Tree sort.
 */
public class TreeSort extends SortAlgo {
    private Node root;
    private int index = 0;
    private int[] itemsTree ;

    /**
     * Instantiates a new Tree sort.
     *
     * @param unsortedItems  the unsorted items
     * @param sortVisualizer the sort visualizer
     */
    public TreeSort(int[] unsortedItems, SortVisualizer sortVisualizer) {
        super(unsortedItems, sortVisualizer);
    }

    @Override
    protected void sort() {
        root = null;
        itemsTree = new int[items.length];
        insertArr(items);
        convertArrayToTreeArray();
        inorderRec(root);
        index = 0;
    }

    private void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    private void inorderRec(Node root) {
        if (root != null) {
            visualizer.colorReset();
            inorderRec(root.left);
            items[index] = root.key;
            highlightBar(index, CompareColor);
            publish(Arrays.copyOf(items, items.length));
            index++;
            inorderRec(root.right);
        }
    }

    private void insertArr(int[] arr) {
        for (int j : arr) {
            insert(j);
        }
    }

    private void convertArrayToTreeArray() {
        treeToArray(root);
        index = 0;
        for (int i = 0; i < itemsTree.length; i++) {
            items[i] = itemsTree[i];
            highlightBar(i, CompareColor);
            publish(Arrays.copyOf(items, items.length));
        }
    }

    private void treeToArray(Node root) {
        if (root != null) {
            itemsTree[index] = root.key;
            index++;
            treeToArray(root.left);
            treeToArray(root.right);
        }
    }

    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }
}
