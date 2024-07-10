package Week3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Merge {

    protected static Comparable[] aux;

    protected static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);    // precondition: first half sorted
        assert isSorted(a, mid+1, hi);  // precondition: second half sorted 

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; // copy a[]
        }

        // merge
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)               a[k] = aux[j++]; 
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
    }

    // internal sort
    protected static void sort(Comparable[] a, Comparable aux[], int lo, int hi) {
        if (hi <= lo) return; 

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid); 
        sort(a, aux, mid+1, hi); 
        merge(a, aux, lo, mid, hi);
    }

    // public sort
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }

    protected static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }
    
    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main (String[] args) {
        String[] a = StdIn.readAllStrings();
        Merge.sort(a);
        for (String s: a) StdOut.println(s);
    }
}
