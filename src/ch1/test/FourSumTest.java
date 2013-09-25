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

import org.junit.Test;

import ch1.FourSum;

public class FourSumTest {

    FourSum ts;
    // String filePathPrefix =
    // "<add-path-to-the-folder-where-test-data-is-stored>";
    String  filePathPrefix = "/home/ssettipalli/workspace/projects/github/Algorithms/src/ch1/test/data/ThreeSum/";


    @Test
    public void testCountWith1000Numbers() {
        String fileName = filePathPrefix + "1Kints.txt";
        int expectedValue = 13668;

        ts = new FourSum(fileName);
        assertEquals(expectedValue, ts.count());
    }

    /*
     * Testing with 2000 and 4000 numbers consumes a very long time due to the
     * brute force algorithm that is O(n^4).
     */
}
