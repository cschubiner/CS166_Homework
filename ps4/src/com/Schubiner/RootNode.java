package com.Schubiner;

/**
 * Created by clayschubiner on 4/28/14.
 */
public class RootNode {
    public TreeNode treeNode;
    public Object testValDontUse;
    public RootNode next;
    public RootNode prev;
    public int order;

    public RootNode(TreeNode tn) {
        this.treeNode = tn;
    }

    public RootNode(Object val) {
        this.testValDontUse = val;
    }
}

