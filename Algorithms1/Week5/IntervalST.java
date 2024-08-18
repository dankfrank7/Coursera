package Week5;

import java.security.Key;
import java.util.*;

public class IntervalST<Key extends Comparable<Key>, Value> 
{
    private Node root;

    private class Node
    {
        private Key lo, hi;
        private Value val;
        private Node left, right;
        private Key max;

        Node(Key lo, Key hi, Value val)
        {
            this.lo = lo;
            this.hi = hi;
            this.val = val;
            this.left = null;
            this.right = null;
            this.max = hi;
        }
    }

    // create interval search tree
    IntervalST()
    {
        this.root = null;
    }

    // put interval-value pair into ST
    public void put(Key lo, Key hi, Value val) 
    {
        this.root = put(root, lo, hi, val);
    }

    private Node put(Node x, Key lo, Key hi, Value val) 
    {
        if (x == null) return new Node(lo, hi, val);
        int cmp = lo.compareTo(x.lo);

        // recursively put nodes
        if (cmp < 0)    x.left = put(x.left, lo, hi, val);
        else            x.right = put(x.right, lo, hi, val);

        // update max
        if (hi.compareTo(x.max) > 0) x.max = hi;
        return x;
    }

    // value paired with given interval
    public Value get(Key lo, Key hi) 
    {
        return get(root, lo, hi);
    }

    private Value get(Node x, Key lo, Key hi) 
    {
        if (x == null) return null;
        int cmp = lo.compareTo(x.lo);
        
        // if value found return 
        if (cmp == 0 && hi.compareTo(x.hi) == 0) return x.val;

        // ...else recursively search bst
        if (cmp < 0)    return get(x.left, lo, hi);
        else            return get(x.right, lo, hi); 
    }
    
    // delete the given interval
    public void delete(Key lo, Key hi) 
    {
        root = delete(root, lo, hi);
    }

    private Node delete(Node x, Key lo, Key hi) 
    {
        if (x == null) return null;
        int cmp = lo.compareTo(x.lo);

        if (cmp < 0)        x.left = delete(x.left, lo, hi);
        else if (cmp > 0)   x.right = delete(x.right, lo, hi);
        else if (hi.compareTo(x.hi) == 0)
        {
            if (x.right == null)    return x.left;
            if (x.left == null)     return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        else x.right = delete(x.right, lo, hi);
        x.max = max(x.hi, max(max(x.left), max(x.right)));
        return x;
    }

    private Node min(Node x)
    {
        if (x.left == null) return x;
        else return min(x.left);
    }

    private Node deleteMin(Node x)
    {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.max = max(x.hi, max(max(x.left), max(x.right)));
        return x;
    }

    private Key max(Node x)
    {
        if (x == null) return null;
        return x.max;
    }

    private Key max(Key x, Key y) 
    {
        if (x == null) return y;
        if (y == null) return x;
        return x.compareTo(y) > 0 ? x : y;
    }

    // all intervals that intersect the given interval
    public Iterable<Value> intersects(Key lo, Key hi)
    {
        List<Value> result = new ArrayList<>();
        intersects(root, lo, hi, result);
        return result;
    }

    private void intersects(Node x, Key lo, Key hi, List<Value> result)
    {
        if (x == null) return;
        if (intersects(x.lo, x.hi, lo, hi))                     result.add(x.val);
        if (x.left != null && x.left.max.compareTo(lo) >= 0)    intersects(x.left, lo, hi, result);
        if (x.right != null)                                    intersects(x.right, lo, hi, result);
    }

    private boolean intersects(Key lo1, Key hi1, Key lo2, Key hi2)
    {
        return lo1.compareTo(hi2) <= 0 && lo2.compareTo(hi1) <= 0;
    }

    public static void main(String[] args) 
    {
        IntervalST<Integer, String> st = new IntervalST<>();
        st.put(15, 20, "A");
        st.put(10, 30, "B");
        st.put(17, 19, "C");
        st.put(5, 20, "D");
        st.put(12, 15, "E");
        st.put(30, 40, "F");

        System.out.println("Intersects with [14, 16]: " + st.intersects(14, 16));
        System.out.println("Get interval [12, 15]: " + st.get(12, 15));
        st.delete(10, 30);
        System.out.println("After deleting interval [10, 30]:");
        System.out.println("Intersects with [14, 16]: " + st.intersects(14, 16));
    }
}