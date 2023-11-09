/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import java.util.Iterator;
import main.java.pt.ipp.estg.ed.exceptions.ElementNotFoundException;
import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.BinaryTreeADT;

/**
 *
 * @author xavie
 * @param <T>
 */
public class LinkedBinaryTree<T extends Comparable<? super T>> implements BinaryTreeADT<T> {

    protected int size;
    protected BinaryTreeNode<T> root;

    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    public LinkedBinaryTree(T element) {
        root = new BinaryTreeNode<>(element);
        size = 1;
    }

    @Override
    public T getRoot() throws EmptyCollectionException {
        return root.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T targetElement) {
        return find(targetElement, root) != null;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> node = find(targetElement, root);
        if (node == null) {
            return null;
        }
        return node.getElement();
    }

    private BinaryTreeNode<T> find(T target, BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (target.equals(node.getElement())) {
            return node;
        }
        BinaryTreeNode<T> left = find(target, node.getLeft());
        if (left != null) {
            return left;
        }
        return find(target, node.getRight());
    }

    public void remove(T target) {
        remove(target, root);
        size--;
    }

    private BinaryTreeNode<T> remove(T target, BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (target.equals(node.getElement())) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                T successorValue = getSuccessorValue(node);
                node.setElement(successorValue);
                node.setRight(remove(successorValue, node.getRight()));
                return node;
            }
        } else if (target.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(target, node.getLeft()));
            return node;
        } else {
            node.setRight(remove(target, node.getRight()));
            return node;
        }
    }

    private T getSuccessorValue(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> current = node.getRight();
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getElement();
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> list = new ArrayUnorderedList<T>();
        inOrder(root, list);
        return list.iterator();
    }

    private void inOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.addToRear(node.getElement());
        inOrder(node.right, list);
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> list = new ArrayUnorderedList<>();
        preOrder(root, list);
        return list.iterator();
    }

    private void preOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> list) {
        if (node == null) {
            return;
        }
        list.addToRear(node.getElement());
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> list = new ArrayUnorderedList<>();
        postOrder(root, list);
        return list.iterator();
    }

    private void postOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> list) {
        if (node == null) {
            return;
        }
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.addToRear(node.getElement());
    }

    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        levelorder(this.root, tempList);

        return tempList.iterator();
    }

    protected void levelorder(BinaryTreeNode<T> root, ArrayUnorderedList<T> results) throws EmptyCollectionException {
        LinkedQueue<BinaryTreeNode<T>> nodes = new LinkedQueue<>();
        BinaryTreeNode<T> node = null;
        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            node = nodes.dequeue();
            if (node.element != null) {
                results.addToRear(node.element);

            } else {
                results.addToRear(null);
            }

            if (node.getLeft() != null) {
                nodes.enqueue(node.getLeft());
            }
            if (node.getRight() != null) {
                nodes.enqueue(node.getRight());
            }
        }
    }

    @Override
    public String toString() {
        String s = "Iterator LevelOrder:";
        Iterator<T> itr = null;
        try {
            itr = iteratorLevelOrder();
        } catch (EmptyCollectionException e) {
            throw new RuntimeException(e);
        }
        s += " | ";
        while (itr.hasNext()) {
            s += itr.next() + " | ";
        }
        return s;
    }

}
