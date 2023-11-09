/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.SmackADT;

/**
 *
 * @author xavie
 * @param <T>
 */
public class SmackStack<T> extends ArrayStack<T> implements SmackADT<T> {

    /**
     * looks at the bottom element without removing it
     *
     * @return the bottom element
     * @throws pt.ipp.estg.pp.exceptions.EmptyCollectionException
     */
    @Override
    public T spy() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack is empty");
        }
        reverseStack();
        T top = peek();
        reverseStack();
        return top;
    }

    /**
     * removes the lowest element in the stack
     *
     * @return the removed element
     * @throws pt.ipp.estg.pp.exceptions.EmptyCollectionException
     */
    @Override
    public T smack() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack is empty");
        }
        return popBottom();
    }

    /**
     * reverses the stack
     *
     * @throws EmptyCollectionException throws the exception if the stack is
     * empty
     */
    private void reverseStack() throws EmptyCollectionException {
        T[] elements = (T[]) (new Object[size()]);
        int i = 0;
        while (!isEmpty()) {
            elements[i] = pop(); //store elements in an array by the order they are popped
            i++;
        }
        for (int j = 0; j < i; j++) {
            push(elements[j]); //store the elements again in the stack in reversed order
        }
    }

    /**
     * removes and returns the bottom of the stack
     *
     * @return the removed element
     * @throws EmptyCollectionException throws the exception if the stack is
     * empty
     */
    private T popBottom() throws EmptyCollectionException {
        reverseStack();
        T bottom = pop();
        reverseStack();
        return bottom;
    }
}
