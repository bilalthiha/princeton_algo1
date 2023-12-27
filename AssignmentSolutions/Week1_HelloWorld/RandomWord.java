import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        double prob = 0.0;
        boolean isChamp = false;
        String currWord = "";
        String champ = "";
        int i = 1;

        while (!StdIn.isEmpty()) {
            // 1. Read each word from the standard input and calculate its probability.
            currWord = StdIn.readString();
            prob = 1.0 / i;
            // 2. Use the given formula to determine championship (true)
            isChamp = StdRandom.bernoulli(prob);
            // 3. Store the index of item (replace prev champion) if the present item is champion
            if (isChamp) {
                champ = currWord;
            }
            // 4. Increment counter
            i++;
            // 5. Repeat 1 to 3 until the standard input is empty
        }

        // 5. Print the surviving champion
        StdOut.println(champ);
    }
}
