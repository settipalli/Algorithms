/*******************************************************************************
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
 ******************************************************************************/

package ch1.test;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import ch1.ResizingArrayStack;
import edu.princeton.cs.introcs.StdRandom;

/**
 * Unit Test class for {@link ResizingArrayStack}
 */
public class ResizingArrayStackTest {

    ResizingArrayStack<Integer> ras;
    int                         size                 = 0;
    int                         numberOfRandomValues = 10000000;


    /**
     * Create and populate with random integers an object representing
     * ResizingArrayStack.
     * 
     * @throws Exception
     *             if failed to create and initialize {@link ResizingArrayStack}
     */
    @Before
    public void setUp() throws Exception {
        ras = new ResizingArrayStack<>();
        size = (int) (StdRandom.random() * numberOfRandomValues);
        for (int i = 0; i < size; i++) {
            ras.push((int) (StdRandom.random() * numberOfRandomValues));
        }
    }


    /**
     * Test method for {@link ch1.ResizingArrayStack#isEmpty()}.
     * 
     * @throws Exception
     *             raised by setUp routine
     */
    @Test
    public void testIsEmpty() throws Exception {
        for (Integer t : ras) {
            ras.pop();
        }
        assertEquals(true, ras.isEmpty());
    }


    /**
     * Test method for {@link ch1.ResizingArrayStack#size()}.
     */
    @Test
    public void testSize() {
        assertEquals(size, ras.size());
    }


    /**
     * Test method for {@link ch1.ResizingArrayStack#push(java.lang.Object)}.
     */
    @Test
    public void testPush() {
        int testsize = (int) (StdRandom.random() * numberOfRandomValues);
        int[] value = new int[testsize];

        for (int i = 0; i < testsize; i++)
            value[i] = (int) (StdRandom.random() * numberOfRandomValues);

        for (int i = 0; i < testsize; i++)
            ras.push(value[i]);

        assertEquals(size + testsize, ras.size());

        for (int i = 0; i < testsize; i++)
            assertEquals(value[testsize - i - 1], (int) ras.pop());
    }


    /**
     * Test method for {@link ch1.ResizingArrayStack#pop()}.
     */
    @Test
    public void testPop() {
        int value = (int) (StdRandom.random() * numberOfRandomValues);
        ras.push(value);
        assertEquals(size + 1, ras.size());
        assertEquals(value, (int) ras.pop());
    }


    /**
     * Test method for {@link ch1.ResizingArrayStack#iterator()}.
     */
    @Test
    public void testIterator() {
        // Empty the stack
        for (Integer t : ras)
            ras.pop();

        int testsize = (int) (StdRandom.random() * numberOfRandomValues);
        int[] value = new int[testsize];

        for (int i = 0; i < testsize; i++)
            value[i] = (int) (StdRandom.random() * numberOfRandomValues);

        for (int i = 0; i < testsize; i++)
            ras.push(value[i]);

        Iterator<Integer> rasItr = ras.iterator();

        // Check if the new elements are inserted into the stack using iterator.
        int i = testsize - 1;
        while (rasItr.hasNext()) {
            int v = (int) rasItr.next();
            assertEquals(value[i], v);
            i--;
        }
    }

}
