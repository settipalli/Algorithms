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

import org.junit.Before;
import org.junit.Test;

import ch3.BST;
import edu.princeton.cs.introcs.In;

public class BSTTest {
    BST<String, String> tree;
    String[]            inputChars;


    @Before
    public void setUp() throws Exception {
        String filePathPrefix = "/home/ssettipalli/workspace/projects/github/Algorithms/src/ch3/test/data";
        inputChars = new In(filePathPrefix + "/tinyST.txt").readAllStrings();
        tree = new BST<>();
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
        BST<String, String> node = new BST<>();
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

}
