/* *****************************************************************************
 *  Name: Bilal Ansari
 *  Date: 29/12/2023
 *  Description: Permutation implementation
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = 0;
        int n = 0;
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        // 1. Get k
        if (args.length != 1) {
            throw new IllegalArgumentException("Accept only one command-line argument!");
        }
        k = Integer.parseInt(args[0]);

        // 2. Read the input string array
        while (!StdIn.isEmpty()) {
            String temp;
            temp = StdIn.readString();
            rq.enqueue(temp);
            n++;
        }

        if (k < 0 || k > n) {
            throw new IllegalArgumentException(
                    "No. of input string tokens must not be less than k!");
        }

        // 3. Dequeue from randomized queue (k times) and print output
        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}
