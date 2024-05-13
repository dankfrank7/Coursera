// Week 1 Assignment 
package Week1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int n;
    private int trials;
    private double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        validateTrials(trials);

        this.n = n;
        this.trials = trials;
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
        double threshold = (double) perc.numberOfOpenSites() / (this.n * this.n);
        return threshold;
    }

    // Returns the mean perc threshold of multiple percoluation simulation tests
    public double mean() {
        return StdStats.mean(this.thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(this.trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(this.trials);
    }

    private void validateTrials(int trials) {
        if (!(trials > 0)) {
            throw new IllegalArgumentException("Trials must be larger than 0");
        }
    }

    // test client 
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Please enter two command line args (n: grid size, T: nTrials)");
        }
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        // Run Monte-Carlo sim
        PercolationStats stats = new PercolationStats(n, trials);
        
        System.out.printf("Mean                    = %f\n", stats.mean());
        System.out.printf("Stddev                  = %f\n", stats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
    }
}