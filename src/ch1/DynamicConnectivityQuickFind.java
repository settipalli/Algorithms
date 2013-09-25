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

package ch1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Implements the algorithm that solves teh UnionFind Dynamic Connectivity
 * problem.
 */
public class DynamicConnectivityQuickFind {

    private int[] id;   // access to component id (site indexed)
    private int   count; // number of components


    /**
     * Initiates N site with integers 0 to N-1
     */
    public DynamicConnectivityQuickFind(int N) {
        // Initialize component id array
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }


    /**
     * Find and report if two given nodes are in the same component.
     * 
     * @param p
     *            first node
     * @param q
     *            second node
     * @return true if p and q are in the same component, else false
     */
    boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    /**
     * Component identifier for p - 0 to N-1
     * 
     * @param p
     *            node whose component identifier has to be identified
     * @return the component identifier of the give node
     */
    int find(int p) {
        return id[p];
    }


    /**
     * Add a connection between a two nodes. This is also termed as merging two
     * components.
     * 
     * @param p
     *            represents the first node
     * @param q
     *            represents the second node to which a connection should be
     *            added with the first node
     */
    void union(int p, int q) {

        // To mark two sites (or points or circles) as connected, make sure

        // Put p and q into the same component.
        int pID = find(p);
        int qID = find(q);

        // Nothing to do if p and q are already in the same component.
        if (pID == qID) return;

        // Rename p's component to q's name
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }


    /**
     * Report the number of components.
     * 
     * @return number of components
     */
    int count() {
        return count;
    }


    /**
     * Print the site array.
     */
    public void printSites() {
        StdOut.println();
        StdOut.println("Sites layout:");
        for (int i = 0; i < id.length; i++) {
            StdOut.printf("%5d", i);
        }
        StdOut.println();
        for (int i = 0; i < id.length; i++) {
            StdOut.printf("%5d", id[i]);
        }
        StdOut.println();
    }


    /**
     * Client for Union-Find.
     * 
     * @param args
     */
    public static void main(String[] args) {

        int[] input = null;

        if (args.length != 1) {
            StdOut.println("Invalid arguments.");
            StdOut.println("Usage: java DynamicConnectivityQuickFind <input-data-set-file-name>");
            System.exit(1);
        }

        try {
            input = In.readInts(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Solve dynamic connectivity program on StdIn.
        int N = input[0]; // Read number of sites
        // Initialize N components
        DynamicConnectivityQuickFind uf = new DynamicConnectivityQuickFind(N);
        for (int i = 1; i < input.length; i += 2) {
            // Read pair to connect.
            int p = input[i];
            int q = input[i + 1];
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println("Connecting site:" + p + " and site: " + q);
        }
        uf.printSites();
        StdOut.println();
        StdOut.println("Total components: " + uf.count());
    }
}
