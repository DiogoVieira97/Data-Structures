package test.java;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.structures.SmackStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmackStackTest {

    @Test
    void spy() throws EmptyCollectionException {
        SmackStack<Integer> stack = new SmackStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.spy(), 1);
    }

    @Test
    void smack() throws EmptyCollectionException {
        SmackStack<Integer> stack = new SmackStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.smack(), 1);
    }
}