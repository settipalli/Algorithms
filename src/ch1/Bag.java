package ch1;

import java.util.Iterator;

/**
 * Implements the Bag datastructure.
 * 
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first; // first node in list
    private int  N;    // size of the bag

    private class Node {
        Item item;
        Node next;
    }


    public void add(Item item) {
        // same as pus() in Stack
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }


    public boolean isEmpty() {
        return first == null;
    }


    public int size() {
        return N;
    }


    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<Item> {
        private Node current = first;


        @Override
        public boolean hasNext() {
            return current != null;
        }


        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }


        @Override
        public void remove() {
        }

    }
}
