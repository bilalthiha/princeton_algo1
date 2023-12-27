import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private int gWidth, gHeight; // n x n grid size
    private double mn; // mean
    private double stdDev; // standard deviation
    private double lowConf, highConf; // confidence interval

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException(
                    "Grid size and no. of trials must be greater than 0");
        }
        else {
            gWidth = n;
            gHeight = gWidth; // n x n grid
            // peform t times percolation experiments (dislike doing here in constructor but the assignment forces it so)
            runTrails(trials);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return mn;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stdDev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return lowConf;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return highConf;
    }

    // test client (see below)
    public static void main(String[] args) {
        int gSize, trials;
        // 1. Get input (n and T) from command line arguments
        inputValChecker(args.length);
        gSize = getIntGSize(args);
        trials = getIntTrials(args);

        // 2. Perform experiments and calculate output (mean, standard deviation and conf. intervals)
        PercolationStats ps = new PercolationStats(gSize, trials);

        // 3. Print out output (mean, standard deviation and conf. intervals)
        StdOut.printf("mean                    = %f\n", ps.mn);
        StdOut.printf("stddev                  = %f\n", ps.stdDev);
        StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.lowConf, ps.highConf);
    }

    // ====================== internal helper methods =====================================
    private static void inputValChecker(int inpLen) {
        if (inpLen != 2) {
            throw new IllegalArgumentException(
                    "Please enter exactly two integer arguments from the command line.");
        }
    }

    private static int getIntGSize(String[] argStr) {
        return Integer.parseInt(argStr[0]);
    }

    private static int getIntTrials(String[] argStr) {
        return Integer.parseInt(argStr[1]);
    }

    private int perfPercolation() {
        int randRow;
        int randCol;
        boolean pclt = false;
        int noOfOpenSites = 0;
        Percolation pl = new Percolation(gWidth); // get new perc object for each trial

        // perform percolation of given nxn sized grid
        while (!pclt) {
            randRow = StdRandom.uniformInt(gWidth) + 1; // 1 to 3
            randCol = StdRandom.uniformInt(gWidth) + 1;
            pl.open(randRow, randCol);
            pclt = pl.percolates();
        }
        noOfOpenSites = pl.numberOfOpenSites();
        return noOfOpenSites;
    }

    private void runTrails(int nOfTrials) {
        int nOfOpenSites = 0;
        double[] fOfOsPerTrial = new double[nOfTrials]; // x, i.e, fract. of open sites per trial

        // run m x percolations for m no. of trials
        for (int i = 0; i < nOfTrials; i++) {
            nOfOpenSites = perfPercolation();
            fOfOsPerTrial[i] = (nOfOpenSites * 1.0) / (gWidth * gHeight);
        }

        // calculate mean
        mn = StdStats.mean(fOfOsPerTrial);

        // calculate stddev
        stdDev = StdStats.stddev(fOfOsPerTrial);

        // calculate confidence interval
        lowConf = mn - ((CONFIDENCE_95 * stdDev) / Math.sqrt(nOfTrials));
        highConf = mn + ((CONFIDENCE_95 * stdDev) / Math.sqrt(nOfTrials));
    }
}
