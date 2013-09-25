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

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import ch1.Stack;
import edu.princeton.cs.introcs.StdRandom;

public class StackTest {

    Stack<Integer> stack;
    int            size                 = 0;
    int            numberOfRandomValues = 10000000;


    @Before
    public void setUp() throws Exception {
        stack = new Stack<>();
        size = (int) (StdRandom.random() * numberOfRandomValues);
        for (int i = 0; i < size; i++) {
            stack.push((int) (StdRandom.random() * numberOfRandomValues));
        }
    }


    @Test
    public void testIsEmpty() {
        for (Integer t : stack) {
            stack.pop();
        }
        assertEquals(true, stack.isEmpty());
    }


    @Test
    public void testSize() {
        assertEquals(size, stack.size());
    }


    @Test
    public void testPush() {
        int testsize = (int) (StdRandom.random() * numberOfRandomValues);
        int[] value = new int[testsize];

        for (int i = 0; i < testsize; i++)
            value[i] = (int) (StdRandom.random() * numberOfRandomValues);

        for (int i = 0; i < testsize; i++)
            stack.push(value[i]);

        assertEquals(size + testsize, stack.size());

        for (int i = 0; i < testsize; i++)
            assertEquals(value[testsize - i - 1], (int) stack.pop());
    }


    @Test
    public void testPop() {
        int value = (int) (StdRandom.random() * numberOfRandomValues);
        stack.push(value);
        assertEquals(size + 1, stack.size());
        assertEquals(value, (int) stack.pop());
    }


    @Test
    public void testIterator() {
        // Empty the stack
        for (Integer t : stack)
            stack.pop();

        int testsize = (int) (StdRandom.random() * numberOfRandomValues);
        int[] value = new int[testsize];

        for (int i = 0; i < testsize; i++)
            value[i] = (int) (StdRandom.random() * numberOfRandomValues);

        for (int i = 0; i < testsize; i++)
            stack.push(value[i]);

        Iterator<Integer> stackItr = stack.iterator();

        // Check if the new elements are inserted into the stack using iterator.
        int i = testsize - 1;
        while (stackItr.hasNext()) {
            int v = (int) stackItr.next();
            assertEquals(value[i], v);
            i--;
        }
    }

}
