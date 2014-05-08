package com.Schubiner;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("test");
    }

    @Test
    public void testEasy() {
        double[] arr = {2, 3};
        WeightBalancedTree wbt = new WeightBalancedTree(arr);
        assertTrue(wbt.contains(0));
        assertTrue(wbt.contains(1));
        assertFalse(wbt.contains(2));
    }

    @Test
    public void testSplay() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        int testMax = 100;
        for (int i = 0; i < testMax; i++) {
            a.add(i);
        }
        Object[] array =  a.toArray();

        Collections.shuffle(Arrays.asList(array));
        SplayTree wbt = new SplayTree();
        for (int i = 0; i < testMax; i++) {
            wbt.insert((Integer)array[i]);
        }

        for (int i = 0; i < testMax; i++) {
            assertTrue(wbt.contains((Integer)array[i]));
        }
    }

    @Test
    public void testSplayEasy() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        SplayTree wbt = new SplayTree();
        wbt.insert(2);
        wbt.insert(1);
        wbt.insert(4);
        assertTrue(wbt.contains(2));
        assertTrue(wbt.contains(1));
        assertTrue(wbt.contains(4));
        assertFalse(wbt.contains(0));
    }

    @Test
    public void testSplayMed() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        SplayTree wbt = new SplayTree();
        wbt.insert(5);
        assertTrue(wbt.contains(5));
        wbt.insert(2);
        assertTrue(wbt.contains(5));
        assertTrue(wbt.contains(2));
        wbt.insert(4);
        assertTrue(wbt.contains(5));
        assertTrue(wbt.contains(2));
        assertTrue(wbt.contains(4));
        wbt.insert(1);
        assertTrue(wbt.contains(5));
        assertTrue(wbt.contains(2));
        assertTrue(wbt.contains(4));
        assertTrue(wbt.contains(1));
        wbt.insert(3);
        assertTrue(wbt.contains(5));
        assertTrue(wbt.contains(2));
        assertTrue(wbt.contains(4));
        assertTrue(wbt.contains(1));
        assertTrue(wbt.contains(3));
        wbt.insert(0);
        assertTrue(wbt.contains(5));
        assertTrue(wbt.contains(2));
        assertTrue(wbt.contains(4));
        assertTrue(wbt.contains(1));
        assertTrue(wbt.contains(3));
        assertTrue(wbt.contains(0));
        assertFalse(wbt.contains(6));

    }
}
