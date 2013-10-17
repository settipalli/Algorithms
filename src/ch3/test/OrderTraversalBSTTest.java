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

package ch3.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import ch1.Queue;
import ch2.QuickSort;
import ch3.OrderTraversalBST;
import edu.princeton.cs.introcs.In;

public class OrderTraversalBSTTest {
    OrderTraversalBST<String, String> tree;
    String[]                          inputChars;


    @Before
    public void setUp() throws Exception {
        String filePathPrefix = "/home/ssettipalli/workspace/projects/github/Algorithms/src/ch3/test/data";
        inputChars = new In(filePathPrefix + "/tinyST.txt").readAllStrings();
        tree = new OrderTraversalBST<>();
        for (String s : inputChars) {
            tree.put(s, s);
        }
    }


    @Test
    public void testPutKeyValue() {
        String key = "X";
        tree.put(key, key);
        String value = tree.get(key);
        assertNotNull(value);
        assertEquals(key, value);
    }


    @Test
    public void testGet() {
        for (String s : inputChars) {
            String value = tree.get(s);
            assertNotNull(value);
            assertEquals(s, value);
        }
    }


    @Test
    public void testIsEmpty() {
        OrderTraversalBST<String, String> node = new OrderTraversalBST<>();
        assertTrue(node.isEmpty());
        assertFalse(tree.isEmpty());
    }


    @Test
    public void testFloor() {
        String key = tree.floor("S");
        assertEquals(key, "S");

        key = tree.floor("G");
        assertEquals(key, "E");

        key = tree.floor("U");
        assertEquals(key, "S");

        key = tree.floor("P");
        assertEquals(key, "P");

        key = tree.floor("Q");
        assertEquals(key, "P");

        key = tree.floor("K");
        assertEquals(key, "H");

        key = tree.floor("B");
        assertEquals(key, "A");

        key = tree.floor("D");
        assertEquals(key, "C");

        key = tree.floor("F");
        assertEquals(key, "E");

        key = tree.floor("T");
        assertEquals(key, "S");
    }


    @Test
    public void testCeil() {
        String key = tree.ceil("S");
        assertEquals(key, "S");

        key = tree.ceil("B");
        assertEquals(key, "C");

        key = tree.ceil("F");
        assertEquals(key, "H");

        key = tree.ceil("Z");
        assertTrue(key == null);

        key = tree.ceil("U");
        assertEquals(key, "X");
    }


    @Test
    public void testRank() {
        int rank = tree.rank("S");
        assertEquals(8, rank);

        rank = tree.rank("X");
        assertEquals(9, rank);

        rank = tree.rank("E");
        assertEquals(2, rank);

        rank = tree.rank("R");
        assertEquals(7, rank);

        rank = tree.rank("A");
        assertEquals(0, rank);

        rank = tree.rank("C");
        assertEquals(1, rank);

        rank = tree.rank("H");
        assertEquals(3, rank);

        rank = tree.rank("M");
        assertEquals(5, rank);

        rank = tree.rank("P");
        assertEquals(6, rank);

        rank = tree.rank("L");
        assertEquals(4, rank);

        rank = tree.rank("Z");
        assertEquals(10, rank);
    }


    @Test
    public void testKeyInOrder() {
        String[] output = new String[inputChars.length];
        for (int i = 0; i < inputChars.length; i++)
            output[i] = inputChars[i];

        QuickSort.sort(output, true);
        Queue<String> q = (Queue) tree.keysInOrder();

        Iterator<String> queueItr = q.iterator();
        int i = 0;
        while (queueItr.hasNext()) {
            assertEquals(output[i++], queueItr.next());
        }
    }


    @Test
    public void testKeyPreOrder() {
        String[] output = { "S", "E", "A", "C", "R", "H", "M", "L", "P", "X" };
        Queue<String> q = (Queue) tree.keysPreOrder();
        Iterator<String> qItr = q.iterator();
        int i = 0;
        while (qItr.hasNext()) {
            assertEquals(output[i++], qItr.next());
        }
    }


    @Test
    public void testKeyPostOrder() {
        String[] output = { "C", "A", "L", "P", "M", "H", "R", "E", "X", "S" };
        Queue<String> q = (Queue) tree.keysPostOrder();
        Iterator<String> qItr = q.iterator();
        int i = 0;
        while (qItr.hasNext()) {
            assertEquals(output[i++], qItr.next());
        }
    }
}
