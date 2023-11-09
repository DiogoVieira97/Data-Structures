package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.desktop.SystemEventListener;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedUnorderedListTest {

    @Test
    void removeFirst() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addAfter(2, 1);
        assertTrue(list.contains(2));
    }

    @Test
    void removeLast() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addAfter(2, 1);
        assertEquals(list.removeLast(), 2);
    }

    @Test
    void remove() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addAfter(2, 1);
        assertEquals(list.remove(2), 2);
    }

    @Test
    void first() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addAfter(2, 1);
        assertEquals(list.first(), 1);
    }

    @Test
    void last() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addAfter(2, 1);
        assertEquals(list.last(), 2);
    }

    @Test
    void contains() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addAfter(2, 1);
        assertTrue(list.contains(2));
    }

    @Test
    void isEmpty() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    void size() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addAfter(2, 1);
        assertEquals(list.size(), 2);
    }

    @Test
    void reverse() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addToFront(2);
        list.reverse();
        System.out.println(list.toString());
        assertEquals(list.first(), 1);
    }

    @Test
    void addToFront() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addToFront(2);
        assertEquals(list.first(), 2);
    }

    @Test
    void addToRear() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addToFront(2);
        assertEquals(list.last(), 1);
    }

    @Test
    void addAfter() throws EmptyCollectionException {
        DoubleLinkedUnorderedList<Integer> list = new DoubleLinkedUnorderedList<>();
        list.addToFront(1);
        list.addAfter(2, 1);
        assertTrue(list.contains(2));
    }
}