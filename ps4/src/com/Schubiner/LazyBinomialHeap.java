package com.Schubiner;

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
    MeldableLinkedList rootList;

    public class TreeNode extends Node{
        public int order;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Object val) {
            super(val);
        }
    }

    /**
     * Constructs a new, empty LazyBinomialHeap.
     * Create the root doubly linked list
     */
    public LazyBinomialHeap() {
        rootList = new MeldableLinkedList();
    }

    /**
     * Returns whether the lazy binomial heap is empty.
     * Simply checks if linkedlist is empty
     * @return Whether this lazy binomial heap is empty.
     */
    public boolean isEmpty() {
        return rootList.isEmpty();
    }

    /**
     * Adds the specified key to the priority queue. Duplicate values are
     * allowed.
     * Make a new node and insert into linked list
     * @param key The key to add.
     */
    public void enqueue(int key) {
        // TODO: Fill this in!
    }

    /**
     * Returns the minimum key in the priority queue. This method can assume
     * that the priority queue is not empty.
     *
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

        // TODO: Implement this!
        return 0;
    }

    /**
     * Removes and returns the minimum element of the priority queue. This
     * method can assume that the priority queue is nonempty.
     *
     * Create a new root linkedlist
     * Iterate over the old root linkedlist, adding it with the new root
     * linked list as we go. Set new root linkedlist as this.root
     *
     * @return The formed minimum element of the priority queue.
     */
    public int extractMin() {
        assert !isEmpty() : "Priority queue is empty!";

        // TODO: Implement this!
        return 0;
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
        // TODO: Implement this!
        return null;
    }
}
