package Week4;

import edu.princeton.cs.algs4.StdOut;

public class UnorderedMaxPQ<Key extends Comparable<Key>>  {

    private Key[] pq;   // pq[i] = ith element on pq (priority queue)
    private int N;      // number of elements on pq

    public UnorderedMaxPQ(int capacity) {
        N = 0;
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[N++] = x;
    }

    public int size() { 
        return N;
    }

    public Key delMax() {
        int max = 0; 
        for (int i = 1; i < N; i++) {
            if (less(max, i)) max = i;        
        }
        exch(max, N-1);
        return pq[--N];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0; 
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args){
        int capacity = 10;
        UnorderedMaxPQ<Integer> pq = new UnorderedMaxPQ<>(capacity);
    
        // Insert some elements into the priority queue
        pq.insert(3);
        pq.insert(1);
        pq.insert(4);
        pq.insert(1);
        pq.insert(5);
        pq.insert(9);
        pq.insert(2);
        pq.insert(6);
        pq.insert(5);
        pq.insert(3);
    
        // Print the size of the priority queue
        StdOut.println("Size of priority queue: " + pq.size());
    
        // Check if the priority queue is empty
        StdOut.println("Is the priority queue empty? " + pq.isEmpty());
    
        // Delete and print the maximum element
        StdOut.println("Deleting max elements:");
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMax());
        }
    
        // Check if the priority queue is empty after deletions
        StdOut.println("Is the priority queue empty? " + pq.isEmpty());
    
        // Try to delete from an empty priority queue to show exception handling
        try {
            pq.delMax();
        } catch (IllegalStateException e) {
            StdOut.println("Caught exception: " + e.getMessage());
        }
    
        // Try to insert into a full priority queue to show exception handling
        UnorderedMaxPQ<Integer> smallPQ = new UnorderedMaxPQ<>(2);
        smallPQ.insert(1);
        smallPQ.insert(2);
        try {
            smallPQ.insert(3);
        } catch (IllegalStateException e) {
            StdOut.println("Caught exception: " + e.getMessage());
        }
    }
}