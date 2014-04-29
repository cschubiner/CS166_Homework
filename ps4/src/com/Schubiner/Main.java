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
    public void testSmall() {
        LazyBinomialHeap lbh = new LazyBinomialHeap();
        lbh.enqueue(1);
        assertFalse(lbh.isEmpty());
        lbh.enqueue(1);
        assertFalse(lbh.isEmpty());
        lbh.extractMin();
        lbh.extractMin();
        assertTrue(lbh.isEmpty());
    }

    @Test
    public void testSmall1() {
        LazyBinomialHeap lbh = new LazyBinomialHeap();
        lbh.enqueue(1);
        assertFalse(lbh.isEmpty());
        lbh.enqueue(2);
        assertFalse(lbh.isEmpty());
        assertEquals(1, lbh.extractMin());
        assertEquals(2, lbh.extractMin());
        assertTrue(lbh.isEmpty());
    }

    @Test
    public void testEasy1() {
        LazyBinomialHeap lbh = new LazyBinomialHeap();
        lbh.enqueue(5);
        assertEquals(5, lbh.min());
        lbh.enqueue(1);
        assertEquals(1, lbh.min());
        lbh.enqueue(0);
        assertEquals(0, lbh.min());
        lbh.enqueue(-1);
        assertEquals(-1, lbh.min());
    }

    @Test
    public void testExtract() {
        LazyBinomialHeap lbh = new LazyBinomialHeap();
        lbh.enqueue(5);
        assertEquals(5, lbh.min());
        lbh.enqueue(1);
        assertEquals(1, lbh.min());
        assertEquals(1, lbh.extractMin());
        assertEquals(5, lbh.min());
        assertEquals(5, lbh.extractMin());
    }

    @Test
    public void testMeld() {
        LazyBinomialHeap lbh = new LazyBinomialHeap();
        lbh.enqueue(5);
        lbh.enqueue(2);
        lbh.enqueue(3);
        LazyBinomialHeap lbh2 = new LazyBinomialHeap();
        lbh2.enqueue(7);
        lbh2.enqueue(8);
        lbh2.enqueue(9);
        LazyBinomialHeap m = LazyBinomialHeap.meld(lbh, lbh2);
        assertFalse(m.isEmpty());
        assertEquals(2, m.min());

        LazyBinomialHeap lbh3 = new LazyBinomialHeap();
        lbh3.enqueue(-7);
        lbh3.enqueue(-10);
        lbh3.enqueue(-8);
        m = LazyBinomialHeap.meld(m, lbh3);
        assertFalse(m.isEmpty());
        assertEquals(-10, m.min());
        Iterator<RootNode> it = m.rootList.iterator();
        assertEquals(5, it.next().treeNode.key);
        assertEquals(2, it.next().treeNode.key);
        assertEquals(3, it.next().treeNode.key);
        assertEquals(7, it.next().treeNode.key);
        assertEquals(8, it.next().treeNode.key);
        assertEquals(9, it.next().treeNode.key);
        assertEquals(-7, it.next().treeNode.key);
        assertEquals(-10, it.next().treeNode.key);
        assertEquals(-8, it.next().treeNode.key);
        assertFalse(it.hasNext());
    }

    @Test
    public void testMeldExtract() {
        LazyBinomialHeap lbh = new LazyBinomialHeap();
        lbh.enqueue(5);
        lbh.enqueue(2);
        lbh.enqueue(3);
        LazyBinomialHeap lbh2 = new LazyBinomialHeap();
        lbh2.enqueue(7);
        lbh2.enqueue(8);
        lbh2.enqueue(9);
        LazyBinomialHeap m = LazyBinomialHeap.meld(lbh, lbh2);
        LazyBinomialHeap lbh3 = new LazyBinomialHeap();
        lbh3.enqueue(-7);
        lbh3.enqueue(-10);
        lbh3.enqueue(-8);
        m = LazyBinomialHeap.meld(m, lbh3);

        assertEquals(-10, m.extractMin());
        assertEquals(-8, m.extractMin());
        assertEquals(-7, m.extractMin());
        assertEquals(2, m.extractMin());
        assertEquals(3, m.extractMin());
        assertEquals(5, m.extractMin());
        assertEquals(7, m.extractMin());
        assertEquals(8, m.extractMin());
        assertEquals(9, m.extractMin());
        assertTrue(m.isEmpty());
    }


    // Linked list tests ----------------------------------------------
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
        assertEquals(2, ll.getHead().testValDontUse);
        assertEquals(3, ll.getTail().testValDontUse);
        ll.deleteNode(node);
        assertFalse(ll.isEmpty());
        assertEquals(3, ll.getHead().testValDontUse);
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
        assertEquals(3, ll.getHead().testValDontUse);
        assertEquals(7, ll.getTail().testValDontUse);
        ll.deleteNode(node);
        assertFalse(ll.isEmpty());
        assertEquals(3, ll.getHead().testValDontUse);
        assertEquals(7, ll.getTail().testValDontUse);
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

        assertEquals(7, ll.getHead().testValDontUse);
        assertEquals(7, ll.getTail().testValDontUse);
        ll.deleteNode(ll.getHead());
        assertTrue(ll.isEmpty());
    }

    @Test
    public void testLLInsertAfter() {
        MeldableLinkedList ll = new MeldableLinkedList();
        ll.insertNodeAtEnd(new RootNode(3));
        ll.insertNodeAtEnd(new RootNode(4));
        RootNode rn = new RootNode(5);
        ll.insertAfterNode(ll.getHead(), new RootNode(2));
        ll.insertNodeAtEnd(new RootNode(1));
        ll.insertNodeAtEnd(new RootNode(6));
        ll.insertAfterNode(ll.getTail(), new RootNode(7));

        assertEquals(3, ll.getHead().testValDontUse);
        ll.deleteNode(ll.getHead());
        assertEquals(2, ll.getHead().testValDontUse);
        assertEquals(7, ll.getTail().testValDontUse);

        ll.insertAfterNode(ll.getHead(), rn);
        ll.insertAfterNode(rn, new RootNode(12));
        ll.deleteNode(ll.getHead());
        assertEquals(5, ll.getHead().testValDontUse);
        ll.deleteNode(ll.getHead());
        assertEquals(12, ll.getHead().testValDontUse);
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

        assertEquals(3, ll.getHead().testValDontUse);
        assertEquals(3, ll.getTail().testValDontUse);
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

        assertEquals(3, ll.getHead().testValDontUse);
        assertEquals(11, ll.getTail().testValDontUse);

        for (int i = 0; i < 2; i++)
            ll.deleteNode(ll.getTail());

        assertEquals(9, ll.getTail().testValDontUse);
        ll.deleteNode(ll.getTail());
        assertEquals(1, ll.getTail().testValDontUse);
        ll.deleteNode(ll.getTail());
        assertEquals(4, ll.getTail().testValDontUse);
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
        assertEquals(3, it.next().testValDontUse);
        assertEquals(4, it.next().testValDontUse);
        assertEquals(1, it.next().testValDontUse);
        assertEquals(9, it.next().testValDontUse);
        assertEquals(10, it.next().testValDontUse);
        assertEquals(11, it.next().testValDontUse);
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
        assertEquals(3, it.next().testValDontUse);
        assertEquals(4, it.next().testValDontUse);
        it.remove();
        assertEquals(1, it.next().testValDontUse);
        it.remove();
        assertEquals(9, it.next().testValDontUse);
        assertEquals(10, it.next().testValDontUse);
        it.remove();
        assertEquals(11, it.next().testValDontUse);
        assertFalse(it.hasNext());
        //just removed 1, 4, and 10

        it = ll.iterator();
        assertEquals(3, it.next().testValDontUse);
        assertEquals(9, it.next().testValDontUse);
        assertEquals(11, it.next().testValDontUse);
        assertFalse(it.hasNext());
    }
}
