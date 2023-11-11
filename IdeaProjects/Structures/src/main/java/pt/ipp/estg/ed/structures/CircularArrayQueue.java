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
public class CircularArrayQueue<T> implements QueueADT<T> {

    private static final int DEFAULT_CAPACITY = 100;
    private T[] queue;
    private int front;
    private int rear;
    private int count;

    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularArrayQueue(int initialCapacity) {
        queue = (T[]) (new Object[initialCapacity]);
        front = 0;
        rear = initialCapacity - 1;
        count = 0;
    }

    /**
     *
     * @param element the element to be added to the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        if (size() == queue.length) {
            expandCapacity();
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = element;
        count++;
    }

    /**
     * Removes the element at the front of the queue and returns reference to it.
     * @return a reference to the removed element
     * @throws EmptyCollectionException if the queue is empty.
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue");
        }

        T result = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;

        return result;
    }

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element in this queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue");
        }

        return queue[front];
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty and false otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size of this queue
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the string representation of this queue
     */
    @Override
    public String toString() {
        String result = "";
        int scan = 0;

        while (scan < count) {
            if (queue[scan] != null) {
                result += queue[scan].toString() + "\n";
            }
            scan++;
        }

        return result;
    }

    /**
     * Expands the queue's capacity by duplicating it's length
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);

        for (int scan = 0; scan < count; scan++) {
            larger[scan] = queue[front];
            front = (front + 1) % queue.length;
        }

        front = 0;
        rear = count - 1;
        queue = larger;
    }
}
