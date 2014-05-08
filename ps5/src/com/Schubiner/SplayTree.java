package com.Schubiner;
/**
 * An implementation of a BST backed by a splay tree.
 */
public class SplayTree implements BST {
    private Node root;

	/**
	 * Constructs a new tree from the specified array of weights. Since splay
	 * trees don't care about access probabilities, you should only need
	 * to read the length of the weights array and not the weights themselves.
	 * This tree should store the values 0, 1, 2, ..., n - 1, where n is the length
	 * of the input array.
	 *
	 * @param weights - the weights on the elements.
	 */
	public SplayTree(double[] weights) {
        for (int i = 0; i < weights.length; i++) {
            insert(i);
        }
	}

    private void bstInsert(Node n) {
        Node root = this.root;
        if (root == null) {
            this.root = n;
            return;
        }
        int key = n.key;

        Node lastRoot = null;
        while (root != null) {
            lastRoot = root;
            if (key > root.key)
                root = root.right;
            else root = root.left;
        }

        if (key > lastRoot.key)
            lastRoot.right = n;
        else
            lastRoot.left = n;

        n.parent = lastRoot;
    }

    private void splay(Node x) {
        if (x == this.root)
            return;

        Node p = x.parent;
        Node gg = p.parent;

        if (this.root == p) {
            //do a zig
            Node a = x.left;
            Node b = x.right;
            boolean isRightRotation = p.left == x;
            Node c = isRightRotation ? p.right : p.left;
            if (isRightRotation) {
                x.left = a;
                x.right = p;
                p.left = b;
                p.right = c;
            }
            else {
                x.right = a;
                x.left = p;
                p.left = c;
                p.right = b;
            }

            if (b != null) b.parent = p;
            p.parent = x;
            if (gg != null) {
                if (gg.left == p)
                    gg.left = x;
                else
                    gg.right = x;
                x.parent = gg;
            }
        }

        if (gg == null) {
            this.root = x;
            x.parent = null;
        }
    }

    public void insert(int key) {
        Node n = new Node(key);
        bstInsert(n);
        splay(n);
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

    private class Node {// implements Comparable<SmallNode>{
        int key;
        Node left;
        Node right;
        Node parent;

        public Node(int key) {
            this.key = key;
        }
    }
}
