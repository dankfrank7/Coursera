package Week2;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedQueueOfStrings {
    
    protected Node first, last;
    
    private class Node {
        String item;
        Node next;
    }

    public void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }      
    }

    public String dequeue() {
        String item = first.item; 
        first = first.next; 
        
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
 
    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        
        // Create arrayStack instnace
        LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
                
        // Take input from standard input
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();

            if (input.equals("-")) {
                String item = queue.dequeue();
                StdOut.println(item);
            } else {
                queue.enqueue(input);      
            }
        }
    }
}