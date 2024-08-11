package Week5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;

public class ST<Key, Value> {
    private ArrayList<Value> values;
    private ArrayList<Key> keys;

    // create symbol table
    ST() {
        this.keys = new ArrayList<Key>();
        this.values = new ArrayList<Value>();
    }

    // put key-value pair into the table 
    // (Remove key from table if value is null)
    void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("call put() with null key");

        if (val == null) {
            delete(key);
            return;
        }

        int index = keys.indexOf(key);
        if (index != -1) {
            values.set(index, val);
        } else {
            keys.add(key);
            values.add(val);
        }
    }
    
    // value paired with key
    // (null if key is absent)
    Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("call get() with null key");

        int index = keys.indexOf(key);
        if (index != -1) {
            return values.get(index);
        }
        return null;
    }

    // remvoe key (and it's value) from table
    void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("call delete() with null key");
        
        // lazy delete      
        put(key, null);
    }

    // is there a value paired with key?
    boolean contains(Key key) {
        return get(key) != null;
    }

    boolean isEmpty() {
        return keys.isEmpty();
    }

    int size() {
        return keys.size();
    }

    // all the keys in the table
    Iterable<Key> keys() {
        return this.keys;
    }

    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }    
        for (String s: st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
