import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Shell { 
    public static void sort(Comparable[] a) {
        int N = a.length; 

        // 3x + 1 increment sequence
        int h = 1;
        while (h < N/3) { 
            h = 3*h + 1;
        }

        // h-sort the array
        while (h >= 1) {

            // outer loop
            for (int i = h; i < N; i++) {

                // inner loop 
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h = h/3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i]; 
        a[i] = a[j];
        a[j] = swap;
    }
    
    public static void main(String[] args) {
        Integer[] a = new Integer[50];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniformInt(100);  // Generate random integers between 0 and 99
        }
        
        // Print the array before sorting
        StdOut.println("Before sorting:");
        for (int n : a) StdOut.print(n + " ");
        StdOut.println();

        // Perform the sort
        Shell.sort(a);
        
        // Print the array after sorting
        StdOut.println("\nAfter sorting:");
        for (int n : a) StdOut.print(n + " ");
        StdOut.println();
    }
}