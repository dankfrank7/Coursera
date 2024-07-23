package Week4;

public class Heap {

    public static void sort(Comparable[] a) {
        int N = a.length;

        // Build heap (rearrange array)
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }

        // One by one extract an element from heap
        while (N > 1) {
            exch(a, 1, N--); // Move current root to end
            sink(a, 1, N); // call max heapify on the reduced heap
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = swap;
    }

    // Test the Heap Sort implementation
    public static void main(String[] args) {
        String[] arr = {"a", "b", "z", "tom", "te", "da"};
        Heap.sort(arr);
        System.out.println("Sorted array is");
        for (String i : arr) {
            System.out.print(i + " ");
        }
    }
}
