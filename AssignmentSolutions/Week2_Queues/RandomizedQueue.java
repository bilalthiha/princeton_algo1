/* *****************************************************************************
 *  Name: Bilal Ansari
 *  Date: 29/12/2023
 *  Description: Implementation of Randomized Queue API
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rqArr;
    private int size;

    private class RandQueIterator implements Iterator<Item> {
        private Item[] itArr;
        private int itSize;

        private RandQueIterator() {
            itSize = size;
            itArr = (Item[]) new Object[itSize];
            // copy randomized queue to iterator array
            for (int i = 0; i < itSize; i++) {
                itArr[i] = rqArr[i];
            }
        }

        public boolean hasNext() {
            return itSize != 0;
        }

        public Item next() {
            Item item;

            if (!hasNext()) {
                throw new NoSuchElementException("Cannot iterarate further after fully iterated!");
            }

            item = itArr[--itSize];
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "Remove method is unsupported and unavailable!");
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        rqArr = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot enqueue a null item!");
        }
        // double the array size if the current array is filled up
        if (size == rqArr.length) {
            resize(2 * rqArr.length);
        }
        rqArr[size++] = item;
        swapItems(); // randomize the queue position of the newly added item
    }

    // remove and return a random item
    public Item dequeue() {
        Item item;

        if (isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue an empty queue!");
        }

        item = rqArr[--size]; // item is already randomized when it's enqueued
        // halve the array size if the size is quarter of the array size
        if ((size > 0) && (size == (rqArr.length / 4))) {
            resize(rqArr.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int rIdx;

        if (isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue an empty queue!");
        }

        rIdx = StdRandom.uniformInt(size);
        return rqArr[rIdx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandQueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        runTestCases();
    }

    //========== private (internal) methods ==========
    private void swapItems() {
        // swap the latest added item with a random item in the array
        Item temp;
        int lIdx = size - 1;
        int rIdx = StdRandom.uniformInt(size);
        temp = rqArr[lIdx];
        rqArr[lIdx] = rqArr[rIdx];
        rqArr[rIdx] = temp;
    }

    private void resize(int newSize) {
        Item[] newArr = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArr[i] = rqArr[i];
        }
        rqArr = newArr;
    }

    private static void runTestCases() {
        Integer temp;
        int loop;
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        // Test init
        q.printTestStep("At the beginning. No action is done");
        q.printTestItrSizeOut(q);
        q.printTestStep("Is queue empty?");
        StdOut.println("[Out]: " + q.isEmpty());

        // Enqueue one item and dequeue one
        q.printTestStep("Enqueue an item to an empty queue");
        q.enqueue(1);
        q.printTestItrSizeOut(q);
        q.printTestStep("Dequeue the item and verify the que is empty");
        temp = q.dequeue();
        StdOut.println("[Out]: " + temp);
        q.printTestItrSizeOut(q);
        q.printTestStep("Is queue empty?");
        StdOut.println("[Out]: " + q.isEmpty());

        // Enqueue 15 items and dequeue 6
        q.printTestStep("Add 15 items to an empty queue");
        loop = 15;
        for (int i = 0; i < loop; i++) {
            q.enqueue(i);
        }
        q.printTestItrSizeOut(q);
        q.printTestStep("Dequeue 6 items randomly");
        StdOut.print("[Out]: ");
        loop = 6;
        for (int i = 0; i < loop; i++) {
            temp = q.dequeue();
            StdOut.print(temp + " ");
        }
        StdOut.println();
        q.printTestItrSizeOut(q);
        q.printTestStep("Is queue empty?");
        StdOut.println("[Out]: " + q.isEmpty());

        // Sample items 20 times and verify size is not affected
        q.printTestStep("Sample items 20 times");
        StdOut.print("[Out]: ");
        loop = 20;
        for (int i = 0; i < loop; i++) {
            temp = q.sample();
            StdOut.print(temp + " ");
        }
        StdOut.println();
        StdOut.println("[Out]: queue size ");
        StdOut.println("[Out]: Queue size is " + q.size());
        StdOut.println();

        // Sample items 20 times more and check with previous sample result to verify randomness
        q.printTestStep(
                "Sample items 20 times more and check with previous sample result to verify randomness");
        StdOut.print("[Out]: ");
        for (int i = 0; i < loop; i++) {
            temp = q.sample();
            StdOut.print(temp + " ");
        }
        StdOut.println();
        StdOut.println("[Out]: queue size ");
        StdOut.println("[Out]: Queue size is " + q.size());
    }

    private void printTestStep(String desc) {
        StdOut.println("[Tst]: " + desc);
    }

    private void printTestItrSizeOut(RandomizedQueue<Integer> q) {
        StdOut.print("[Out]: Iterated (random) items: ");
        for (Integer i : q) {
            StdOut.print(i + " ");
        }
        StdOut.println("\n[Out]: Queue size is " + q.size() + "\n");
    }
}
