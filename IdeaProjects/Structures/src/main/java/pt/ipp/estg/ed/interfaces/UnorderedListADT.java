/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.interfaces;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;

/**
 *
 * @author xavie
 */
public interface UnorderedListADT<T> {

    void addToFront(T element);

    void addToRear(T element);

    void addAfter(T element, T target) throws EmptyCollectionException;
}
