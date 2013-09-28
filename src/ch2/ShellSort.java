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

package ch2;

import edu.princeton.cs.introcs.StdOut;

/**
 * Implements ShellSort algorithm.
 * 
 * Algorithm:
 * 
 * The technique to assume each value as a card; consider the cards one at a
 * time, insert each into its proper position among the already considered ones
 * (keeping them sorted)
 * 
 */
public class ShellSort {

    /**
     * Performs the core operation of this program i.e. sorting the entries in
     * an array that is expected to have implemented the Comparable interface.
     * 
     * @param a
     *            an input array which is to be sorted and has implemented the
     *            Comparable interface
     * 
     * @param ascending
     *            if true, the input will be sorted in ascending order, else
     *            descending order
     */
    public static void sort(Comparable[] a, boolean ascending) {
        int N = a.length;
        int h = 1;

        // We use Donald Knuth's formula for 'h' = 3x+1
        while (h < N / 3)
            h = 3 * h + 1;

        while (h >= 1) {
            // Find the smallest entry in the array and exchange it with the hth
            // entry.
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    // Inner loop traverses the array backwards from the ith
                    // position with a difference of 'h' elements during each
                    // iteration and interchanges any elements that are not
                    // sorted.
                    if (ascending == true) {
                        if (lessThan(a[j], a[j - h])) {
                            // if a[j] is less than a[j-1], interchange them.
                            exchange(a, j, j - h);
                        }
                    } else {
                        if (lessThan(a[j - h], a[j])) {
                            // if a[j] is greater than a[j-1], interchange them.
                            exchange(a, j, j - h);
                        }
                    }
                }
            }

            h = h / 3;
        }
    }


    /**
     * A helper method to return true if the first argument is less than the
     * second argument.
     * 
     * Note: Both the first and the second arguments should implement the
     * {@link Comparable} interface and should override the compareTo method.
     * 
     * @param v
     *            first value to be compared against the second value
     * @param w
     *            second value that would be compared against the first
     * @return true if the first value is less than the second value, else false
     */
    private static boolean lessThan(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    /**
     * Swaps two values in the array which is expected to have implemented the
     * Comparable interface provided the reference to the array and the
     * positions to be swapped are passed as arguments.
     * 
     * @param a
     *            an array that is expected to have implemented the Comparable
     *            interface
     * @param i
     *            the position in the array which has to be swapped with the
     *            other position signified by the second argument
     * @param j
     *            the position in the array which has to be swapped with the
     *            other position signified by the first argument
     */
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    /**
     * Display the array on the console.
     * 
     * @param a
     *            the input array which is expected to have implemented the
     *            Comparable interface
     */
    private static void show(Comparable[] a) {
        // Print the array, on a single line.
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + "  ");
        }
        StdOut.println();
    }


    /**
     * A helper method that verifies if the contents of the array are in sorted
     * order.
     * 
     * @param a
     *            input array which is expected to have implemented the
     *            Comparable interface.
     * 
     * @param ascending
     *            if true, the input will be checked for ascending order, else
     *            descending order
     * 
     * @return true if all the entries in the array are in the expected order.
     */
    public static boolean isSorted(Comparable[] a, boolean ascending) {
        // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++) {
            if (ascending == true) {
                // If the consecutive number in less the previous number in the
                // array, return false
                if (lessThan(a[i], a[i - 1])) return false;
            } else {
                // Descending - the next number in the array should be less than
                // the current number.
                if (lessThan(a[i - 1], a[i])) return false;
            }
        }
        return true;
    }

}
