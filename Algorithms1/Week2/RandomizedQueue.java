//package Week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // Construct an empty randomized queue
    public RandomizedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the number of items in the queue
    public int size() {
        return size;
    }

    // Add the item to tail
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int randomIndex = StdRandom.uniformInt(size);
        Node current = head;
        for (int i = 0; i < randomIndex; i++) {
            current = current.next;
        }

        // Check if current node is at head
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }

        // Chec if current node is at tail
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }

        size--;
        return current.item;
    }

    // Return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        int randomIndex = StdRandom.uniformInt(size);
        Node current = head;
        for (int i = 0; i < randomIndex; i++) {
            current = current.next;
        }
        return current.item;
    }

    // Return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node currentIt;
        private final int[] order = StdRandom.permutation(size);
        private int index = 0;

        public boolean hasNext() {
            return index < size;
        }

        public Item next() {
            currentIt = head;
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            // Move to the node at the index specified by order[index]
            for (int i = 0; i < order[index]; i++) {
                currentIt = currentIt.next;
            }
            index++;
            return currentIt.item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Unit testing
    public static void main(String[] args) {
        // Implement some test cases
        RandomizedQueue<String> deque = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();

            if (input.equals("-")) {
                String item = deque.dequeue();
                StdOut.println(item);
            } else {
                deque.enqueue(input);      
            }

        }
    }
}
