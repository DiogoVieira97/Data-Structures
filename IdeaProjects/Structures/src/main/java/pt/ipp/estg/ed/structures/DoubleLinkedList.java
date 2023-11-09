/*
 * To change this license head, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.ElementNotFoundException;
import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.ListADT;

import java.util.Iterator;

/**
 * LISTA DUPLAMENTE LIGADA COM NOS SENTINELA
 *
 * @param <T>
 * @author xavie
 */
public abstract class DoubleLinkedList<T> implements ListADT<T> {

    protected DoubleNode<T> head;
    protected DoubleNode<T> tail;
    protected int count;
    protected int modCount;

    /**
     * Creates a linkedList with sentinel nodes
     */
    public DoubleLinkedList() {
        this.head = new DoubleNode<>(null);
        this.tail = new DoubleNode<>(null);
        this.head.setNext(this.tail);
        this.count = 0;
        this.modCount++;
    }

    /**
     * iterates through the list
     *
     * @param <T>
     */
    private static class BasicIterator<T> implements Iterator<T> {

        private DoubleNode<T> current;

        public BasicIterator(DoubleLinkedList<T> list) {
            current = list.head.getNext();
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            T data = current.getElement();
            current = current.getNext();
            return data;
        }
    }

    /**
     * Removes the head node in the list
     *
     * @return the element from the removed node
     * @throws EmptyCollectionException
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty.");
        }
        return remove(first());
    }

    /**
     * Removes the tail node in the list
     *
     * @return the element of the removed node
     * @throws EmptyCollectionException
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty.");
        }
        return remove(last());
    }

    /**
     * Removes the head instance of the specified element from this list and
     * returns a reference to it.Throws an EmptyListException if the list is
     * empty.Throws a NoSuchElementException if the specified element is not
     * found in the list.
     *
     * @return the removed element
     * @throws EmptyCollectionException
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty.");
        }
        DoubleNode<T> current;
        boolean found = false;
        for (current = head.getNext(); current != null && !found; current = current.getNext()) {
            if (current.getElement().equals(element)) {
                found = true;
                if (current.getNext() != null) {
                    current.getNext().setPrevious(current.getPrevious());
                }
                if (current.getPrevious() != null) {
                    current.getPrevious().setNext(current.getNext());
                }
                count--;
                modCount++;
                return current.getElement();
            }
        }
        if (!found) {
            throw new ElementNotFoundException("Element was not found.");
        }
        return null;
    }

    /**
     * Returns the head element of the list
     *
     * @return the head element of the list
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is Empty.");
        }
        return head.getNext().getElement();
    }

    /**
     * Returns the tail element of the list
     *
     * @return the tail element of the list
     * @throws EmptyCollectionException
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty.");
        }
        return tail.getPrevious().getElement();
    }

    /**
     * Checks if the list contains a certain element
     *
     * @param target the element to find
     * @return true if found false otherwise
     */
    @Override
    public boolean contains(T target) {
        DoubleNode<T> current;
        for (current = head.getNext(); current != null; current = current.getNext()) {
            if (current.getElement().equals(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the list is empty
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Returns the size of the list
     *
     * @return the count
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Iterates through the list
     *
     * @return the basic iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<>(this);
    }

    /**
     * Turns the list into a string
     *
     * @return the string
     */
    @Override
    public String toString() {
        DoubleNode<T> current;
        String s = "DoubleLinkedList:\n";
        for (current = head.getNext(); current != null; current = current.getNext()) {
            s += current.getElement() + "\n";
        }
        return s;
    }

    /**
     * reverses the order of elements
     */
    public void reverse() {
        DoubleNode<T> current = head.getNext();
        DoubleNode<T> temp = null;

        while (current != null && current != tail) {
            temp = current.getPrevious();
            current.setPrevious(current.getNext());
            current.setNext(temp);

            current = current.getPrevious();
        }

        // Update tail after reversing
        tail.setPrevious(temp);
        if (temp != null) {
            temp.setNext(tail);
        }

        // Set the next of the original head to null
        if (head.getNext() != null) {
            head.getNext().setNext(null);
        }

        if (temp != null && temp.getPrevious() != null) {
            head.setNext(temp.getPrevious());
            temp.getPrevious().setPrevious(head.getNext());
        }
    }
}
