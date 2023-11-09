/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.StackADT;

/**
 *
 * @author xavie
 * @param <T>
 */
public class ArrayStack<T> implements StackADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    private int top;
    private transient T[] stack;

    /**
     * Creates an empty stack using the default capacity.
     */
    public ArrayStack() {
        top = 0;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Adds the specified element to the top of the stack, expanding the
     * capacity of the stack array if necessary.
     *
     * @param element the element to push
     */
    @Override
    public void push(T element) {
        if (size() == stack.length) {
            expandCapacity();
        }
        stack[top] = element;
        top++;
    }

    /**
     * Removes the element at the top of the stack and returns a reference to
     * it.
     *
     * @return the top element
     * @throws EmptyCollectionException Throws an EmptyStackException if the
     * stack is empty
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack is empty.");
        }
        top--;
        T result = stack[top];
        stack[top] = null;
        return result;
    }

    /**
     * Returns a reference to the element at the top of the stack. The element
     * is not removed from the stack.
     *
     * @return the top element
     * @throws EmptyCollectionException Throws an EmptyStackException if the stack is empty
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack is empty.");
        }
        return stack[top - 1];
    }

    /**
     * Checks if the stack is empty
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (top == 0);
    }

    /**
     * 
     * @return the number of elements in the stack.
     */
    @Override
    public int size() {
        return top;
    }

    /**
     * expands the capacity of the stack
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[stack.length * 2]);
        for (int index = 0; index < stack.length; index++) {
            larger[index] = stack[index];
        }
        stack = larger;
    }
    
    @Override
    public String toString() {
        String str = "Stack: \n";
        for(int i = 0; i < size(); i++) {
            str += stack[i] + "\n";
        }
        return str;
    }
}
