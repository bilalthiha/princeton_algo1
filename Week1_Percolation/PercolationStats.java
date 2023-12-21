import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int gSize; // n x n grid size
    private int nOfTrials; // no. of trials

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException(
                    "Grid size and no. of trials must be greater than 0!");
        }
        else {
            gSize = n;
            nOfTrials = trials;
        }
    }

    public int getGSize() {
        return gSize;
    }

    // sample mean of percolation threshold
    // public double mean()

    // sample standard deviation of percolation threshold
    // public double stddev()

    // low endpoint of 95% confidence interval
    // public double confidenceLo()

    // high endpoint of 95% confidence interval
    // public double confidenceHi()

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(4, 20);
        ps.runTrails();
    }

    // ====================== internal helper methods =====================================
    private void perfPercolation() {
        int randRow;
        int randCol;
        boolean pclt = false;

        Percolation pl = new Percolation(gSize);

        // perform percolation of given nxn sized grid
        while (!pclt) {
            randRow = StdRandom.uniformInt(gSize) + 1; // 1 to 3
            randCol = StdRandom.uniformInt(gSize) + 1;
            pl.open(randRow, randCol);
            pclt = pl.percolates();
            if (pclt) {
                StdOut.println("No. of open sites " + pl.numberOfOpenSites());
            }
        }
    }

    private void runTrails() {
        // run m x percolations for m no. of trials
        for (int i = 0; i < nOfTrials; i++) {
            perfPercolation();
            StdOut.println("End of trial " + (i + 1));
            StdOut.println();
        }
    }
}
