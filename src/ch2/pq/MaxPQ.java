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

package ch2.pq;

import edu.princeton.cs.introcs.StdOut;

/**
 * Implements a priority queue where the maximum value is obtained on every call
 * to delMax(). To achieve this, the maximum value is always stored as a parent
 * node.
 * 
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int   N;


    @SuppressWarnings("unchecked")
    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }


    public boolean isEmpty() {
        return N == 0;
    }


    public int size() {
        return N;
    }


    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }


    public Key delMax() {
        Key max = pq[1]; // Considering that array is 1 based.
        exch(1, N--);
        sink(1);
        pq[N + 1] = null; // prevent loitering.
        return max;
    }


    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            /*
             * k/2 is the parent of k. Exchange the child (k) and parent (k/2)
             * if the value of the parent is less than the child.
             */
            exch(k, k / 2);
            k = k / 2;
        }
    }


    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k; // / Children of node at k are at 2k and 2k+1
            if (j < N && less(j, j + 1)) {
                /* find the larger subchild - to exchange with the root */
                j++;
            }

            /* Check if the larger child is greater than node at k */
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }


    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


    public void display() {
        StdOut.println("Contents of the MaxPQ:");
        for (int i = 1; i <= N; i++)
            StdOut.print(pq[i] + " ");
        StdOut.println();
    }
}
