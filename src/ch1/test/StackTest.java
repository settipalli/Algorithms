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
