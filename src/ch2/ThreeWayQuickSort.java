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
import edu.princeton.cs.introcs.StdRandom;

/**
 *
 * Implements 3-way QuickSort algorithm. This is best suited where there are
 * duplicate keys.
 * 
 * Algorithm:
 * =========
 * An improvement over devised by Dijkstra over the standard QuickSort
 * algorithms implemented for the first time by Sir Charles Antony Richard Hoare
 * (receiver of Turing Award - 1980) in 1961.
 * 
 * Goal:
 * ====
 *  - Partition array into three parts so that:
 *      - Entries between lt and gt equal to partition item 'v'.
 *      - No larger entries to left of lt.
 *      - Not smaller elements to the right of gt.
 * 
 */
public class ThreeWayQuickSort {

    /**
     * Performs the core operation of this program i.e. sorting the entries in
     * an array that is expected to have implemented the Comparable interface.
     * 
     * The logic devised by Djkstra is:
     *  - Let 'v' be assigned the value of a[lo] which would act as a
     *    partitioning item.
     *  - Scan 'i' from left to right.
     *  - (a[i] < v) exchange a[lt] with a[i] and increment both lt and i.
     *  - (a[i] > v) exchange a[gt] with a[i] and decrement gt.
     *    Do not increment i.
     *  - (a[i] == v) increment i.
     * 
     * @param a
     *            an input array which is to be sorted and has implemented the
     *            Comparable interface
     * 
     * @param ascending
     *            if true, the input will be sorted in ascending order, else
     *            descending order
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo;
        int gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                // a[i] is smaller than v
                exchange(a, lt++, i++);
            } else if (cmp > 0) {
                // a[i] is greater than v
                exchange(a, i, gt--);
            } else {
                // a[i] and v are equal.
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }


    /**
     * Performs the core operation of this program by triggering the recursive
     * overloaded sort method on the contents of the array.
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
        StdRandom.shuffle(a); // shuffle needed to avoid worst case performance
                              // of quicksort.
        sort(a, 0, a.length - 1); // length - 1 is required
        if (ascending == false) {
            // Reverse the array
            for (int i = 0; i < a.length / 2; i++) {
                Comparable t = a[i];
                a[i] = a[a.length - 1 - i];
                a[a.length - 1 - i] = t;
            }
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
     * order. The contents are scanned between lo and hi values (inclusive).
     * 
     * @param a
     *            input array which is expected to have implemented the
     *            Comparable interface.
     * 
     * @param lo
     *            lower bound
     * 
     * @param hi
     *            higher bound
     * 
     * @param ascending
     *            if true, the input will be checked for ascending order, else
     *            descending order
     * 
     * @return true if all the entries in the array are in the expected order.
     */
    public static boolean isSorted(Comparable[] a, int lo, int hi,
            boolean ascending) {
        // Test whether the array entries are in order.
        for (int i = lo + 1; i <= hi; i++) {
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
