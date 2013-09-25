/*
 * The MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * ****************************************************************************
 */

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
