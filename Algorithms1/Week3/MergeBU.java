package Week3;

import java.lang.Math;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MergeBU extends Merge {

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];

        // remove recursion: ~10% slower but much simpler
        for (int sz = 1; sz < N; sz = sz+sz) {
            for (int lo = 0; lo < N - sz; lo += sz+sz) {
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        MergeBU.sort(a);
        for (String s: a) StdOut.println(s);
    }
}