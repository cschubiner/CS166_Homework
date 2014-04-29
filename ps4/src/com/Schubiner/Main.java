package com.Schubiner;

import org.junit.Test;


import java.util.Iterator;

import static org.junit.Assert.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("test");
    }

    @Test
    public void testEasy() {
        LazyBinomialHeap lbh = new LazyBinomialHeap();
        lbh.enqueue(2);
        assertEquals(2, lbh.min()); //we want 2
    }

    @Test
    public void testEmpty() {
        LazyBinomialHeap lbh = new LazyBinomialHeap();
        assertTrue(lbh.isEmpty());
        lbh.enqueue(2);
        assertFalse(lbh.isEmpty());
    }

























    @Test
    public void testLLEmpty() {
        MeldableLinkedList lbh = new MeldableLinkedList();
        assertTrue(lbh.isEmpty());
        lbh.insertNodeAtEnd(new RootNode(2));
        assertFalse(lbh.isEmpty());
    }

    @Test
    public void testLLsmall() {
        MeldableLinkedList ll = new MeldableLinkedList();
        RootNode node = new RootNode(2);
        ll.insertNodeAtEnd(node);
        ll.deleteNode(node);
        assertTrue(ll.isEmpty());
    }

    @Test
    public void testLLsmall2() {
        MeldableLinkedList ll = new MeldableLinkedList();
        RootNode node = new RootNode(2);
        ll.insertNodeAtEnd(node);
        ll.insertNodeAtEnd(new RootNode(3));
        assertEquals(2, ll.getHead().val);
        assertEquals(3, ll.getTail().val);
        ll.deleteNode(node);
        assertFalse(ll.isEmpty());
        assertEquals(3, ll.getHead().val);
    }

    @Test
    public void testLL3() {
        MeldableLinkedList ll = new MeldableLinkedList();
        RootNode node = new RootNode(2);
        ll.insertNodeAtEnd(new RootNode(3));
        ll.insertNodeAtEnd(new RootNode(4));
        ll.insertNodeAtEnd(new RootNode(1));
        ll.insertNodeAtEnd(node);
        ll.insertNodeAtEnd(new RootNode(6));
        ll.insertNodeAtEnd(new RootNode(7));
        assertEquals(3, ll.getHead().val);
        assertEquals(7, ll.getTail().val);
        ll.deleteNode(node);
        assertFalse(ll.isEmpty());
        assertEquals(3, ll.getHead().val);
        assertEquals(7, ll.getTail().val);
    }

    @Test
     public void testLL4() {
        MeldableLinkedList ll = new MeldableLinkedList();
        ll.insertNodeAtEnd(new RootNode(3));
        ll.insertNodeAtEnd(new RootNode(4));
        ll.insertNodeAtEnd(new RootNode(1));
        ll.insertNodeAtEnd(new RootNode(6));
        ll.insertNodeAtEnd(new RootNode(7));
        for (int i = 0; i < 4; i++)
            ll.deleteNode(ll.getHead());

        assertEquals(7, ll.getHead().val);
        assertEquals(7, ll.getTail().val);
        ll.deleteNode(ll.getHead());
        assertTrue(ll.isEmpty());
    }

    @Test
    public void testLL4b() {
        MeldableLinkedList ll = new MeldableLinkedList();
        ll.insertNodeAtEnd(new RootNode(3));
        ll.insertNodeAtEnd(new RootNode(4));
        ll.insertNodeAtEnd(new RootNode(1));
        ll.insertNodeAtEnd(new RootNode(6));
        ll.insertNodeAtEnd(new RootNode(7));
        for (int i = 0; i < 4; i++)
            ll.deleteNode(ll.getTail());

        assertEquals(3, ll.getHead().val);
        assertEquals(3, ll.getTail().val);
        ll.deleteNode(ll.getTail());
        assertTrue(ll.isEmpty());
    }

    @Test
    public void testLLConcat() {
        MeldableLinkedList ll = new MeldableLinkedList();
        ll.insertNodeAtEnd(new RootNode(3));
        ll.insertNodeAtEnd(new RootNode(4));
        ll.insertNodeAtEnd(new RootNode(1));
        MeldableLinkedList ll2 = new MeldableLinkedList();
        ll2.insertNodeAtEnd(new RootNode(9));
        ll2.insertNodeAtEnd(new RootNode(10));
        ll2.insertNodeAtEnd(new RootNode(11));

        ll.concatWithList(ll2);

        assertEquals(3, ll.getHead().val);
        assertEquals(11, ll.getTail().val);

        for (int i = 0; i < 2; i++)
            ll.deleteNode(ll.getTail());

        assertEquals(9, ll.getTail().val);
        ll.deleteNode(ll.getTail());
        assertEquals(1, ll.getTail().val);
        ll.deleteNode(ll.getTail());
        assertEquals(4, ll.getTail().val);
    }

    @Test
    public void testLLConcatIterator() {
        MeldableLinkedList ll = new MeldableLinkedList();
        ll.insertNodeAtEnd(new RootNode(3));
        ll.insertNodeAtEnd(new RootNode(4));
        ll.insertNodeAtEnd(new RootNode(1));
        MeldableLinkedList ll2 = new MeldableLinkedList();
        ll2.insertNodeAtEnd(new RootNode(9));
        ll2.insertNodeAtEnd(new RootNode(10));
        ll2.insertNodeAtEnd(new RootNode(11));

        ll.concatWithList(ll2);

        Iterator<RootNode> it = ll.iterator();
        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }

        assertEquals(6, count);
    }

    @Test
    public void testLLConcatIterator2() {
        MeldableLinkedList ll = new MeldableLinkedList();
        ll.insertNodeAtEnd(new RootNode(3));
        ll.insertNodeAtEnd(new RootNode(4));
        ll.insertNodeAtEnd(new RootNode(1));
        MeldableLinkedList ll2 = new MeldableLinkedList();
        ll2.insertNodeAtEnd(new RootNode(9));
        ll2.insertNodeAtEnd(new RootNode(10));
        ll2.insertNodeAtEnd(new RootNode(11));

        ll.concatWithList(ll2);

        Iterator<RootNode> it = ll.iterator();
        assertEquals(3, it.next().val);
        assertEquals(4, it.next().val);
        assertEquals(1, it.next().val);
        assertEquals(9, it.next().val);
        assertEquals(10, it.next().val);
        assertEquals(11, it.next().val);
        assertFalse(it.hasNext());
    }

    @Test
    public void testLLConcatIterator3() {
        MeldableLinkedList ll = new MeldableLinkedList();
        ll.insertNodeAtEnd(new RootNode(3));
        ll.insertNodeAtEnd(new RootNode(4));
        ll.insertNodeAtEnd(new RootNode(1));
        MeldableLinkedList ll2 = new MeldableLinkedList();
        ll2.insertNodeAtEnd(new RootNode(9));
        ll2.insertNodeAtEnd(new RootNode(10));
        ll2.insertNodeAtEnd(new RootNode(11));

        ll.concatWithList(ll2);

        Iterator<RootNode> it = ll.iterator();
        assertEquals(3, it.next().val);
        assertEquals(4, it.next().val);
        it.remove();
        assertEquals(1, it.next().val);
        it.remove();
        assertEquals(9, it.next().val);
        assertEquals(10, it.next().val);
        it.remove();
        assertEquals(11, it.next().val);
        assertFalse(it.hasNext());
        //just removed 1, 4, and 10


        it = ll.iterator();
        assertEquals(3, it.next().val);
        assertEquals(9, it.next().val);
        assertEquals(11, it.next().val);
        assertFalse(it.hasNext());
    }
}
