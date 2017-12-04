package edu.algo.ch3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BST <Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public Value get(Key key) {
        Node temp = root;
        while (temp != null) {
            int cmp = key.compareTo(temp.key);
            if (cmp < 0)
                temp = temp.left;
            else if (cmp > 0)
                temp = temp.right;
            else return temp.value;

        }
        return null;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null)
            return new Node(key, value);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;
        return x;
    }

    public Iterable<Key> inorder() {
        List<Key> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node x, List<Key> result) {
        if (x == null) return;
        inorder(x.left, result);
        result.add(x.key);
        inorder(x.right, result);
    }

    public Iterable<Key> bfs() {
        List<Key> result = new ArrayList<>();
        bfs(root, result);
        return result;
    }

    private void bfs(Node x, List<Key> result) {
        if (x == null) return;
        Queue<Node> q = new ArrayDeque<>();
        q.add(x);
        while (!q.isEmpty()) {
            Node t = q.remove();
            result.add(t.key);
            if (t.left != null) q.add(t.left);
            if (t.right != null) q.add(t.right);
        }
    }

    public Iterable<Key> dfs() {
        // dfs is practially pre-order traversal in a tree.
        List<Key> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(Node x, List<Key> result) {
        if (x == null) return;
        result.add(x.key);
        dfs(x.left, result);
        dfs(x.right, result);
    }

    public Iterable<Key> dfsWithStack() {
        List<Key> result = new ArrayList<>();
        dfsWithStack(root, result);
        return result;
    }

    private void dfsWithStack(Node x, List<Key> result) {
        if (x == null) return;
        Stack<Node> s = new Stack<>();
        s.add(x);
        while (!s.empty()) {
            Node t = s.pop();
            result.add(t.key);
            if (t.right != null) s.add(t.right);
            if (t.left != null) s.add(t.left);
        }
    }

    public Key min() {
        Node x = root;
        if (x == null) return null;
        while (x.left != null) x = x.left;
        return x.key;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    
    // T.Hibbard (1962) deletion
    public void delete(Key key) {
        if (root == null) return;
        root = delete(root, key);
    }

    private Node min(Node x) {
        if (x == null) return null;
        while (x.left != null) x = x.left;
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            // we found the key
            Node t = x;
            if (t.right == null) return t.left; // beauty of java - just forget the key to delete it.
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/ch3/bst.txt"));
        String[] input = br.readLine().trim().split(" ");
        br.close();
        BST<String, Integer> bst = new BST<>();
        for (String s : input) {
            bst.put(s, (int) Math.random() * 100);
        }

        for (String s: bst.inorder())
            System.out.print(s + " ");
        System.out.println();

        for (String s: bst.bfs())
            System.out.print(s + " ");
        System.out.println();

        for (String s: bst.dfs())
            System.out.print(s + " ");
        System.out.println();

        for (String s: bst.dfsWithStack())
            System.out.print(s + " ");
        System.out.println();

//        System.out.println("Min: " + bst.min());
//        bst.deleteMin();
//        for (String s: bst.bfs())
//            System.out.print(s + " ");
//        System.out.println();
//
//        System.out.println("Min: " + bst.min());
//        bst.deleteMin();
//        for (String s: bst.bfs())
//            System.out.print(s + " ");
//        System.out.println();
//
//        System.out.println("Min: " + bst.min());
//        bst.deleteMin();
//        for (String s: bst.bfs())
//            System.out.print(s + " ");
//        System.out.println();

        System.out.println("Deleting 'E':");
        bst.delete("E");
        for (String s: bst.dfsWithStack())
            System.out.print(s + " ");
        System.out.println();

    }
}
