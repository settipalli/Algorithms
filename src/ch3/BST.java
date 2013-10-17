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

package ch3;

/**
 * Implements that Binary Search Tree datastructure.
 * 
 * A BST is a binary search tree in symmetric order.
 * 
 * A binary tree is either:
 *  - empty, Or
 *  - two disjoint binary trees (left and right)
 *  
 *  Symmetric Order: Each node has a key and every node key is:
 *  - Larger than all keys in the left subtree.
 *  - Smaller than all keys in the right subtree.
 */

public class BST<Key extends Comparable<Key>, Value> {

    private Node root; // root of the BST.

    /**
     * Represents a Node in the BST. It comprises of four fields.
     */
    private class Node {
        private Key   key;
        private Value value;
        private Node  left, right; // by default are assigned to 'null' in Java.


        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }


    /**
     * Triggers the overloaded put method with the first argument being the
     * 'root' of the BST.
     * 
     * @param key
     *            key of the new node
     * @param value
     *            value to be assigned for the new node
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }


    /**
     * Insert algorithm: if less, go left; if greater, go right; if null,
     * insert.
     * 
     * Cost: # compares = 1 + depth of the node.
     * 
     * @param x
     *            Node
     * @param key
     *            key of the new node
     * @param value
     *            value to be assigned for the new node
     * @return pointer to the node
     */
    public Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value);
        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;
        return x;
    }


    /**
     * Search for a given key in the BST. Algorithm: If less than the current
     * node value, go left; if greater, go right; if equal, search hit.
     * 
     * Cost: # compares = 1 + depth of the node.
     * 
     * @param key
     *            search the tree for the node with the key
     * @return the value stored in the node whose key matches the supplied key;
     *         null if the key is not found.
     */
    public Value get(Key key) {

        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.value;
        }

        return null;

    }


    /**
     * Checks if the tree is empty.
     * 
     * @return true if the tree is empty else false.
     */
    public boolean isEmpty() {
        return root == null;
    }


    /**
     * Find the smallest key that is either greater than or equal to the given
     * key.
     * 
     * @param key
     *            key whose ceiling key has to be computed
     * @return smallest key that is either greater than or equal to the given
     *         key.
     */
    public Key ceil(Key key) {
        Node x = ceil(root, key);

        if (x == null) return null;
        return x.key;
    }


    /**
     * Overloaded ceiling method that returns the node with the smallest key
     * that is either greater than or equal to the given key.
     * 
     * @param x
     *            starting node
     * @param key
     *            the key whose ceiling has to be computed
     * @return smallest key that is either greater than or equal to the given
     *         key
     */
    private Node ceil(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceil(x.left, key);
            if (t == null)
                return x;
            else
                return t;
        } else
            return ceil(x.right, key);

    }


    /**
     * Find the largest key that is either less than or equal to the given key.
     * 
     * @param key
     *            the key whose floor has to be computed
     * @return largest key that is either less than or equal to the given key
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        else
            return x.key;

    }


    /**
     * Overloaded floor function that returns a key which is the largest key
     * that is either less than or equal to the given key.
     * 
     * @param x
     *            start node of the BST
     * @param key
     *            the key whose floor has to be computed
     * 
     * @return largest key that is either less than or equal to the given key
     */
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            return floor(x.left, key);
        }

        // cmp > 0
        Node t = floor(x.right, key);
        if (t == null)
            return x;
        else
            return t;
    }
}
