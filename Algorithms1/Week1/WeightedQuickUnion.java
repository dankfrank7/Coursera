package Week1;

public class WeightedQuickUnion extends QuickUnion {
    // Adds tree weighting for performance improvement
    private int[] size;

    public WeightedQuickUnion(int N) {
        
        // Parent constructor
        super(N);

            // Start with all trees of size one
        size = new int[N];
        for (int i = 0; i < N; i++) {
            size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        // Override union to link root of smaller tree to root of larger tree 
        int i = root(p);
        int j = root(q); 
        if (i == j) {
            return;
        }        
        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
    }
}