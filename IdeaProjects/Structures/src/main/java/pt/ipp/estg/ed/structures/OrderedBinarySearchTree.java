/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import java.util.Iterator;
import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.OrderedListADT;

/**
 *
 * @author xavie
 * @param <T>
 */
public class OrderedBinarySearchTree<T extends Comparable<? super T>> extends LinkedBinarySearchTree<T> implements OrderedListADT<T> {

    public OrderedBinarySearchTree() {
        super();
    }

    @Override
    public void add(T element) {
        insert(element);
        iteratorInOrder();
    }

    public void insert(T element) {
        BinaryTreeNode<T> current = root;
        BinaryTreeNode<T> parent = null;
        boolean found = false;

        while (!found && current != null) {
            int comparison = element.compareTo(current.getElement());
            if (comparison < 0) {
                parent = current;
                current = current.getLeft();
            } else if (comparison > 0) {
                parent = current;
                current = current.getRight();
            } else {
                found = true;
            }
        }

        if (!found) {
            current = new BinaryTreeNode<>(element);
            if (parent == null) {
                root = current;
            } else {
                if (element.compareTo(parent.getElement()) < 0) {
                    parent.setLeft(current);
                } else {
                    parent.setRight(current);
                }
            }
        }
    }

    public T removeFirst() throws EmptyCollectionException {
        T first = removeMin();
        return first;
    }

    public T removeLast() throws EmptyCollectionException {
        T last = removeMax();
        return last;
    }

    public T first() throws EmptyCollectionException {
        T min = findMin();
        return min;
    }

    public T last() throws EmptyCollectionException {
        T max = findMax();
        return max;
    }

    public Iterator<T> iterator() {
        return iteratorInOrder();
    }

}
