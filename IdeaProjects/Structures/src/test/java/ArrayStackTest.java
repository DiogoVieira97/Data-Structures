package test.java;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.structures.ArrayStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    void push() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.size(), 2);
    }

    @Test
    void pop() throws EmptyCollectionException {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.pop(), 2);
    }

    @Test
    void peek() throws EmptyCollectionException {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.peek(), 2);
    }

    @Test
    void isEmpty() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        assertFalse(stack.isEmpty());
    }

    @Test
    void size() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.size(), 2);
    }
}