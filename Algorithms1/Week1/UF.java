package Week1;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UF {

    //private QuickFindUF alg; 
    //private QuickUnion alg; 
    private WeightedQuickUnion alg;

    public UF(int N) {
        // Constructor
        //alg = new QuickFindUF(N);
        alg = new WeightedQuickUnion(N);
    }

    public void union(int p, int q) {
        // Add connection between p and q
        alg.union(p, q);
    }

    public boolean connected(int p, int q) {
        // are p and q in the same component
        return alg.connected(p, q);
    }

    public void print_ids() {
        alg.print_ids();
    }

    public static void main(String[] args) {
        // Client to interact with UF algorithm

        int N = StdIn.readInt(); // First line is the total number of nodes
        UF uf = new UF(N); // Instantiate new UF object

        // Take standard input 
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (!uf.connected(p, q)) {
                uf.union(p, q); 
                StdOut.println(p + " " + q);
            }
        }
        
        // Print out id array
        uf.print_ids();
    }
}