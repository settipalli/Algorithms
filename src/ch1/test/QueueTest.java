package ch1.test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import ch1.Queue;
import edu.princeton.cs.introcs.StdRandom;

public class QueueTest {

    Queue<Integer> queue;
    int            size                 = 0;
    int            numberOfRandomValues = 10000000;
    Integer[]      value;


    @Before
    public void setUp() throws Exception {
        queue = new Queue<>();
        size = (int) (StdRandom.random() * numberOfRandomValues);
        value = new Integer[size];
        for (int i = 0; i < size; i++) {
            value[i] = (int) (StdRandom.random() * numberOfRandomValues);
            queue.enqueue(value[i]);
        }
    }


    @Test
    public void testSize() {
        assertEquals(size, queue.size());
    }


    @Test
    public void testEnqueue() {
        assertEquals(size, queue.size());

        for (int i = 0; i < size; i++)
            assertEquals(value[i], queue.dequeue());

    }


    @Test
    public void testDequeue() {
        int value = (int) (StdRandom.random() * numberOfRandomValues);
        queue.enqueue(value);
        assertEquals(size + 1, queue.size());
        assertNotEquals(value, (int) queue.dequeue());

        for (int i = 0; i < size; i++)
            queue.dequeue();

        value = (int) (StdRandom.random() * numberOfRandomValues);
        queue.enqueue(value);
        assertEquals(value, (int) queue.dequeue());
    }


    @Test
    public void testIterator() {
        Iterator<Integer> queueItr = queue.iterator();

        // Check if the new elements are inserted into the queue using iterator.
        int i = 0;
        while (queueItr.hasNext()) {
            assertEquals(value[i], queueItr.next());
            i++;
        }
    }

}
