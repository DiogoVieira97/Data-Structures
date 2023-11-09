/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

/**
 *
 * @author xavie
 * @param <T>
 */
public class CircularlyLinkedList<T> {

    private LinearNode<T> tail;
    private int count;

    public CircularlyLinkedList() {
        this.tail = null;
        this.count = 0;
    }

    /**
     *
     * @return the size of the list
     */
    public int size() {
        return count;
    }

    /**
     * checks if the list is empty
     *
     * @return true if is empty, false otherwise
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * returns (but does not remove) the first element
     *
     * @return
     */
    public T first() {
        if (isEmpty()) {
            return null;
        }
        return tail.getNext().getElement();
    }

    /**
     * returns (but does not remove) the last element
     *
     * @return null if list is empty, last element otherwise
     */
    public T last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    /**
     * rotate the first element to the back of the list
     */
    public void rotate() {
        if (tail != null) {// if empty, do nothing
            tail = tail.getNext(); // the old head becomes the new tail
        }
    }

    /**
     * adds element e to the front of the list
     *
     * @param element the element to add
     */
    public void addFirst(T element) {
        if (size() == 0) {
            tail = new LinearNode(element);
            tail.setNext(tail);// link to itself circularly
        } else {
            LinearNode<T> newest = new LinearNode<>(element);
            newest.setNext(tail.getNext());
        }
        count++;
    }

    /**
     * adds element e to the end of the list
     *
     * @param element the element to add
     */
    public void addLast(T element) {
        addFirst(element);// insert new element at front of list
        tail = tail.getNext();// now new element becomes the tail
    }

    /**
     * removes and returns the first element
     *
     * @return returns null if list is empty, the element of the head otherwise
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;  //nothing to remove
        }
        LinearNode<T> head = tail.getNext();
        if (head == tail) {
            tail = null;  // must be the only node left
        } else {
            tail.setNext(head.getNext()); // removes ”head” from the list
        }
        count--;
        return head.getElement();
    }
}
