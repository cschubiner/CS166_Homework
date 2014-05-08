package com.Schubiner;

import java.util.ArrayList;

/**
 * An implementation of a static BST backed by a weight-balanced tree.
 */
public class WeightBalancedTree implements BST {
    Node root;

    /**
     * Constructs a new tree from the specified array of weights. The array entry
     * at position 0 specifies the weight of 0, the entry at position 1 specifies
     * the weight of 1, etc.
     *
     * @param elements - weights on the elements.
     */
    public WeightBalancedTree(double[] elements) {
        ArrayList<SmallNode> smallNodeElements = new ArrayList<SmallNode>(elements.length);
        for (int i = 0; i < elements.length; i++)
            smallNodeElements.add(new SmallNode(i, elements[i]));

        this.root = makeWBT(smallNodeElements, 0, elements.length - 1);
    }

    private Node makeWBT(ArrayList<SmallNode> elements, int low, int high) {
        if (low > high)
            return null;

        int lowIndex = low, highIndex = high, rootIndex = low;
        double lowSum = elements.get(lowIndex).weight;
        double highSum = elements.get(highIndex).weight;
        double oldDiff = Math.abs(lowSum - highSum);

        boolean oldWasLow = true;
        while (lowIndex < highIndex) {
            double valLow = lowSum + elements.get(lowIndex + 1).weight;
            double valHigh = highSum + elements.get(highIndex - 1).weight;
            if (Math.abs(highSum - valLow) < Math.abs(valHigh - lowSum)) {
                lowIndex += 1;
                lowSum = valLow;
                oldWasLow = true;
            } else {
                highIndex -= 1;
                highSum = valHigh;
                oldWasLow = false;
            }

            double diff = Math.abs(lowSum - highSum);
            if (diff > oldDiff) {
                rootIndex = oldWasLow ? lowIndex - 1 : highIndex + 1;
                break;
            }
            oldDiff = diff;
        }

        Node root = new Node(elements.get(rootIndex).key, elements.get(rootIndex).weight);
        root.left = makeWBT(elements, low, rootIndex - 1);
        root.right = makeWBT(elements, rootIndex + 1, high);

        return root;
    }

    /**
     * Returns whether the specified key is in the BST.
     *
     * @param key The key to test.
     * @return Whether it's in the BST.
     */
    public boolean contains(int key) {
        Node root = this.root;
        while (root != null) {
            if (root.key == key)
                return true;
            if (key > root.key)
                root = root.right;
            else root = root.left;
        }

        return false;
    }

    private class SmallNode implements Comparable<SmallNode> {
        int key;
        double weight;


        public SmallNode(int key, double weight) {
            this.key = key;
            this.weight = weight;
        }

        private SmallNode() {
        }

        @Override
        public int compareTo(SmallNode o) {
            return this.key - o.key > 0 ? 1 : -1;
        }
    }

    private class Node extends SmallNode {
        Node left;
        Node right;

        public Node(int key, double weight) {
            this.key = key;
            this.weight = weight;
        }
    }
}
