package com.Schubiner;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly-linked list that supports O(1) concatenation. You are not required
 * to implement this class, but we strongly recommend doing so as a stepping
 * stone toward building the LazyBinomialHeap.
 */
public class MeldableLinkedList {
    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    private Node head, tail;
    private int size;

    public void deleteNode(Node node) {
        Node prevNode = node.prev;
        prevNode.next = node.next;
        node.next.prev = prevNode;

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
    }

    public boolean isEmpty() {
        return this.head != null;
    }

    public void insertAfterNode(Node beforeNode, Node newNode) {

    }

    public void insertNodeAtEnd(Node newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
    }

    public Iterator<Node> iterator()
    {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Node> {
        private Node nextNode;

        public LinkedListIterator()
        {
            nextNode = head;
        }

        @Override
        public boolean hasNext() {
            return nextNode.next != null;
        }

        @Override
        public Node next() {
            if(!hasNext()) throw new NoSuchElementException();
            nextNode = nextNode.next;
            return nextNode;
        }

        @Override
        public void remove() {
            deleteNode(nextNode);
        }
    }
}

