package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.HeapADT;

public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {


    public ArrayHeap() {
        super();
    }

    /**
     * Adds the specified element to the heap in the appropriate
     * position according to its key value.  Note that equal elements
     * are added to the right.
     *
     * @param obj the element to added to this head
     */
    public void addElement(T obj) {
        if (count == size()) expandCapacity();

        tree[count] = obj;
        count++;

        if (count > 1) heapifyAdd();
    }

    /**
     * Reorders the heap to maintain the ordering property
     */
    private void heapifyAdd() {
        T temp;

        int next = count - 1;
        while ((next != 0) && (((Comparable) tree[next]).compareTo(tree[(next - 1) / 2]) < 0)) {
            temp = tree[next];
            tree[next] = tree[(next - 1) / 2];
            tree[(next - 1) / 2] = temp;
            next = (next - 1) / 2;
        }
    }

    /**
     * Remove the element with the lowest value in the heap and
     * returns a reference to it.  Throws an EmptyHeapException if
     * the heap is empty.
     *
     * @return the removed element
     * @throws EmptyCollectionException
     */
    public T removeMin() throws EmptyCollectionException {

        if (isEmpty()) throw new EmptyCollectionException("Empty Heap");

        T minElement = tree[0];

        tree[0] = tree[count - 1];
        heapifyRemove();
        count--;
        return minElement;

    }

    /**
     * reorder the heap to maintain the ordering property
     */
    private void heapifyRemove() {
        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null)) next = count;
        else if (tree[left] == null) next = right;
        else if (tree[right] == null) next = left;
        else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) next = left;
        else next = right;
        while ((next < count) && (((Comparable) tree[next]).compareTo(tree[node]) < 0)) {
            temp = tree[node];
            tree[node] = tree[next];
            tree[next] = temp;
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);
            if ((tree[left] == null) && (tree[right] == null)) next = count;
            else if (tree[left] == null) next = right;
            else if (tree[right] == null) next = left;
            else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) next = left;
            else next = right;
        }
    }

    /**
     * Returns the element with the lowest value in the heap.
     * Throws an EmptyHeapException if the heap is empty.
     *
     * @return the min element
     * @throws EmptyCollectionException
     */
    public T findMin() throws EmptyCollectionException {

        if (isEmpty()) throw new EmptyCollectionException("Empty Heap");

        return tree[0];

    }


}
