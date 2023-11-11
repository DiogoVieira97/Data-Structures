/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.interfaces.StackADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <T>
 * @author xavie
 */
public class LinkedStack<T> implements StackADT {
    private int n;          // size of the stack
    private LinearNode<T> first;     // top of stack


    /**
     * Initializes an empty stack.
     */
    public LinkedStack() {
        first = null;
        n = 0;
    }

    /**
     * Is this stack empty?
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size() {
        return n;
    }

    /**
     * Adds one element to the top of this stack.
     *
     * @param element element to be pushed onto stack
     */
    @Override
    public void push(Object element) {
        LinearNode<T> oldfirst = first;
        first = new LinearNode<>();
        first.setElement((T) element);
        first.setNext(oldfirst);
        n++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        T item = (T) first.getElement();
        first = first.getNext();
        n--;
        return item;
    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return (T) first.getElement();
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in the stack in LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterator<T> iterator = this.iterator();
        while(iterator.hasNext()) {
            s.append(iterator.next() + "\n");
        }
        return s.toString();
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    public Iterator<T> iterator() {
        return (Iterator<T>) new BasicIterator<>();
    }

    public class BasicIterator<T> implements Iterator<T> {

        private LinearNode<T> current = (LinearNode<T>) first;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.getElement();
            current = current.getNext();
            return item;
        }
    }
}

