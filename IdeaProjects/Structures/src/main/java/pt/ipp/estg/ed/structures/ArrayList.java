/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.ListADT;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 * @author xavie
 */
public abstract class ArrayList<T> implements ListADT<T> {

    private final int SIZE = 100;
    protected int front;
    protected int modCount;
    protected int rear;

    protected T[] list;

    public ArrayList() {
        this.front = 0;
        this.modCount = 0;
        this.rear = 0;
        this.list = (T[]) (new Object[SIZE]);
    }

    //TESTADO
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista estÃ¡ vazia");
        }
        T result = list[front];
        list[front] = null;
        for (int i = 0; i < this.rear; i++) {
            this.list[i] = this.list[i + 1];
        }
        this.rear--;
        this.modCount++;
        return result;
    }

    //TESTADO
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista estÃ¡ vazia");
        }
        this.rear--;
        T result = list[rear];
        list[rear] = null;
        this.modCount++;

        return result;
    }

    //TESTADO
    @Override
    public T remove(T element) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty");
        }
        if (!contains(element)) {
            throw new EmptyCollectionException("Element doesnt exist");
        }
        int position = 0;
        for (int i = 0; i < this.rear; i++) {
            if (element.equals(list[i])) {
                position = i;
            }
        }

        T result = list[position];
        list[position] = null;
        for (int j = position; j < this.rear; j++) {
            this.list[j] = this.list[j + 1];
        }
        this.rear--;
        this.modCount++;
        return result;
    }

    //TESTADO
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty");
        }
        return list[front];
    }

    //TESTADO
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty");
        }
        return list[rear - 1];
    }

    //TESTADO
    public T get(int i) {
        if (i >= this.rear || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + i);
        }
        return list[i];
    }

    //TESTADO
    @Override
    public boolean contains(T target) {
        boolean found = false;
        int i = 0;
        while (i < this.rear && !found) {
            if (target.equals(list[i])) {
                found = true;
            }
            i++;
        }
        return found;
    }

    //TESTADO
    @Override
    public boolean isEmpty() {
        return rear == 0;
    }

    //TESTADO
    @Override
    public int size() {
        return this.rear;
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<>();
    }

    //TESTADO
    @Override
    public String toString() {
        String s = "ArrayList:\n";
        for (int i = 0; i < this.rear; i++) {
            s += list[i] + "\n";
        }
        return s;
    }

    private class BasicIterator<T> implements Iterator<T> {

        private final int size;
        private final T[] items;
        private int current;
        private int expectedModCount;

        public BasicIterator() {
            this.items = (T[]) ArrayList.this.list;
            this.size = ArrayList.this.rear;
            this.current = 0;
            this.expectedModCount = ArrayList.this.modCount;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("ConcorrÃªncia");
            }
            return (this.items[this.current] != null);
        }

        @Override
        public T next() {
            T temp = items[this.current];
            if (hasNext()) {
                this.current++;
            }
            return temp;
        }

        @Override
        public void remove() {
            try {
                ArrayList.this.remove(list[--this.current]);
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(ArrayList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
