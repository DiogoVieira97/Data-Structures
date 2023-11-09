package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.ElementNotFoundException;
import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.BinaryTreeADT;

import java.util.Iterator;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected T[] tree;
    private final int capacity = 50;

    /**
     * Creates an empty binary tree.
     */
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[capacity];
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the root element
     */
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[capacity];

        tree[0] = element;
    }


    protected void expandCapacity() {
        T[] temp = (T[]) new Object[tree.length * 2];
        for (int ct = 0; ct < tree.length; ct++)
            temp[ct] = tree[ct];
        tree = temp;
    }

    /**
     * Removes the left subtree of this binary tree.
     */
    public void removeLeftSubtree() {

    }

    /**
     * Removes the right subtree of this binary tree.
     */
    public void removeRightSubtree() {

    }

    /**
     * Deletes all nodes from the binary tree.
     */
    public void removeAllElements() {
        count = 0;
        for (int ct = 0; ct < tree.length; ct++)
            tree[ct] = null;
    }

    /**
     * Returns a reference to the root element
     *
     * @return a reference to the root
     * @throws EmptyCollectionException
     */
    @Override
    public T getRoot() throws EmptyCollectionException {
        return null;
    }

    /**
     * Returns true if the binary tree is empty and false otherwise.
     *
     * @return true if the binary tree is empty and false otherwise.
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Returns true if the binary tree is empty and false otherwise.
     *
     * @return true if the binary tree is empty and false otherwise.
     */
    public int size() {
        return count;
    }


    /**
     * Returns true if the tree contains an element that matches the
     * specified target element and false otherwise.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the binary tree contains the element, false otherwise
     */
    public boolean contains(T targetElement) {
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++)
            if (targetElement.equals(tree[ct])) found = true;

        return found;

    }

    /**
     * Returns a reference to the specified target element if it is
     * found in the binary tree.  Throws a NoSuchElementException if
     * the specified target element is not found in the binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @return the element to find
     * @throws ElementNotFoundException
     */
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++)
            if (targetElement.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }

        if (!found) throw new ElementNotFoundException("binary tree");

        return temp;


    }

    /**
     * Returns a string representation of the binary tree.
     *
     * @return a string representation of the binary tree.
     */
    public String toString() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        inorder(0, templist);
        return templist.toString();
    }

    /**
     * Performs an inorder traversal on the binary tree by calling an
     * overloaded, recursive inorder method that starts with
     * the root.
     *
     * @return the iterator
     */
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        inorder(0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node
     * @param templist
     */
    protected void inorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) if (tree[node] != null) {
            inorder((node + 1) * 2 - 1, templist);
            templist.addToRear(tree[node]);
            inorder((node + 1) * (2 + 1) - 1, templist);
        }

    }

    /**
     * Performs a preorder traversal on the binary tree by calling an
     * overloaded, recursive preorder method that starts with
     * the root.
     *
     * @return the iterator
     */
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        preorder(0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node
     * @param templist
     */
    protected void preorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) if (tree[node] != null) {
            templist.addToRear(tree[node]);
            inorder((node + 1) * 2 - 1, templist);
            inorder((node + 1) * (2 + 1) - 1, templist);
        }


    }

    /**
     * Performs an postorder traversal on the binary tree by calling
     * an overloaded, recursive postorder method that starts
     * with the root.
     *
     * @return the iterator
     */
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        postorder(0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node
     * @param templist
     */
    protected void postorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) if (tree[node] != null) {
            inorder((node + 1) * 2 - 1, templist);
            inorder((node + 1) * (2 + 1) - 1, templist);
            templist.addToRear(tree[node]);

        }


    }

    /**
     * Performs a levelorder traversal on the binary tree, using a
     * templist.
     *
     * @return the iterator
     */
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        for (int ct = 0; ct < count; ct++)
            templist.addToRear(tree[ct]);
        return templist.iterator();
    }
}
