/*******************************************************************************
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
 ******************************************************************************/

package ch1;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    /*
     * At the moment, Java Generics framework does not allow an array of Generic
     * type. Hence casting is unavoidable.
     */
    private Item[] a = (Item[]) new Object[1]; // stack items.
    private int    N = 0;                     // number of items.


    /**
     * Allows a user to verify if the stack is empty.
     * 
     * @return true if the stack is empty else false
     */
    public boolean isEmpty() {
        return N == 0;
    }


    /**
     * Allows a user to obtain the current size of the stack.
     * 
     * @return int size of the stack
     */
    public int size() {
        return N;
    }


    /**
     * Resizes the array by allocating a new array, deep copying the contents of
     * the current array to the new array, reassigning the reference to the
     * current array pointer and allowing the garbage collector to eventually
     * purge the memory allocated to the old array.
     * 
     * @param max
     *            the new size to which the array has to be resized
     */
    private void resize(int max) {
        // Move the stack to the new array of size max.
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
        // The memory occupied by the old array would be garbage collected.
    }


    /**
     * Adds an item to the top of the stack and also resizes the stack if the
     * length of the array representing the stack has reached the current limit.
     * 
     * @param item
     *            the value to be pushed to the top of the stack
     */
    public void push(Item item) {
        // Add item to the top of the stack.
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }


    /**
     * Returns the element on the top of the stack and marks the memory
     * allocated to that element in the array as 'null' so that the garbage
     * collector can redeem the memory.
     * 
     * Also resizes the array representing the stack to half if the total number
     * of elements in it are 1/4th its current capacity.
     * 
     * @return the element on the top of the stack
     */
    public Item pop() {
        // Remove item from top of the stack.
        Item item = a[--N];
        a[N] = null; // Avoid loitering - let the garbage collector redeem the
                     // memory!
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * Allow for-each style of traversal of the stack. Every class that inherits
     * the Iterator class has to implement hasNext(), next() and remove()
     * methods.
     * 
     * The remove() method is an empty method because we do not want to provide
     * a mechanism for the user to remove elements using iterator. We expect
     * them to use the right method i.e. pop() to remove elements from the
     * stack.
     */
    private class ReverseArrayIterator implements Iterator<Item> {
        // Support LIFO iteration.
        private int i = N;


        /**
         * Allows an iterator to verify if it has to continue iteration by
         * checking if there are additional elements in the stack.
         * 
         * @return true if there are additional elements in the stack else
         *         return false
         */
        public boolean hasNext() {
            return i > 0;
        }


        /**
         * Allows the iterator to obtain the next element in the array following
         * the LIFO principle so as to adhere to Stack specifications. Note that
         * the element is not poped out of stack as part of the iteration
         * sequence.
         * 
         * @return the next element in the array following LIFO principle.
         */
        public Item next() {
            return a[--i]; // Remove in LIFO order!
        }


        /**
         * Is a blank method to make sure that users are not allowed to modify
         * the contents of the object using an iterator.
         */
        public void remove() {
        }
    }

}
