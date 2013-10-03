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
 * Implements QuickSort algorithm.
 * 
 * Algorithm:
 * =========
 * Sir Charles Antony Richard Hoare (receiver of Turing Award - 1980) devised
 * this algorithm in 1961.
 * 
 * Basic Plan:
 * ==========
 * - Shuffle Array
 * - Partition so that for some 'j':
 *      - entry a[j] is in place
 *      - no larger entry to the left of 'j'
 *      - no smaller entry to the right of 'j'.
 * - Sort each piece recursively
 * 
 */
public class QuickSort {

    /**
     * Quicksort Partitioning Design:
     * =============================
     * 
     * Phase 1: Repeat until i and j pointers cross.
     *  - scan 'i' from left to right so long as (a[i] < a[lo])
     *  - scan 'j' from right to left so long as a[j] > a[lo]
     *  - exchange a[i] and a[j]
     * 
     * Phase 2: When pointers cross:
     *  - exchange a[lo] and a[j]
     * 
     * @param a
     *            input array
     * @param lo
     *            position of the lower bound
     * @param hi
     *            position of the higher bound
     * @return index of the item now known to be in-place
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (lessThan(a[++i], a[lo])) {
                // find item on the left to swap
                // traverse the array from left to right as long as a[++i] is
                // less than a[lo]
                if (i == hi) break;
            }

            while (lessThan(a[lo], a[--j])) {
                // find item on the right to swap
                // traverse the array from right to left as long as a[--j] is
                // greater than a[lo]
                if (j == lo) break;
            }

            if (i >= j) {
                // check if pointers cross. Then, nothing to swap, break.
                break;
            }

            // Else, i has stopped at a value that is greater than a[lo] and j
            // has
            // stopped at an item that is less than a[lo]
            exchange(a, i, j);
        }
        exchange(a, lo, j); // swap with the partition item.
        return j; // return index of the item now known to be in-place.
    }


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
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
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
