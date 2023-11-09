/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.UnorderedListADT;
import main.java.pt.ipp.estg.ed.exceptions.ElementNotFoundException;

/**
 *
 * @author xavie
 * @param <T>
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList() {
        super();
    }

    @Override
    public void addToFront(T element) {
        if (size() == list.length) {
            expandCapacity();
        }
        for (int scan = rear; scan > 0; scan--) {
            list[scan] = list[scan - 1];
        }
        list[0] = element;
        rear++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        if (size() == list.length) {
            expandCapacity();
        }
        list[rear] = element;
        rear++;
        modCount++;
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        if (size() == list.length) {
            expandCapacity();
        }
        int scan = 0;
        while (scan < rear && !target.equals(list[scan])) {
            scan++;
        }
        if (scan == rear) {
            throw new ElementNotFoundException("Element was not found.");
        }
        scan++;
        for (int scan2 = rear; scan2 > scan; scan2--) {
            list[scan2] = list[scan2 - 1];
        }
        list[scan] = element;
        rear++;
        modCount++;

    }

    /**
     * expands lists capacity
     */
    protected void expandCapacity() {
        T[] newList = (T[]) (new Object[list.length * 2]);
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }
}
