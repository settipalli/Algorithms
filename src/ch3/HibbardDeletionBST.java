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

import ch1.Queue;

/**
 * Implements that Binary Search Tree data structure with inorder, preorder and
 * postorder traversal choices.
 * 
 * Implements the algorithms that can delete a key using Hibbard Deletion
 * technique.
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

public class HibbardDeletionBST<Key extends Comparable<Key>, Value> {

    private Node root; // root of the BST.

    /**
     * Represents a Node in the BST. It comprises of four fields.
     */
    private class Node {
        private Key   key;
        private Value value;
        private Node  left, right; // by default are assigned to 'null' in Java.
        private int   count;      // Count of number of nodes in the subtrees.


        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }

    }


    /**
     * Return the number of nodes in left subtree + right subtree + 1 (for the
     * current node).
     * 
     * @param x
     *            node whose size has to be reported
     * @return number of nodes in the subtrees + 1 (for current node)
     */
    public int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.count;
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
        x.count = 1 + size(x.left) + size(x.right);
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


    /**
     * Return the rank of the key i.e. number of keys less than the given key.
     * 
     * @param key
     *            the key whose rank has to be found.
     * @return number of keys less than or equal to the given key = rank
     */
    public int rank(Key key) {
        return rank(root, key);
    }


    /**
     * Overloaded rank method that return the rank of the key i.e. number of
     * keys less than the given key.
     * 
     * @param x
     *            starting node.
     * @param key
     *            the key whose rank has to be found.
     * @return number of keys less than or equal to the given key = rank
     */
    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(x.right, key);
        } else
            // cmp == 0
            return size(x.left);
    }


    /**
     * Returns a iterable object that can be used to traverse the tree in
     * in-order fashion.
     * 
     * @return iterable object (a queue) that allows us to traverse the tree in
     *         in-order fashion.
     */
    public Iterable<Key> keysInOrder() {
        Queue<Key> q = new Queue<>();
        inorder(root, q);
        return q;
    }


    /**
     * Implements the actual in-order traversal technique where the traversed
     * nodes are queued in a Queue.
     * 
     * @param x
     *            starting node
     * @param q
     *            a queue where the traversed nodes are enqueued.
     */
    private void inorder(Node x, Queue<Key> q) {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }


    /**
     * Returns a iterable object that can be used to traverse the tree in
     * pre-order fashion.
     * 
     * @return iterable object (a queue) that allows us to traverse the tree in
     *         pre-order fashion.
     */
    public Iterable<Key> keysPreOrder() {
        Queue<Key> q = new Queue<>();
        preorder(root, q);
        return q;
    }


    /**
     * Implements the actual pre-order traversal technique where the traversed
     * nodes are queued in a Queue.
     * 
     * @param x
     *            starting node
     * @param q
     *            a queue where the traversed nodes are enqueued.
     */
    private void preorder(Node x, Queue<Key> q) {
        if (x == null) return;
        q.enqueue(x.key);
        preorder(x.left, q);
        preorder(x.right, q);
    }


    /**
     * Returns a iterable object that can be used to traverse the tree in
     * post-order fashion.
     * 
     * @return iterable object (a queue) that allows us to traverse the tree in
     *         post-order fashion.
     */
    public Iterable<Key> keysPostOrder() {
        Queue<Key> q = new Queue<>();
        postorder(root, q);
        return q;
    }


    /**
     * Implements the actual post-order traversal technique where the traversed
     * nodes are queued in a Queue.
     * 
     * @param x
     *            starting node
     * @param q
     *            a queue where the traversed nodes are enqueued.
     */
    private void postorder(Node x, Queue<Key> q) {
        if (x == null) return;
        postorder(x.left, q);
        postorder(x.right, q);
        q.enqueue(x.key);
    }


    /**
     * Deletes the node with the lowest possible key in the tree.
     */
    public void deleteMin() {
        root = deleteMin(root);
    }


    /**
     * Deletes the node bearing the key that is considered as the lowest in the
     * tree.
     * 
     * @param x
     *            starting node
     * @return pointer to the tree after deleting the key with minimum value
     */
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;

    }


    /**
     * Deletes the node with the highest possible key in the tree.
     */

    public void deleteMax() {
        root = deleteMax(root);
    }


    /**
     * Deletes the node bearing the key that is considered as the highest in the
     * tree.
     * 
     * @param x
     *            starting node
     * @return pointer to the tree after deleting the key with maximum value
     */

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }


    /**
     * Deletes a given key from the tree utilizing the Hibbard Deletion
     * technique.
     * 
     * @param key
     *            a node whose key matches the given key would be deleted from
     *            the tree
     */
    public void delete(Key key) {
        root = delete(root, key);
    }


    /**
     * Overloaded delete method that deletes a node in the tree whose key
     * matches the given the key.
     * 
     * @param x
     *            starting node
     * @param key
     *            a node with the matching key would be deleted from the tree
     * @return link to the root node after deleting a node whose key matches the
     *         given key
     */
    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            // key found
            if (x.right == null) return x.left;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }


    /**
     * Returns the node whose key is the considered as the least within the
     * given tree.
     * 
     * @param x
     *            starting node
     * @return link to the node whose key is considered as the least in the
     *         given tree.
     */
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }
}
