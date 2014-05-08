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

    public SplayTree() { }

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
            Node a, b, c;
            boolean isRightRotation = p.left == x;
            if (isRightRotation) {
                a = x.left;
                b = x.right;
                c = p.right;

                x.left = a;
                x.right = p;
                p.left = b;
                p.right = c;
            }
            else {
                a = x.right;
                b = x.left;
                c = p.left;

                x.right = a;
                x.left = p;
                p.right = b;
                p.left = c;
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
        else {
            boolean xIsLeftChild = p.left == x;
            boolean pIsLeftChild = gg.left == p;
            Node ggg = gg.parent;

            if (this.root != p && xIsLeftChild == pIsLeftChild) {
                //do a zig-zig
                Node a,b,c,d;
                boolean isRightRotation = p.left == x;
                if (isRightRotation) {
                    a = x.left;
                    b = x.right;
                    c = p.right;
                    d = gg.right;

                    x.left = a;
                    x.right = p;
                    p.left = b;
                    p.right = gg;
                    gg.left = c;
                    gg.right = d;
                } else {
                    a = x.right;
                    b = x.left;
                    c = p.left;
                    d = gg.left;

                    x.right = a;
                    x.left = p;
                    p.right = b;
                    p.left = gg;
                    gg.right = c;
                    gg.left = d;
                }

                if (a != null) a.parent = x;
                if (b != null) b.parent = p;
                if (c != null) c.parent = gg;
                if (d != null) d.parent = gg;
                if (p != null) p.parent = x;
                if (gg != null) gg.parent = p;
            }

            if (this.root != p && xIsLeftChild != pIsLeftChild){
                boolean isRightRotation = p.right == x;
                Node a, b, c, d;
                if (isRightRotation) {
                    a = p.left;
                    b = x.left;
                    c = x.right;
                    d = gg.right;

                    x.right = gg;
                    gg.right = d;
                    gg.left = c;
                    p.left = a;
                    p.right = b;
                    x.left = p;
                    x.right = gg;

                } else {
                    a = p.right;
                    b = x.right;
                    c = x.left;
                    d = gg.left;

                    x.left = gg;
                    gg.left = d;
                    gg.right = c;
                    p.right = a;
                    p.left = b;
                    x.right = p;
                    x.left = gg;
                }

                if (a != null) a.parent = p;
                if (b != null) b.parent = p;
                if (c != null) c.parent = gg;
                if (d != null) d.parent = gg;
                if (p != null) p.parent = x;
                if (gg != null) gg.parent = x;
            }

            if (ggg != null) {
                if (ggg.left == gg)
                    ggg.left = x;
                else
                    ggg.right = x;
            }
            else
                this.root = x;
            x.parent = ggg;
        }

        if (gg == null) {
            this.root = x;
            x.parent = null;
        }
    }

    public void insert(int key) {
//        System.out.println(key);
        Node n = new Node(key);
        bstInsert(n);
        while (n != this.root)
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
