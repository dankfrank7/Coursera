
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Insertion;

public class Experiment 
{
    public static void main(String[] args)
    {
        
        // Create an array of random numbers
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) 
        {
            a[i] = StdRandom.uniformDouble();
        }

        Insertion.sort(a);
        for (int i = 0; i < N; i++) 
        {
            StdOut.println(a[i]);
        }
    }
}