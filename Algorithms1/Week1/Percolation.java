package Week1;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private boolean[][] grid;
    private int n;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF fullUf;
    private int topVirtualIndex;
    private int botVirtualIndex;
    private int openSiteCount = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be larger than 0.");
        }

        this.grid = new boolean[n][n];
        this.n = n;
        this.topVirtualIndex = n * n;
        this.botVirtualIndex = n * n + 1;

        // We need to create two instnaces uf: connected top and bottom, fullUf: only connected to top
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.fullUf = new WeightedQuickUnionUF(n * n + 1);
    }

    public void open(int row, int col) {
        validateIndices(row, col);

        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            openSiteCount++;

            int index = xyTo1D(row, col);

            // Connect to top virtual site
            if (row == 1) {
                uf.union(index, topVirtualIndex);
                fullUf.union(index, topVirtualIndex);
            }
            // Connect to bottom virtual site
            if (row == n) {
                uf.union(index, botVirtualIndex);
            }

            connectIfOpen(row, col, row - 1, col);
            connectIfOpen(row, col, row + 1, col);
            connectIfOpen(row, col, row, col - 1);
            connectIfOpen(row, col, row, col + 1);

            //printMatrix(grid);
        }
    }

    private void connectIfOpen(int row, int col, int adjRow, int adjCol) {
        if (adjRow > 0 && adjRow <= n && adjCol > 0 && adjCol <= n) {
            if (grid[adjRow-1][adjCol-1]) {
                int index = xyTo1D(row, col);
                int adjIndex = xyTo1D(adjRow, adjCol);
                uf.union(index, adjIndex);
                fullUf.union(index, adjIndex);
            }
        }
    }

    private void validateIndices(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Index out of bounds: row=" + row + ", col=" + col);
        }
    }

    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        int index = xyTo1D(row, col);
        return isOpen(row, col) && fullUf.find(index) == fullUf.find(topVirtualIndex);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return uf.find(topVirtualIndex) == uf.find(botVirtualIndex);
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * n + (col-1);
    }

    private boolean[][] get_grid() {
        return this.grid;
    }

    private static void printMatrix(boolean[][] matrix) {
        System.out.println("-");
        for (boolean[] row : matrix) {
            for (boolean value : row) {
                System.out.print(value ? 1 : 0);  // Print 1 for true, 0 for false
                System.out.print(" ");  
            }
            System.out.println();  
        }
    }

    // test client 
    public static void main(String[] args) {

        // Initialise all sites to be blocked
        Percolation perc = new Percolation(10);
        
        // Testings
        printMatrix(perc.get_grid());
        perc.open(1, 1);
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(3, 2);
        perc.open(4, 2);
        perc.open(5, 2);
        System.out.println("Percolates: " + perc.percolates());
        System.out.println("Open Sites: " + perc.numberOfOpenSites());
    }
}