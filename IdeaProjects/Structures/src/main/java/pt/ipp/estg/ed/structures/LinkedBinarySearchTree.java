/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.ElementNotFoundException;
import main.java.pt.ipp.estg.ed.interfaces.BinarySearchTreeADT;

/**
 *
 * @author xavie
 * @param <T>
 */
public class LinkedBinarySearchTree<T extends Comparable<? super T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    public LinkedBinarySearchTree() {
        super();
    }

    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adds the specified object to the binary search tree in the appropriate
     * position according to its natural order. Note that equal elements are
     * added to the right.
     *
     * @param element the element to be added to the binary search tree
     */
    @Override
    public void addElement(T element) {
        BinaryTreeNode<T> current = root;
        BinaryTreeNode<T> parent = null;
        while (current != null) {
            parent = current;
            if (element.compareTo(current.getElement()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        BinaryTreeNode<T> newNode = new BinaryTreeNode<>(element);
        if (parent == null) {
            root = newNode;
        } else if (element.compareTo(parent.getElement()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
    }

    /**
     * Removes the first element that matches the specified target element from
     * the binary search tree and returns a reference to it. Throws a
     * ElementNotFoundException if the specified target element is not found in
     * the binary search tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @return T
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException {
        T result = null;
        if (root != null) {
            if (root.getElement().equals(targetElement)) {
                result = root.getElement();
                root = replacement(root);
            } else {
                BinaryTreeNode<T> current = root;
                boolean found = false;
                while (!found && current != null) {
                    if (targetElement.compareTo(current.getElement()) < 0) {
                        current = current.getLeft();
                    } else if (targetElement.compareTo(current.getElement()) > 0) {
                        current = current.getRight();
                    } else {
                        found = true;
                        result = current.getElement();
                        current.setElement(replacement(current).getElement());
                    }
                }
            }
        }
        return result;
    }

    /**
     * Returns a reference to a node that will replace the one specified for
     * removal. In the case where the removed node has two children, the inorder
     * successor is used as its replacement.
     *
     * @param node the node to be removed
     * @return a reference to the replacing node
     */
    private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;
        if ((node.getLeft() == null) && (node.getRight() == null)) {
            result = null;
        } else if ((node.getLeft() != null) && (node.getRight() == null)) {
            result = node.getLeft();
        } else if ((node.getLeft() == null) && (node.getRight() != null)) {
            result = node.getRight();
        } else {
            BinaryTreeNode<T> current = node.getRight();
            BinaryTreeNode<T> parent = node;
            while (current.getLeft() != null) {
                parent = current;
                current = current.getLeft();
            }
            if (node.getRight() != current) {
                parent.setLeft(current.getRight());
                current.setRight(node.getRight());
            }
            current.setLeft(node.getLeft());
            result = current;
        }
        return result;
    }

    /**
     * Removes elements that match the specified target element from the binary
     * search tree. Throws a ElementNotFoundException if the specified target
     * element is not found in this tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if the target element is not found
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException {
        removeElement(targetElement);

        try {
            while (contains(targetElement)) {
                removeElement(targetElement);
            }
        } catch (Exception ex) {
            throw new ElementNotFoundException("Element was not found");
        }
    }

    /**
     * Removes the node with the least value from the binary search tree and
     * returns a reference to its element. Throws an EmptyCollectionException if
     * this tree is empty.
     *
     * @return a reference to the node with the least value
     */
    @Override
    public T removeMin() {
        if (isEmpty()) {
            return null;
        }

        BinaryTreeNode<T> minNode = findMinNode(root);
        T min = minNode.getElement();
        root = removeMin(root);
        return min;
    }

    /**
     * Removes the node with the least value from the binary search tree and
     * returns a reference to its element. Throws an EmptyCollectionException if
     * this tree is empty.
     *
     * @param node the node to remove
     * @return the removed node
     */
    protected BinaryTreeNode<T> removeMin(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node.getRight();
        } else {
            node.setLeft(removeMin(node.getLeft()));
            return node;
        }
    }

    /**
     * Returns the element with the least value in the binary search tree. It
     * does not remove the node from the binary search tree.
     *
     * @param node the root
     * @return the element with the least value
     */
    protected BinaryTreeNode<T> findMinNode(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node;
        } else {
            return findMinNode(node.getLeft());
        }
    }

    /**
     * Removes the node with the highest value from the binary search tree and
     * returns a reference to its element. .
     *
     * @return a reference to the element with the highest value
     */
    @Override
    public T removeMax() {
        if (isEmpty()) {
            return null;
        }

        BinaryTreeNode<T> maxNode = findMaxNode(root);
        T max = maxNode.getElement();
        root = removeMax(root);
        return max;
    }

    /**
     * Removes the node with the highest value from the binary search tree and
     * returns a reference to its element.
     *
     * @param node the root
     * @return a reference to the node with the highest value
     */
    protected BinaryTreeNode<T> removeMax(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getRight() == null) {
            return node.getLeft();
        } else {
            node.setRight(removeMax(node.getRight()));
            return node;
        }
    }

    /**
     * Returns the element with the highest value in the binary search tree. It
     * does not remove the node from the binary search tree.
     *
     * @param node the root
     * @return the element with the highest value
     */
    protected BinaryTreeNode<T> findMaxNode(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getRight() == null) {
            return node;
        } else {
            return findMaxNode(node.getRight());
        }
    }

    /**
     * Returns the element with the lowest value in the binary search tree. It
     * does not remove the node from the binary search tree.
     *
     * @return the element with the lowest value
     * @throws ElementNotFoundException if this tree is empty.
     */
    @Override
    public T findMin() throws ElementNotFoundException {
        if (isEmpty()) {
            return null;
        }

        BinaryTreeNode<T> minNode = findMinNode(root);
        return minNode.getElement();
    }

    /**
     * Returns the element with the highest value in the binary search tree. It
     * does not remove the node from the binary search tree.
     *
     * @return the elemetn with the highest value
     */
    @Override
    public T findMax() {
        if (isEmpty()) {
            return null;
        }

        BinaryTreeNode<T> maxNode = findMaxNode(root);
        return maxNode.getElement();
    }
}
