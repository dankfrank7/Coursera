package Week1;

public class QuickUnion {

    protected int[] id;

    public QuickUnion(int N) {
        // Constructor
        id = new int[N]; 

        // Set each element of id equal to it
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    protected int root(int i) {
        // Chase nodes until root is found
        while(i != id[i]) {
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        // Check if p and q have the same root
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        // Change root of p to point to root of q
        int i = root(p);
        int j = root(q); 
        id[i] = j;
    }

    public void print_ids() {
        
        // Print out each line
        System.out.println("Node | Root");
        System.out.println("--------------");
        
        for (int i = 0; i < id.length; i++) {
            System.out.printf("%5d | %5d\n", i, id[i]);
        }
    }

}