/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.QueueADT;

/**
 *
 * @author xavie
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private LinearNode<T> head;
    private LinearNode<T> tail;
    private int size;

    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (head == null) {
            throw new EmptyCollectionException("A queue está vazia");
        }
        T data = head.getElement();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (head == null) {
            throw new EmptyCollectionException("A queue está vazia");
        }
        return head.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

}
