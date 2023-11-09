/*
 * To change this license head, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import java.util.NoSuchElementException;
import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.UnorderedListADT;

/**
 *
 * @author xavie
 */
public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
        if (isEmpty()) {
            head.setNext(newNode);
            tail.setPrevious(newNode);
        } else {
            newNode.setNext(head.getNext());
            head.getNext().setPrevious(newNode);
            head.setNext(newNode);
        }
        count++;
    }

    @Override
    public void addToRear(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
        if (isEmpty()) {
            head.setNext(newNode);
            tail.setPrevious(newNode);
        } else {
            newNode.setPrevious(tail.getPrevious());
            tail.getPrevious().setNext(newNode);
            tail.setNext(newNode);
        }
        count++;
    }

    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException {
        DoubleNode<T> current = head.getNext();
        while (current != null && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        if (current == null) {
            throw new NoSuchElementException();
        }
        DoubleNode<T> newNode = new DoubleNode<>(element);
        newNode.setNext(current.getNext());
        newNode.setPrevious(current);
        current.setNext(newNode);
        if (current == tail.getPrevious()) {
            tail.setPrevious(newNode);
        } else {
            newNode.getNext().setPrevious(newNode);
        }
        count++;
    }

}
