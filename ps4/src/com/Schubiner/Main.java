package com.Schubiner;

import org.junit.Test;


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
}
