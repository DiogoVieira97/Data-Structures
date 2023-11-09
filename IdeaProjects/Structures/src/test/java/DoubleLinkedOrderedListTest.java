package test.java;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.structures.DoubleLinkedOrderedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedOrderedListTest {

    @Test
    void removeFirst() throws EmptyCollectionException {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        assertEquals(list.removeFirst(), 1);
    }

    @Test
    void removeLast() throws EmptyCollectionException {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        assertEquals(list.removeLast(), 2);
    }

    @Test
    void remove() throws EmptyCollectionException {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        assertEquals(list.remove(1), 1);
    }

    @Test
    void first() throws EmptyCollectionException {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        assertEquals(list.first(), 1);
    }

    @Test
    void last() throws EmptyCollectionException {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        assertEquals(list.last(), 2);
    }

    @Test
    void contains() {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        assertTrue(list.contains(1));
    }

    @Test
    void isEmpty() {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void size() {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        assertEquals(list.size(), 2);
    }

    @Test
    void reverse() throws EmptyCollectionException {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        list.reverse();
        System.out.println(list.toString());
        assertEquals(list.first(), 2);
    }

    @Test
    void add() {
        DoubleLinkedOrderedList<Integer> list = new DoubleLinkedOrderedList<>();
        list.add(2);
        list.add(1);
        assertEquals(list.size(), 2);
    }
}