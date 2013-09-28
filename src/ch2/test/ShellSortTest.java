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

import ch2.ShellSort;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class ShellSortTest {

    String[]  inputChars;
    String[]  inputWords;
    Integer[] inputNumber1k;
    Integer[] inputNumber2k;
    Integer[] inputNumber4k;
    Integer[] inputNumber8k;


    public ShellSortTest() {
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
        ShellSort.sort(inputChars, true);
        boolean isSorted = ShellSort.isSorted(inputChars, true);
        assertEquals(true, isSorted);
        for (String c : inputChars)
            StdOut.print("\"" + c + "\", ");

        // Descending order sort - Characters
        StdOut.println();
        ShellSort.sort(inputChars, false);
        isSorted = ShellSort.isSorted(inputChars, false);
        assertEquals(true, isSorted);
        for (String c : inputChars)
            StdOut.print("\"" + c + "\", ");

        // Ascending order sort - words
        StdOut.println();
        ShellSort.sort(inputWords, true);
        isSorted = ShellSort.isSorted(inputWords, true);
        assertEquals(true, isSorted);
        for (String c : inputWords)
            StdOut.print("\"" + c + "\", ");

        // Descending order sort - words
        StdOut.println();
        ShellSort.sort(inputWords, false);
        isSorted = ShellSort.isSorted(inputWords, false);
        assertEquals(true, isSorted);
        for (String c : inputWords)
            StdOut.print("\"" + c + "\", ");

        // Ascending order sort - 1000 numbers
        StdOut.println();
        ShellSort.sort(inputNumber1k, true);
        isSorted = ShellSort.isSorted(inputNumber1k, true);
        assertEquals(true, isSorted);

        // Descending order sort - 1000 numbers
        StdOut.println();
        ShellSort.sort(inputNumber1k, false);
        isSorted = ShellSort.isSorted(inputNumber1k, false);
        assertEquals(true, isSorted);

        // Ascending order sort - 2000 numbers
        StdOut.println();
        ShellSort.sort(inputNumber2k, true);
        isSorted = ShellSort.isSorted(inputNumber2k, true);
        assertEquals(true, isSorted);

        // Descending order sort - 2000 numbers
        StdOut.println();
        ShellSort.sort(inputNumber2k, false);
        isSorted = ShellSort.isSorted(inputNumber2k, false);
        assertEquals(true, isSorted);

        // Ascending order sort - 4000 numbers
        StdOut.println();
        ShellSort.sort(inputNumber4k, true);
        isSorted = ShellSort.isSorted(inputNumber4k, true);
        assertEquals(true, isSorted);

        // Descending order sort - 4000 numbers
        StdOut.println();
        ShellSort.sort(inputNumber4k, false);
        isSorted = ShellSort.isSorted(inputNumber4k, false);
        assertEquals(true, isSorted);

        // Ascending order sort - 8000 numbers
        StdOut.println();
        ShellSort.sort(inputNumber8k, true);
        isSorted = ShellSort.isSorted(inputNumber8k, true);
        assertEquals(true, isSorted);

        // Descending order sort - 8000 numbers
        StdOut.println();
        ShellSort.sort(inputNumber8k, false);
        isSorted = ShellSort.isSorted(inputNumber8k, false);
        assertEquals(true, isSorted);
    }


    @Test
    public void testIsSorted() {

        // Check for ascending order sort - characters.
        String[] inputSet1 = { "A", "E", "E", "L", "M", "O", "P", "R", "S",
                "T", "X" };
        boolean isSorted = ShellSort.isSorted(inputSet1, true);
        assertEquals(true, isSorted);

        // Check for descending order sort - characters.
        String[] inputSet2 = { "X", "T", "S", "R", "P", "O", "M", "L", "E",
                "E", "A" };
        isSorted = ShellSort.isSorted(inputSet2, false);
        assertEquals(true, isSorted);

        // Check for ascending order sort - words.
        String[] inputSet3 = { "all", "bad", "bed", "bug", "dad", "dim", "dug",
                "egg", "fee", "few", "for", "gig", "hut", "ilk", "jam", "jay",
                "jot", "joy", "men", "nob", "now", "owl", "rap", "sky", "sob",
                "tag", "tap", "tar", "tip", "wad", "was", "wee", "yes", "yet",
                "zoo" };
        isSorted = ShellSort.isSorted(inputSet3, true);
        assertEquals(true, isSorted);

        // Check for descending order sort - words.
        String[] inputSet4 = { "zoo", "yet", "yes", "wee", "was", "wad", "tip",
                "tar", "tap", "tag", "sob", "sky", "rap", "owl", "now", "nob",
                "men", "joy", "jot", "jay", "jam", "ilk", "hut", "gig", "for",
                "few", "fee", "egg", "dug", "dim", "dad", "bug", "bed", "bad",
                "all", };
        isSorted = ShellSort.isSorted(inputSet4, false);
        assertEquals(true, isSorted);

    }
}
