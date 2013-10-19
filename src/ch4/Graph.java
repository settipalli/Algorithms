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

package ch4;

import ch1.Bag;
import edu.princeton.cs.introcs.In;

/**
 * Implements a Graph using Adjacency-list representation.
 * 
 */
public class Graph {
    private final int      V;  // Vertices are number from 0 to V-1
    private Bag<Integer>[] adj; // Adjacency list of Bag datatypes.


    /**
     * Construct the empty Graph taking into account the number of vertices it
     * should be supporting.
     * 
     * @param V
     *            number of vertices in the Graph
     */
    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }


    /**
     * Return the number of vertices in the Graph.
     * 
     * @return number of vertices in the Graph
     */
    public int V() {
        return V;
    }


    /**
     * Add an edge between two vertices in the Graph.
     * 
     * @param v
     *            first vertex
     * @param w
     *            second vertex
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }


    /**
     * Return an iterable object for the edges list for a given vertex.
     * 
     * @param v
     *            vertex whose edge-list is supposed to be returned
     * @return iterable edge-list for the given vertex
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


    /**
     * Find the number of edges incident and leaving the given vertex.
     * 
     * @param G
     *            the Graph
     * @param v
     *            vertex whose degree has to be found
     * @return the degree of the given vertex
     */
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v))
            degree++;
        return degree;
    }


    /**
     * Find the maximum degree in the Graph.
     * 
     * @param G
     *            the Graph
     * @return value that represents the maximum degree in the Graph
     */
    public static int maxDegree(Graph G) {
        int maxDegree = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v)) {
                int d = degree(G, v);
                maxDegree = d > maxDegree ? d : maxDegree;
            }
        return maxDegree;
    }


    /**
     * Find the number of self loops in the Graph.
     * 
     * @param G
     *            the Graph
     * @return the total number of self loops in the given Graph
     */
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v == w) count++;
        return count / 2; // each edge would have bene counted twice.
    }
}
