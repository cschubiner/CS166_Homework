package com.Schubiner;

import org.junit.Test;

import java.util.ArrayList;
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
}
