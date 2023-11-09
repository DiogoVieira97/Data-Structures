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
 * @param <T>
 */
public interface SmackADT<T> {

    /**
     * looks at the bottom element without removing it
     * @return the bottom element
     * @throws main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException
     */
    T spy() throws EmptyCollectionException;

    /**
     * removes the lowest element in the stack
     * @return the removed element
     * @throws main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException
     */
    T smack() throws EmptyCollectionException;
}
