package test.java;

import main.java.pt.ipp.estg.ed.structures.LinkedStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    @Test
    void isEmpty() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.isEmpty(), false);
    }

    @Test
    void size() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.size(), 2);
    }

    @Test
    void push() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.size(), 2);
    }

    @Test
    void pop() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.pop(), 2);
    }

    @Test
    void peek() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.peek(), 2);
    }

    @Test
    void testToString() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.toString());
    }
}