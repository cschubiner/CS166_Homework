package com.Schubiner;

/**
 * Created by clayschubiner on 4/28/14.
 */
public class TreeNode {
    public int order;
    public final int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key) {
        this.key = key;
        order = 0;
    }
}
