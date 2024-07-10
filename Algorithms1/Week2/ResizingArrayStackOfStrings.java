import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStackOfStrings extends FixedCapacityOfStrings {

    public ResizingArrayStackOfStrings(int capacity) {
        super(capacity);
    }

    @Override
    public void push(String item) {
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }

    @Override
    public String pop() {
        if (isEmpty()) {
            StdOut.println("Stack is empty!");
            return null;
        }
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) {
            resize(s.length/2);
        }
        return item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }

    public static void main(String[] args) {
        
        // Create arrayStack instnace
        int initialCapcity = 5;
        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings(initialCapcity);
        
        // Take input from standard input
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();

            if (input.equals("-")) {
                String item = stack.pop();
                StdOut.println(item);
            } else {
                stack.push(input);      
            }
        }
    }
}
