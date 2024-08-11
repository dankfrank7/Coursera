package Week5;

import java.util.LinkedList;
import java.util.Queue;

public class RangeSearchBST<Key extends Comparable<Key>, Value> { 
    private Node root;
    
    private class Node {
        Key key;
        Value val;
        Node left, right; 
        int size; // number of nodes in subtree rooted here

        public  Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.left = null;
            this.right = null;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else    x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    // return the keys between a given range
    public Iterable<Key> rangeSearch(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        rangeSearch(root, queue, lo, hi);
        return queue;
    }

    private void rangeSearch(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0) rangeSearch(x.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) queue.add(x.key);
        if (cmpHi > 0) rangeSearch(x.right, queue, lo, hi);
    }

    // return the number of key, value pairs between a given range
    public int rangeCount(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) return 0;
        return rank(hi) - rank(lo) + (contains(hi) ? 1 : 0);
    }

    // return the 1d rank for a given key
    private int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        else return size(x.left);
    }

    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    private boolean contains(Key key) {
        return get(key) != null;
    }

    private Value get(Key key) {
        Node x = root; 
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0)   x = x.left; 
            else if (cmp > 0)   x = x.right;
            else                return x.val;
        }
        return null; // should never reach here
    }
    
    private static void visualiseBST(RangeSearchBST.Node node) {
        if (node == null) return;
        printBST("", node, false);
    }

    private static void printBST(String prefix, RangeSearchBST.Node node, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.key + " [" + node.val + "]");
        // Recur on the left child and right child with updated prefix
        printBST(prefix + (isLeft ? "│   " : "    "), node.left, true);
        printBST(prefix + (isLeft ? "│   " : "    "), node.right, false);
    }

    public static void main(String[] args) {
        RangeSearchBST<String, Integer> bst = new RangeSearchBST<>();

        // Insert some key-value pairs
        bst.put("S", 1);
        bst.put("E", 2);
        bst.put("A", 3);
        bst.put("R", 4);
        bst.put("C", 5);
        bst.put("H", 6);
        bst.put("X", 7);

        // perform range count
        int count = bst.rangeCount("C","S");
        System.out.println("Number of keys in range C to S: " + count);

        // perform range search 
        Iterable<String> result = bst.rangeSearch("C","S");
        System.out.println("Keys in range C to S:");
        for (String key : result) {
            System.out.println(key + " -> " + bst.get(key));
        }

        // visualise the tree 
        visualiseBST(bst.root);
    }
}


