// Week 1 Assignment 
package Week1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private int n;
    private Percolation perc;
    private double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        validateTrials(trials);

        this.n = n;
        this.thresholds = new double[trials];
        
        // Monte-Carlo simulation
        for (int trial = 0; trial < trials; trial++) {
            this.thresholds[trial] = find_threshold();
        }
    }

    // Monte-Carlo Iteration
    private double find_threshold() {
        
        Percolation perc = new Percolation(this.n);

        while (!perc.percolates()) {
            int randRow = StdRandom.uniformInt(1, this.n + 1);
            int randCol = StdRandom.uniformInt(1, this.n + 1);

            if (!perc.isOpen(randRow, randCol)) {
                perc.open(randRow, randCol);
            }
        }
        double threshold = perc.numberOfOpenSites() / (this.n * this.n);
        return threshold;
    }

    // Returns the mean perc threshold of multiple percoluation simulation tests
    public double mean() {

    }

    // sample standard deviation of percolation threshold
    public double stddev() {

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

    }

    private void validateTrials(int trials) {
        if (!(trials > 0)) {
            throw new IllegalArgumentException("Trials must be larger than 0")
        }
    }

   // test client (see below)
   public static void main(String[] args) {

   }
}