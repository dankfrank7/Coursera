package Week2;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityOfStrings {

    protected String[] s;
    protected int N = 0;

    public FixedCapacityOfStrings(int capacity) {
        this.s = new String[capacity];
    }

    public boolean isEmpty() {
        return this.N == 0;
    }

    public void push(String item) {
        if (N == s.length) {
            StdOut.println("Stack is full!");
            return; // Optionally, you could also throw an exception here
        }
        this.s[N++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            StdOut.println("Stack is empty!");
            return null;
        } else {
            String item = this.s[--N];
            this.s[N] = null;
            return item;
        }
    }

    public static void main(String[] args) {
        
        // Create arrayStack instnace
        int capacity = 5;
        FixedCapacityOfStrings arrayStack = new FixedCapacityOfStrings(capacity);
        arrayStack.push("first");
        arrayStack.push("second");
        StdOut.println(arrayStack.pop());
        StdOut.println(arrayStack.pop());
        StdOut.println(arrayStack.pop());
    }
}