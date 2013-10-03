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

package ch2.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch2.QuickSort;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class ThreeWayQuickSortTest {

    String[]  inputChars;
    String[]  inputWords;
    Integer[] inputNumber1k;
    Integer[] inputNumber2k;
    Integer[] inputNumber4k;
    Integer[] inputNumber8k;


    public ThreeWayQuickSortTest() {
        String filePathPrefix = "/home/ssettipalli/workspace/projects/github/Algorithms/src/ch2/test/data/Sort";

        inputChars = In.readStrings(filePathPrefix + "/tiny.txt");

        inputWords = In.readStrings(filePathPrefix + "/words3.txt");

        int[] int1kvalues = In.readInts(filePathPrefix + "/1Kints.txt");
        inputNumber1k = new Integer[int1kvalues.length];
        for (int i = 0; i < int1kvalues.length; i++)
            inputNumber1k[i] = int1kvalues[i];

        int[] int2kvalues = In.readInts(filePathPrefix + "/2Kints.txt");
        inputNumber2k = new Integer[int2kvalues.length];
        for (int i = 0; i < int2kvalues.length; i++)
            inputNumber2k[i] = int2kvalues[i];

        int[] int4kvalues = In.readInts(filePathPrefix + "/4Kints.txt");
        inputNumber4k = new Integer[int4kvalues.length];
        for (int i = 0; i < int4kvalues.length; i++)
            inputNumber4k[i] = int4kvalues[i];

        int[] int8kvalues = In.readInts(filePathPrefix + "/8Kints.txt");
        inputNumber8k = new Integer[int8kvalues.length];
        for (int i = 0; i < int8kvalues.length; i++)
            inputNumber8k[i] = int8kvalues[i];

    }


    @Test
    public void testSort() {

        // Ascending order sort - Characters
        QuickSort.sort(inputChars, true);
        boolean isSorted = QuickSort.isSorted(inputChars, 0,
                inputChars.length - 1, true);
        assertEquals(true, isSorted);
        for (String c : inputChars)
            StdOut.print("\"" + c + "\", ");

        // Descending order sort - Characters
        StdOut.println();
        QuickSort.sort(inputChars, false);
        isSorted = QuickSort.isSorted(inputChars, 0, inputChars.length - 1,
                false);
        assertEquals(true, isSorted);
        for (String c : inputChars)
            StdOut.print("\"" + c + "\", ");

        // Ascending order sort - words
        StdOut.println();
        QuickSort.sort(inputWords, true);
        isSorted = QuickSort.isSorted(inputWords, 0, inputWords.length - 1,
                true);
        assertEquals(true, isSorted);
        for (String c : inputWords)
            StdOut.print("\"" + c + "\", ");

        // Descending order sort - words
        StdOut.println();
        QuickSort.sort(inputWords, false);
        isSorted = QuickSort.isSorted(inputWords, 0, inputWords.length - 1,
                false);
        assertEquals(true, isSorted);
        for (String c : inputWords)
            StdOut.print("\"" + c + "\", ");

        // Ascending order sort - 1000 numbers
        StdOut.println();
        QuickSort.sort(inputNumber1k, true);
        isSorted = QuickSort.isSorted(inputNumber1k, 0,
                inputNumber1k.length - 1, true);
        assertEquals(true, isSorted);

        // Descending order sort - 1000 numbers
        StdOut.println();
        QuickSort.sort(inputNumber1k, false);
        isSorted = QuickSort.isSorted(inputNumber1k, 0,
                inputNumber1k.length - 1, false);
        assertEquals(true, isSorted);

        // Ascending order sort - 2000 numbers
        StdOut.println();
        QuickSort.sort(inputNumber2k, true);
        isSorted = QuickSort.isSorted(inputNumber2k, 0,
                inputNumber2k.length - 1, true);
        assertEquals(true, isSorted);

        // Descending order sort - 2000 numbers
        StdOut.println();
        QuickSort.sort(inputNumber2k, false);
        isSorted = QuickSort.isSorted(inputNumber2k, 0,
                inputNumber2k.length - 1, false);
        assertEquals(true, isSorted);

        // Ascending order sort - 4000 numbers
        StdOut.println();
        QuickSort.sort(inputNumber4k, true);
        isSorted = QuickSort.isSorted(inputNumber4k, 0,
                inputNumber4k.length - 1, true);
        assertEquals(true, isSorted);

        // Descending order sort - 4000 numbers
        StdOut.println();
        QuickSort.sort(inputNumber4k, false);
        isSorted = QuickSort.isSorted(inputNumber4k, 0,
                inputNumber4k.length - 1, false);
        assertEquals(true, isSorted);

        // Ascending order sort - 8000 numbers
        StdOut.println();
        QuickSort.sort(inputNumber8k, true);
        isSorted = QuickSort.isSorted(inputNumber8k, 0,
                inputNumber8k.length - 1, true);
        assertEquals(true, isSorted);

        // Descending order sort - 8000 numbers
        StdOut.println();
        QuickSort.sort(inputNumber8k, false);
        isSorted = QuickSort.isSorted(inputNumber8k, 0,
                inputNumber8k.length - 1, false);
        assertEquals(true, isSorted);
    }


    @Test
    public void testIsSorted() {

        // Check for ascending order sort - characters.
        String[] inputSet1 = { "A", "E", "E", "L", "M", "O", "P", "R", "S",
                "T", "X" };
        boolean isSorted = QuickSort.isSorted(inputSet1, 0,
                inputSet1.length - 1, true);
        assertEquals(true, isSorted);

        // Check for descending order sort - characters.
        String[] inputSet2 = { "X", "T", "S", "R", "P", "O", "M", "L", "E",
                "E", "A" };
        isSorted = QuickSort
                .isSorted(inputSet2, 0, inputSet2.length - 1, false);
        assertEquals(true, isSorted);

        // Check for ascending order sort - words.
        String[] inputSet3 = { "all", "bad", "bed", "bug", "dad", "dim", "dug",
                "egg", "fee", "few", "for", "gig", "hut", "ilk", "jam", "jay",
                "jot", "joy", "men", "nob", "now", "owl", "rap", "sky", "sob",
                "tag", "tap", "tar", "tip", "wad", "was", "wee", "yes", "yet",
                "zoo" };
        isSorted = QuickSort.isSorted(inputSet3, 0, inputSet3.length - 1, true);
        assertEquals(true, isSorted);

        // Check for descending order sort - words.
        String[] inputSet4 = { "zoo", "yet", "yes", "wee", "was", "wad", "tip",
                "tar", "tap", "tag", "sob", "sky", "rap", "owl", "now", "nob",
                "men", "joy", "jot", "jay", "jam", "ilk", "hut", "gig", "for",
                "few", "fee", "egg", "dug", "dim", "dad", "bug", "bed", "bad",
                "all", };
        isSorted = QuickSort
                .isSorted(inputSet4, 0, inputSet4.length - 1, false);
        assertEquals(true, isSorted);

    }
}
