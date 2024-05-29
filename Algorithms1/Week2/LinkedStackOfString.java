package Week2;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStackOfString {
    
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null; // Optionally, you could throw an exception here
        }
        String item = first.item;
        first = first.next; 
        return item;
    }

    public static void main(String[] args) {

        // Create linked list instance
        LinkedStackOfString linkedList = new LinkedStackOfString();
        linkedList.push("first");
        linkedList.push("second");
        StdOut.println(linkedList.pop());
        StdOut.println(linkedList.pop());
        linkedList.push("third");
        StdOut.println(linkedList.pop());
        linkedList.pop();
    }
}