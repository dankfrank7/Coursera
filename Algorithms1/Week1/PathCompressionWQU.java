package Week1; 

public class PathCompressionWQU extends WeightedQuickUnion {

    public PathCompressionWQU(int N) {
        super(N);
    }

    @Override
    protected int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // Second degree comporession
            i = id[i];
        }
        return i;
    }
}