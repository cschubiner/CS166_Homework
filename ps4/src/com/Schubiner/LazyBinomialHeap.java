package com.Schubiner;

import java.util.HashMap;
import java.util.Iterator;

/**
 * An implementation of a priority queue backed by a lazy binomial heap. Each
 * binomial tree in this heap should be represented using the left-child/right-
 * sibling representation, and the binomial heaps should be stored in a doubly-
 * linked list (though not necessarily a circularly-linked list).
 * <p/>
 * For simplicity, we will not make a distinction between keys and values in
 * this priority queue.
 */
public class LazyBinomialHeap {
    public MeldableLinkedList rootList;
    private RootNode minRootNode;

    /**
     * Constructs a new, empty LazyBinomialHeap.
     * Create the root doubly linked list
     */
    public LazyBinomialHeap() {
        rootList = new MeldableLinkedList();
        minRootNode = null;
    }

    /**
     * Melds together the two input priority queues into a single priority
     * queue. After this method is called on two priority queues, both of the
     * input queues should not be used again in the future and any operations
     * performed on them will have unspecified behavior.
     *
     * @param one The first queue to meld.
     * @param two The second queue to meld.
     * @return A queue consisting of all the keys in both input queues.
     */
    public static LazyBinomialHeap meld(LazyBinomialHeap one,
                                        LazyBinomialHeap two) {
        LazyBinomialHeap ret = new LazyBinomialHeap();
        one.rootList.concatWithList(two.rootList);
        ret.minRootNode = one.minRootNode.treeNode.key <= two.minRootNode.treeNode.key ? one.minRootNode : two.minRootNode;
        ret.rootList = one.rootList;
        return ret;
    }

    /**
     * Returns whether the lazy binomial heap is empty.
     * Simply checks if Linked List is empty
     *
     * @return Whether this lazy binomial heap is empty.
     */
    public boolean isEmpty() {
        return rootList.isEmpty();
    }

    /**
     * Adds the specified key to the priority queue. Duplicate values are
     * allowed.
     * Make a new node and insert into linked list
     *
     * @param key The key to add.
     */
    public void enqueue(int key) {
        TreeNode tn = new TreeNode(key);
        RootNode rn = new RootNode(tn);
        tn.order = rn.order = 0;
        if (minRootNode == null || key < minRootNode.treeNode.key)
            minRootNode = rn;

        rootList.insertNodeAtEnd(rn);
    }

    /**
     * Returns the minimum key in the priority queue. This method can assume
     * that the priority queue is not empty.
     * <p/>
     * We keep track of the minimum node in our root linked list. Return it
     *
     * @return The minimum key in the priority queue.
     */
    public int min() {
        /* To enable assertions during testing, run java with the -ea flag:
         *
		 *    java -ea NameOfMainClass
		 */
        assert !isEmpty() : "Priority queue is empty!";
        return minRootNode.treeNode.key;
    }

    public void removeMinNode() {
        rootList.deleteNode(minRootNode);
        TreeNode min = minRootNode.treeNode;
        TreeNode child = min.left;
        while (child != null) {
            TreeNode next = child.right;
            child.right = null;
            RootNode toBeInserted = new RootNode(child);
            toBeInserted.order = child.order;
            rootList.insertNodeAtEnd(toBeInserted);
            child = next;
        }
        minRootNode = null;
    }

    public void lookForNewRootNode() {
        Iterator<RootNode> it = rootList.iterator();
        int min_value = Integer.MAX_VALUE;
        RootNode min = null;
        while (it.hasNext()) {
            RootNode curr = it.next();
            TreeNode comp = curr.treeNode;
            if (comp.key < min_value) {
                min_value = comp.key;
                min = curr;
            }
        }
        minRootNode = min;
    }

    /**
     * Removes and returns the minimum element of the priority queue. This
     * method can assume that the priority queue is nonempty.
     * <p/>
     * Create a new root linkedlist
     * Iterate over the old root linkedlist, adding it with the new root
     * linked list as we go. Set new root linkedlist as this.root
     *
     * @return The formed minimum element of the priority queue.
     */
    public int extractMin() {
        assert !isEmpty() : "Priority queue is empty!";

        int min_value = min();
        removeMinNode();

        MeldableLinkedList compacted_list = new MeldableLinkedList();
        Iterator<RootNode> it = rootList.iterator();

        HashMap<Integer, RootNode> available_trees = new HashMap<Integer, RootNode>();
        while (it.hasNext()) {
            RootNode to_be_added = it.next();
            it.remove();
            int order = to_be_added.order;

            while (available_trees.containsKey(order)) {
                RootNode same_order_node = available_trees.get(order);
                compacted_list.deleteNode(same_order_node);
                available_trees.remove(order);
                to_be_added = combine(to_be_added, same_order_node);
                order++;
            }
            to_be_added.order = order;
            available_trees.put(order, to_be_added);
            compacted_list.insertNodeAtEnd(to_be_added);
        }

        rootList = compacted_list;
        lookForNewRootNode();

        return min_value;
    }

    public RootNode combine(RootNode a, RootNode b) {
        if (a.order != b.order) return null; //error right here

        TreeNode first = a.treeNode;
        TreeNode second = b.treeNode;
        TreeNode parent, child;
        RootNode to_be_returned;
        if (first.key < second.key) {
            to_be_returned = a;
            parent = first;
            child = second;
        } else {
            to_be_returned = b;
            parent = second;
            child = first;
        }

        child.right = parent.left;
        parent.left = child;
        parent.order = parent.order + 1;
        to_be_returned.order = to_be_returned.order + 1;
        return to_be_returned;
    }
}
