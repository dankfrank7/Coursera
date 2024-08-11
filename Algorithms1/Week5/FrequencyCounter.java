package Week5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
        while(!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (!st.contains(word)) st.put(word, 1);
            else                    st.put(word, st.get(word) + 1);
        }
        String max = "";
        st.put(max, 0);
        for (String word: st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        for (String key: st.keys()) {
            StdOut.println(key + " " + st.get(key));
        }
    }
}
