package Week4;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Insertion;

public class Quick {
    private static final int CUTOFF = 10;

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
            }

            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + CUTOFF -1) {
            Insertion.sort(a, lo, hi);
            return;
        }   
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static COmparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if      (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else           return a[k];
        }
        return a[k];
    }

    // true if a < b, false otherwise
    private static boolean less(Comparable a, Comparable b) {

        // -1 if a < b, 0 if a == b, +1 if a > b
        return a.compareTo(b) < 1;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable copy = a[i]; 
        a[i] = a[j];
        a[j] = copy;
    }

    public static void main(String[] args) {

    }
}
