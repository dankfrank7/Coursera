//package Week2;

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;


public class Permutation {
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Expected 1 argument for number of iterations");
        }
        int iters = Integer.parseInt(args[0]);

        // create randque 
        RandomizedQueue<String> randQue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            randQue.enqueue(input);      
        }

        Iterator<String> iterator = randQue.iterator(); // create an iterator for each number
        for (int i = 1; i < iters + 1; i++) {   // need to start at one 
            System.out.println(iterator.next());
        }
    }
}