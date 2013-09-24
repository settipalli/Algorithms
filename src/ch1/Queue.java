package ch1;

import java.util.Iterator;

/**
 * Implements a FIFO Queue
 */
public class Queue<Item> implements Iterable<Item> {
    private Node first; // link to the least recently added node.
    private Node last; // link to the most recently added node.
    private int  N;    // number of items in the queue.

    private class Node {
        Item item;
        Node next;
    }


    public boolean isEmpty() {
        return first == null;
    }


    public int size() {
        return N;
    }


    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        N++;
    }


    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        Node current = first;


        @Override
        public boolean hasNext() {
            return current == null;
        }


        @Override
        public Item next() {
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }


        @Override
        public void remove() {
        }

    }
}
