/*
 * To change this license head, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.interfaces.OrderedListADT;

/**
 *
 * @author xavie
 * @param <T>
 */
public class DoubleLinkedOrderedList<T extends Comparable> extends DoubleLinkedList<T> implements OrderedListADT<T> {

    public DoubleLinkedOrderedList() {
        super();
    }

    /**
     * Adds a new element to the list and sets in the correct order
     *
     * @param element
     */
    @Override
    public void add(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
        //Case1: when there is no element in LinkedList
        if (isEmpty()) {
            head.setNext(newNode);
            tail.setPrevious(newNode);
            newNode.setNext(tail);
            newNode.setPrevious(head);
            count++;
            return;
        }
        //Case2: when node should be replaced at tail
        if (newNode.getElement().compareTo(tail.getPrevious().getElement()) >= 0) {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            newNode.setNext(null);
            tail = newNode;
            count++;
            return;
        }
        //Case3: when node should be replaced at head
        if (newNode.getElement().compareTo(head.getNext().getElement()) <= 0) {
            newNode.setNext(head.getNext());
            head.getNext().setPrevious(newNode);
            head.setNext(newNode); //first -> newNode
            count++;
            return;
        }
        //Case3: when newNode should be at some position other than head
        DoubleNode<T> current;
        for (current = head.getNext(); newNode.getElement().compareTo(current.getElement()) > 0; current = current.getNext()) {

        }
        newNode.setNext(current);
        newNode.setPrevious(current.getPrevious());
        current.getPrevious().setNext(newNode);
        current.setPrevious(newNode);
        count++;
    }
}
