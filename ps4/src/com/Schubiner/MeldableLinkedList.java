package com.Schubiner;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly-linked list that supports O(1) concatenation. You are not required
 * to implement this class, but we strongly recommend doing so as a stepping
 * stone toward building the LazyBinomialHeap.
 */
public class MeldableLinkedList {
    private RootNode head, tail;

    public RootNode getHead() {
        return head;
    }

    public RootNode getTail() {
        return tail;
    }

    public void deleteNode(RootNode node) {
        if (node == head) {
            head = node.next;
            if (head != null)
                head.prev = null;
        } else if (node == tail) {
            tail = node.prev;
            tail.next = null;
        } else {
            RootNode prevNode = node.prev;
            prevNode.next = node.next;
            node.next.prev = prevNode;
        }

        node.next = null;
        node.prev = null;
    }

    /* We support O(1) concatenation :)
     * The MeldableLinkedList this is called on will now be merged with "two"
     * "two" will now be unusable.
     */
    public void concatWithList(MeldableLinkedList two) {
        this.tail.next = two.head;
        two.head.prev = this.tail;
        this.tail = two.tail;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void insertAfterNode(RootNode beforeNode, RootNode newNode) {
        if (beforeNode == tail) {
            newNode.prev = this.tail;
            this.tail.next = newNode;
            this.tail = newNode;
            return;
        }

        RootNode oldNext = beforeNode.next;
        beforeNode.next = newNode;
        newNode.next = oldNext;
        oldNext.prev = newNode;
        newNode.prev = beforeNode;
    }

//    public void printList() {
//        Iterator<RootNode> it = this.iterator();
//        while(it.hasNext()) {
//            TreeNode comp = it.next().treeNode;
//            System.out.println(comp.key);
//            System.out.println("children: ");
//            TreeNode children = comp.left;
//            while(children!= null){
//                System.out.println(children.key);
//                children= children.right;
//            }
//        }
//    }

    public void insertNodeAtEnd(RootNode newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.prev = this.tail;
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }

    public Iterator<RootNode> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<RootNode> {
        private RootNode curr;
        private RootNode nextNode;

        public LinkedListIterator() {
            nextNode = head;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public RootNode next() {
            if (!hasNext()) throw new NoSuchElementException();
            RootNode ret = nextNode;
            curr = nextNode;
            nextNode = nextNode.next;
            return ret;
        }

        @Override
        public void remove() {
            deleteNode(curr);
        }
    }
}

