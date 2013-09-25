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
