/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import java.util.Objects;

/**
 * A simple implementation of a singly linked list.
 *
 * This class represents a singly linked list, where each element is stored in a LinearNode and linked to the next element.
 * It provides methods to add and remove elements from the list and supports toString, equals, and hashCode methods for
 * convenient manipulation and comparison of linked lists.
 *
 * @param <T> The type of elements that the linked list stores.
 * @author xavie
 */
public class LinkedList<T> {

    private LinearNode<T> head;
    private int size;

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Adds an element to the beginning of the linked list.
     *
     * @param element The element to be added to the linked list.
     */
    public void add(T element) {
        head = new LinearNode<>(element, head);
        size++;
    }

    /**
     * Removes the first occurrence of the specified element from the linked list.
     *
     * @param element The element to be removed from the linked list.
     * @throws NullPointerException If the specified element is null.
     */
    public void remove(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }

        if (head == null) {
            return;
        }

        if (head.getElement().equals(element)) {
            head = head.getNext();
            size--;
            return;
        }

        LinearNode<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getElement().equals(element)) {
                current.setNext(current.getNext().getNext());
                size--;
                return;
            }
            current = current.getNext();
        }
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return A string representation of the linked list in the format "[element1, element2, ...]".
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        LinearNode<T> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Compares this linked list with another object for equality.
     *
     * @param obj The object to compare with this linked list.
     * @return true if the specified object is a LinkedList and has the same elements in the same order as this linked list;
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof LinkedList)) {
            return false;
        }

        LinkedList<?> other = (LinkedList<?>) obj;
        if (size != other.size) {
            return false;
        }

        LinearNode<T> thisCurrent = head;
        LinearNode<?> otherCurrent = other.head;
        while (thisCurrent != null) {
            if (!thisCurrent.getElement().equals(otherCurrent.getElement())) {
                return false;
            }
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }

        return true;
    }

    /**
     * Returns the hash code value for this linked list.
     *
     * @return The hash code value for this linked list.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.head);
        hash = 23 * hash + this.size;
        return hash;
    }
}
