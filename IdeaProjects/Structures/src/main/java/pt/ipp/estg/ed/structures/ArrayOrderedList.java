/*
 * To change this license header, choose License Headers in Project Properties.
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
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList() {
        super();
    }

    /**
     * adds an element to the list
     */
    @Override
    public void add(T element) {
        if (size() == list.length)
            expandCapacity();

        Comparable<T> temp = (Comparable<T>)element;

        int scan = 0;
        while (scan < rear && temp.compareTo(list[scan]) > 0)
            scan++;

        for (int scan2=rear; scan2 > scan; scan2--)
            list[scan2] = list[scan2-1];

        list[scan] = element;
        rear++;
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
