package Week4;

import edu.princeton.algs4.StdIn;

public class UnorderedMaxPQ<Key extends Comparable<Key>  {

    private Key[] pq;

    // example api, adjust as needed
    static public void main(String[] args) {
        
        UnorderedMaxPQ<String> pq = new UnorderedMaxPQ<String>;

        while (StdIn.hasNextLine())
        {
            String line = StdIn.readLine();
            pq.insert(line);
            if (pq.size() > M) {
                pq.delMax();
            }
        }
    }
}
