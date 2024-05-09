package Week1;

public class QuickFindUF {
    // Initialise a int array 
    private int[] id;

    public QuickFindUF(int N) {    
        // Constructor
        id = new int[N];
 
        // Set each element of id equal to it
        for (int i = 0; i < N; i++) {
            id[i]  = i;
        }
    }

    public boolean connected(int p, int q) {
        // Returns true if p and q are in the same component        
        return id[p] == id[q];
    }
    
    public void union(int p, int q) {
        // Change all entries with id[p] to id[q]
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
    
    public void print_ids() {
        
        // Print out each line
        System.out.println("Node | Set");
        System.out.println("--------------");
        
        for (int i = 0; i < id.length; i++) {
            System.out.printf("%5d | %5d\n", i, id[i]);
        }
    }


}