import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // Constants
    private static final int LEFT_INC = -1;
    private static final int RIGHT_INC = 1;
    private static final int TOP_INC = -1;
    private static final int BOTTOM_INC = 1;

    // an array to track open status for each site and num of open sites
    private boolean[] openSites;
    private int numOfOpenSites;

    // WQUF object to model dynamic connectivity of nodes
    private WeightedQuickUnionUF uf;

    // Number of nodes
    private int numOfNodes;

    // Grid width
    private int gridWidth;

    // Top and bottom root nodes' indices
    private int topRoot;
    private int botRoot;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        boolean isValidGSize;
        isValidGSize = isGSizeValidInput(n);
        if (isValidGSize) {
            // Save grid width
            gridWidth = n;
            // Get the number of nodes. 2 additional nodes for the top and bottom roots
            numOfNodes = (n * n) + 2;
            topRoot = (n * n);
            botRoot = (n * n) + 1;

            // Init open status array as false. All sites are BLOCKED initially.
            openSites = new boolean[numOfNodes];
            for (int i = 0; i < numOfNodes; i++) {
                openSites[i] = false;
            }
            numOfOpenSites = 0;

            // Init union-find model
            uf = new WeightedQuickUnionUF(numOfNodes);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int siteIdx = 0; // index of target cell in the grid
        boolean isO = false;
        boolean isInpValid = isRCValidInput(row, col);
        if (isInpValid) {
            siteIdx = getSiteIndex(row, col);
            // open a site if it's not already open
            isO = isOpen(row, col);
            if (!isO) {
                // 1. mark the site as open
                openSites[siteIdx] = true;
                // 2. Connect to any neighbouring open sites
                connect2Neighbours(row, col);
                // 3. Increment the number of open sites
                numOfOpenSites += 1;
            }
        } // else do nothing
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int siteIdx = 0;
        boolean ret = false;
        boolean isInpValid = isRCValidInput(row, col);
        if (isInpValid) {
            siteIdx = getSiteIndex(row, col);
            ret = openSites[siteIdx];
        }
        return ret;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int siteIdx = getSiteIndex(row, col);
        boolean ret = false;
        boolean isInpValid = isRCValidInput(row, col);
        boolean isO = isOpen(row, col);

        if (isInpValid && isO) {
            if (row == 1) {
                ret = true; // An open item on the first row is full because it's self-connected
            }
            else {
                // a site is full when it's connect to the top root
                // ret = uf.connected(siteIdx, topRoot) //"connected()" API is deprecated
                ret = (uf.find(siteIdx) == uf.find(topRoot));
            }
        }
        return ret;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean ret = false;
        // the system percolates when the bottom root connected to the top root
        // ret = uf.connected(topRoot, botRoot); //"connected()" API is deprecated
        ret = (uf.find(topRoot) == uf.find(botRoot));
        return ret;
    }

    // test client (optional)
    // public static void main(String[] args)

    // ====================== internal helper methods =====================================
    private int getSiteIndex(int row, int col) {
        int siteIndex;
        siteIndex = (row * gridWidth) - (gridWidth - col);
        siteIndex -= 1;

        return siteIndex;
    }

    private boolean isRCValidInput(int row, int col) {
        boolean out = false;
        if (row < 1 || row > gridWidth || col < 1 || col > gridWidth) {
            throw new IllegalArgumentException("Number of rows or columns must be between 1 and n");
        }
        else {
            out = true;
        }
        return out;
    }

    private boolean isGSizeValidInput(int n) {
        boolean out = false;
        if (n < 1) {
            throw new IllegalArgumentException("Number of sites must be greater than 0.");
        }
        else {
            out = true;
        }
        return out;
    }

    private void connect2Neighbours(int row, int col) {
        int siteIdx = 0;
        int nbIdx = 0;
        boolean isO = false;

        siteIdx = getSiteIndex(row, col);

        // check left
        if (col != 1) {
            isO = isOpen(row, col + LEFT_INC);
            if (isO) {
                nbIdx = getSiteIndex(row, col + LEFT_INC);
                uf.union(siteIdx, nbIdx);
            }
        }

        // check right
        if (col != gridWidth) {
            isO = isOpen(row, col + RIGHT_INC);
            if (isO) {
                nbIdx = getSiteIndex(row, col + RIGHT_INC);
                uf.union(siteIdx, nbIdx);
            }
        }

        // check top
        if (row != 1) {
            isO = isOpen(row + TOP_INC, col);
            if (isO) {
                nbIdx = getSiteIndex(row + TOP_INC, col);
                uf.union(siteIdx, nbIdx);
            }
        }
        else {
            // special case for efficiency
            // Open top root
            openSites[topRoot] = true;

            // If top row, connect to the top root
            uf.union(siteIdx, topRoot);
        }

        // check bottom
        if (row != gridWidth) {
            isO = isOpen(row + BOTTOM_INC, col);
            if (isO) {
                nbIdx = getSiteIndex(row + BOTTOM_INC, col);
                uf.union(siteIdx, nbIdx);
            }
        }
        else {
            // special cases for efficiency
            // Open bottom root
            openSites[botRoot] = true;
            // If bottom row, connect to the bottom root
            uf.union(siteIdx, botRoot);
        }
    }
}
