package test.java;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.structures.ArrayOrderedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayOrderedListTest {

    @Test
    void add() {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(1);
        assertEquals(arr.size(), 1);
    }

    @Test
    void expandCapacity() {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        for (int i = 0; i < 200; i++) {
            arr.add(i);
        }
        assertEquals(arr.size(), 200);
    }

    @Test
    void removeFirst() throws EmptyCollectionException {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(2);
        arr.add(1);
        assertEquals(arr.removeFirst(), 1);
    }

    @Test
    void removeLast() throws EmptyCollectionException {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(2);
        arr.add(1);
        assertEquals(arr.removeLast(), 2);
    }

    @Test
    void remove() throws EmptyCollectionException {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(2);
        arr.add(1);
        assertEquals(arr.remove(1), 1);
    }

    @Test
    void first() throws EmptyCollectionException {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(2);
        arr.add(1);
        assertEquals(arr.first(), 1);
    }

    @Test
    void last() throws EmptyCollectionException {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(2);
        arr.add(1);
        assertEquals(arr.last(), 2);
    }

    @Test
    void get() {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(2);
        arr.add(1);
        assertEquals(arr.get(0), 1);
        assertEquals(arr.get(1), 2);
    }

    @Test
    void contains() {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(2);
        arr.add(1);
        assertTrue(arr.contains(1));
    }

    @Test
    void isEmpty() {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(2);
        arr.add(1);
        assertFalse(arr.isEmpty());

    }

    @Test
    void size() {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        arr.add(2);
        arr.add(1);
        assertEquals(arr.size(), 2);
    }

}