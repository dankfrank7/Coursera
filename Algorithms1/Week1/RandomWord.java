package Week1;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomWord {
    public static void main(String[] args) {

        // Initialise variables
        String champion = null;
        int count = 0;

        // Loop through every string in args
        while (!StdIn.isEmpty()) {
            String currentWord = StdIn.readString();
            count++;

            // Apply Knuthg's method ('reservoir sampling') to choose a random item
            if (StdRandom.bernoulli(1.0 / count)) {
                champion = currentWord;
            }
        }

        // Print out the champion!
        StdOut.println(champion);
    }
}