package telran.interview;
// All methods should have complexity O[1]

import java.util.EmptyStackException;

// A "Stack" is a linear data structure that adds and removes
// items from the top in a last-in, first-out (LIFO) order.
// This is our custom Stack working with int's
public class MyStackInt {

    // Inner "Node" class to store individual stack elements
    // This is essentially a modified LinkedList 
    private static class Node {

        // To store a integer value in the Node
        int value;

        // To track the maximum element at each node
        // Just for the complexity O[1] for getMaxElement()
        int maxSoFar;

        // To store a link to the next Node
        // You can think of it as a link to previous Node, it doesn't matter
        Node next;

        // Constructor for the Node
        Node(int value, int maxSoFar, Node next) {
            this.value = value;
            this.maxSoFar = maxSoFar;
            this.next = next;
        }
    }

    private Node top; // The top of the stack

    // Constructor of our Stack
    public MyStackInt() {
        top = null; // Stack starts as empty
    }

    // Returns true if the stack is empty, otherwise false
    public boolean isEmpty() {
        return top == null;
    }

    // Helper method to throw an exception if the stack is empty
    // It's not necessary
    private void ifEmptyThrowExeption() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
    }

    // Adds an element to the top of the stack (last element)
    // In "Stack" terminology it's "push"
    public void push(int num) {
        if (isEmpty()) {
            // If stack is empty, num is the max so far
            top = new Node(num, num, null);
        } else {
            // Calculate the new max element so far
            int newMax = Math.max(num, top.maxSoFar);
            // Push the new element onto the stack
            top = new Node(num, newMax, top);
        }
    }

    // Removes element from top of stack (last element)
    // In "Stack" terminology it's "pop"
    // Returns removed number
    // Throws exception if the stack is empty
    public int pop() {
        ifEmptyThrowExeption();
        int removedValue = top.value;
        // Move the top to the next node
        top = top.next;
        return removedValue;
    }

    // Returns last number in the Stack
    // Throws exception if the stack is empty
    public int peek() {
        ifEmptyThrowExeption();
        return top.value;
    }

    // Returns the max number from the stack
    // Throws exception if the stack is empty
    public int getMaxElement() {
        ifEmptyThrowExeption();
        return top.maxSoFar;
    }
}
