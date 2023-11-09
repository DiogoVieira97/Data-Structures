/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

/**
 *
 * @author xavie
 * @param <T>
 */
public class DoubleNode<T> {

    private DoubleNode<T> next;
    private T element;
    private DoubleNode<T> previous;

    public DoubleNode() {

    }

    public DoubleNode(T element) {
        this.next = null;
        this.element = element;
        this.previous = null;
    }
    
    public DoubleNode(DoubleNode<T> previous, T element, DoubleNode<T> next) {
        this.previous = previous;
        this.element = element;
        this.next = next;
    }
    
    public DoubleNode<T> getNext() {
        return next;
    }
    
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
    
    public DoubleNode<T> getPrevious() {
       return previous; 
    }
    
    public void setPrevious(DoubleNode<T> node) {
        this.previous = node;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
    
    @Override
    public String toString() {
        return " Double Node: " + element;
    }

}
