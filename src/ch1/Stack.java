package ch1;

import java.util.Iterator;

/**
 * An implementation of the Pushdown Stack using LinkedList.
 * 
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first; // Top of the stack. Most recently added node.
    private int  N;    // Number of items in the stack.

    private class Node {
        // Nested class to define Nodes.
        Item item;
        Node next;
    }


    public boolean isEmpty() {
        return first == null; // Or N == 0
    }


    public int size() {
        return N;
    }


    public void push(Item item) {
        // Add item to top of the stack.
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }


    public Item pop() {
        // Remove item from top of the stack.
        Item item = first.item;
        first = first.next; // garbage collection will take care of redeeming
                            // the lost node.
        N--;
        return item;
    }


    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        private Node current = first;


        public boolean hasNext() {
            return current != null;
        }


        public void remove() {
        }


        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
