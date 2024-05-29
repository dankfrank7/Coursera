package Week2; 

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {

    public static void main(String[] args) {
    
        // two stacks for operations and values
        StackOf<String> ops = new StackOf<String>();
        StackOf<Double> vals = new StackOf<Double>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString(); 
            if (s.equals("(")) { 
                // do nothing
            } else if (s.equals("+")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals("*")) {
                    vals.push(vals.pop() * vals.pop());
                }
            } else vals.push(Double.parseDouble(s));
        } 
        StdOut.println(vals.pop());
    }
}