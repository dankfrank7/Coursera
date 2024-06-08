import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Insertion;

public class StringSorter 
{
    public static void main(String[] args)
    {
        String[] a = StdIn.readAllStrings();
 
        Insertion.sort(a);
        for (String s : a) {
            StdOut.println(s);
        }
    }
}