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
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import ch1.Bag;
import edu.princeton.cs.introcs.StdRandom;

public class BagTest {

    Bag<Integer> bag;
    int          size                 = 0;
    int          numberOfRandomValues = 100;
    Integer[]    value;


    @Before
    public void setUp() throws Exception {
        bag = new Bag<>();
        size = (int) (StdRandom.random() * numberOfRandomValues);
        value = new Integer[size];
        for (int i = 0; i < size; i++) {
            value[i] = (int) (StdRandom.random() * numberOfRandomValues);
            bag.add(value[i]);
        }
    }


    @Test
    public void testAdd() {
        int testsize = (int) (StdRandom.random() * numberOfRandomValues);
        int[] tmpvalue = new int[testsize];

        for (int i = 0; i < testsize; i++)
            tmpvalue[i] = (int) (StdRandom.random() * numberOfRandomValues);

        for (int i = 0; i < testsize; i++)
            bag.add(tmpvalue[i]);

        assertEquals(size + testsize, bag.size());

        Iterator<Integer> bagItr = bag.iterator();

        for (int i = size + testsize - 1; i >= size && bagItr.hasNext(); i--) {
            assertEquals((int) tmpvalue[i - size], (int) bagItr.next());
        }

        for (int i = size - 1; i >= 0 && bagItr.hasNext(); i--) {
            assertEquals((int) value[i], (int) bagItr.next());
        }

    }


    @Test
    public void testIsEmpty() {
        // We are not allowed to remove entries from the Bag that are added by
        // the setUp routine.
        assertEquals(false, bag.isEmpty());

        Bag<Integer> testBag = new Bag<>();
        assertEquals(true, testBag.isEmpty());
    }


    @Test
    public void testSize() {
        assertEquals(size, bag.size());
    }


    @Test
    public void testIterator() {
        Iterator<Integer> bagItr = bag.iterator();

        // Check if the new elements are inserted into the bag using iterator.
        int i = size - 1;
        while (bagItr.hasNext()) {
            assertEquals((int) value[i--], (int) bagItr.next());
        }
    }

}
