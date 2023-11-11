package test.java;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.structures.CircularArrayQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularArrayQueueTest {

    @Test
    void enqueue() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(queue.size(), 2);
    }

    @Test
    void dequeue() throws EmptyCollectionException {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(queue.dequeue(), 1);
    }

    @Test
    void first() throws EmptyCollectionException {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(queue.first(), 1);
    }

    @Test
    void isEmpty() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertFalse(queue.isEmpty());
    }

    @Test
    void size() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(queue.size(), 2);
    }

    @Test
    void testToString() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.println(queue.toString());
    }
}