/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import java.util.Iterator;
import java.util.Objects;
import main.java.pt.ipp.estg.ed.exceptions.ElementNotFoundException;
import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.ListADT;

/**
 * SINGLY LINKED LIST SEM NOS SENTINELA
 *
 * @author xavie
 * @param <T>
 */
public class SinglyLinkedList<T> implements ListADT<T> {

    private LinearNode<T> head;
    private LinearNode<T> tail;
    private int count;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    //TESTADO
    /**
     * Removes the first node in the list
     *
     * @return the element from the removed node
     * @throws EmptyCollectionException
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is Empty!");
            /*testado*/
        }
        if (head == null) {
            tail = null;
        }
        LinearNode<T> result = head;
        head = head.getNext();
        count--;
        return result.getElement();
    }

    //TESTADO
    /**
     * Removes the last node in the list
     *
     * @return the element of the removed node
     * @throws EmptyCollectionException
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty!");
        }
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            return null;
        }
        LinearNode<T> secondLast = head;
        T element = tail.getElement();
        while (secondLast.getNext().getNext() != null) {
            secondLast = secondLast.getNext();
        }
        secondLast.setNext(null);
        count--;
        return element;
    }

    //TESTADO
    /**
     * Removes the first instance of the specified element from this list and
     * returns a reference to it.Throws an EmptyListException if the list is
     * empty.Throws a NoSuchElementException if the specified element is not
     * found in the list.
     *
     * @param targetElement the element to remove
     * @return the removed element
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     */
    @Override
    public T remove(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (size() == 1) {
            LinearNode<T> current = head;
            head = tail = null;
            count--;
            return current.getElement();
        }
        if (!isEmpty()) {
            boolean found = false;
            if (targetElement.equals(first())) {
                found = true;
                return removeFirst();
            }
            if (targetElement.equals(last())) {
                found = true;
                return removeLast();
            }
            LinearNode<T> current = head;
            while (current != null && !found) {
                if (current.getElement().equals(targetElement)) {
                    found = true;
                    current.setNext(current.getNext().getNext());
                    return current.getElement();
                }
                current = current.getNext();
            }
            if (!found) {
                throw new ElementNotFoundException("Element was not found.");
            }
            count--;
        } else {
            throw new EmptyCollectionException("List is empty");
        }
        return null;
    }

    //TESTADO
    /**
     * Turns the list into a string
     *
     * @return the string
     */
    @Override
    public String toString() {
        LinearNode<T> current = head;
        String s = "LinkedList:\n";
        while (current != null) {
            s += current.getElement() + "\n";
            current = current.getNext();
        }
        return s;
    }

    //TESTADO
    /**
     * Returns the first element of the list
     *
     * @return the first element of the list
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty");
        }
        /*Testado*/
        if (head == null) {
            return null;
        }
        return head.getElement();
    }

    //TESTADO
    /**
     * Returns the last element of the list
     *
     * @return the last element of the list
     * @throws EmptyCollectionException
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty");
        }
        /*Testado*/
        if (tail == null) {
            return null;
        }
        return tail.getElement();
    }

    //TESTADO
    /**
     * Checks if the list contains a certain element
     *
     * @param target the element to find
     * @return true if found false otherwise
     */
    @Override
    public boolean contains(T target) {
        LinearNode<T> current = head;
        while (current != null) {
            if (current.getElement().equals(target)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    //TESTADO
    /**
     * Checks if the list is empty
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    //TESTADO
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

    //TESTADO
    /**
     * iterates through the list
     *
     * @param <T>
     */
    private class BasicIterator<T> implements Iterator<T> {

        private LinearNode<T> current;

        public BasicIterator(SinglyLinkedList<T> list) {
            current = list.head;
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
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.head);
        hash = 97 * hash + Objects.hashCode(this.tail);
        hash = 97 * hash + this.count;
        return hash;
    }

    /**
     * checks if an object is equal to this
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SinglyLinkedList<?> other = (SinglyLinkedList<?>) obj;
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.head, other.head)) {
            return false;
        }
        if (!Objects.equals(this.tail, other.tail)) {
            return false;
        }
        return true;
    }
}
