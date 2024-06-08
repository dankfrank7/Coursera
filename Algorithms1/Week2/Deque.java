//package Week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    // private node class
    private class Node {
        // Initilaise item and next / prev links
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0; 
    }

    // return the number of items on the deque 
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Please Enter a valid arguments");
        }
        
        // place the new first and link to the old first
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        
        // Old first now has a prev link...
        if (oldFirst != null) {
            oldFirst.prev = first;
        } else {
            last = first; // ...unless it's empty
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Please Enter a valid arguments");
        }
        
        // Place new last and link to old last
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {

            // Link oldlast and last together
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = first.item;
        first = first.next;

        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = last.item;
        last = last.prev;
        
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // private list iterator class
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Method not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Deque is empty");
            }  
        
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        System.out.println("Size = " + deque.size());

        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " ");
        }
  
        System.out.println("\nRemoved from front: " + deque.removeFirst()); 
        System.out.println("Removed from back: " + deque.removeLast());
        System.out.println("Size: " + deque.size()); 
    }
}