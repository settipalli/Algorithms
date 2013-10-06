package ch2.test.pq;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ch2.pq.MaxPQ;
import edu.princeton.cs.introcs.In;

public class MaxPQTest {

    Integer[] inputNumber8k;
    MaxPQ     pq;


    @Before
    public void setUp() throws Exception {
        String filePathPrefix = "/home/ssettipalli/workspace/projects/github/Algorithms/src/ch2/test/data/Sort";
        int[] int8kvalues = In.readInts(filePathPrefix + "/8Kints.txt");
        inputNumber8k = new Integer[int8kvalues.length];
        for (int i = 0; i < int8kvalues.length; i++)
            inputNumber8k[i] = int8kvalues[i];

        pq = new MaxPQ(inputNumber8k.length);

        for (int v : inputNumber8k) {
            pq.insert(v);
        }
    }


    @Test
    public void testMaxPQ() {
        assertEquals(inputNumber8k.length, pq.size());
    }


    @Test
    public void testIsEmpty() {
        MaxPQ test = new MaxPQ(1);
        assertTrue(test.isEmpty());
        assertEquals(0, test.size());
        test.insert(100);
        assertFalse(test.isEmpty());
        assertEquals(1, test.size());
    }


    @Test
    public void testInsert() {
        int currentMax = (Integer) pq.delMax();
        int currentSize = pq.size();

        int newMax = currentMax + 1;
        pq.insert(newMax);

        assertTrue(pq.size() == currentSize + 1);
        assertEquals(newMax, pq.delMax());
        assertTrue(pq.size() == currentSize);
    }


    @Test
    public void testDelMax() {
        int currentMax = (Integer) pq.delMax();
        int nextMax = (Integer) pq.delMax();

        assertTrue(currentMax > nextMax);
        int newMax = currentMax + 1;
        pq.insert(newMax);

        assertEquals(newMax, pq.delMax());
    }
}
