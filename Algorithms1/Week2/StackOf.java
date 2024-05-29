package Week2;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;


public class StackOf<Item> implements Iterable<Item> {

    // Store the reference to the first node
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop() {
        if (isEmpty()) {
            return null;
        }

        Item item = first.item;
        first = first.next;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
       }

    }

    public static void main(String args[]) {
        // Create a stack and push elements
        StackOf<String> stringStack = new StackOf<>();
        stringStack.push("hello");
        stringStack.push("world");
        stringStack.push("this");
        stringStack.push("is");
        stringStack.push("iterator");
        stringStack.push("test");

        // Use iterator to traverse the stack
        StdOut.println("Iterating over stack:");
        for (String s : stringStack) {
            StdOut.println(s);
        }

	// I want to first check how I can run this!
        // Testing the pop functionality to verify stack behavior
        StdOut.println("\nPopping from stack:");
        while (!stringStack.isEmpty()) {
            StdOut.println(stringStack.pop());
        }
    }
}
