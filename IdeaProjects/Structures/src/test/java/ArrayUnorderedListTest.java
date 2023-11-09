package test.java;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.structures.ArrayOrderedList;
import main.java.pt.ipp.estg.ed.structures.ArrayUnorderedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayUnorderedListTest {

    @Test
    void addToFront() throws EmptyCollectionException {
        ArrayUnorderedList<Integer> arr = new ArrayUnorderedList<>();
        arr.addToFront(1);
        arr.addToFront(2);

        assertEquals(arr.first(), 2);
        assertEquals(arr.size(), 2);
    }

    @Test
    void addToRear() throws EmptyCollectionException {
        ArrayUnorderedList<Integer> arr = new ArrayUnorderedList<>();
        arr.addToRear(1);
        arr.addToRear(2);

        assertEquals(arr.last(), 2);
        assertEquals(arr.size(), 2);
    }

    @Test
    void addAfter() {
        ArrayUnorderedList<Integer> arr = new ArrayUnorderedList<>();
        arr.addToFront(1);
        arr.addAfter(2, 1);

        assertEquals(arr.get(1), 2);
        assertEquals(arr.size(), 2);
    }

    @Test
    void expandCapacity() {
        ArrayOrderedList<Integer> arr = new ArrayOrderedList<>();
        for(int i = 0; i < 200; i++) {
            arr.add(i);
        }
        assertEquals(arr.size(), 200);
    }
}