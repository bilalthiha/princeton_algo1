import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* *****************************************************************************
 *  Name: Bilal Ansari
 *  Date: 28 Dec 2023
 *  Description: Implementation of Deque API
 **************************************************************************** */
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("No more elements to iterate!");
            }
            Item i = current.item;
            current = current.next;
            return i;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "Remove method is unsupported and unavailable!");
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null || last == null);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldFirst = first;

        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item");
        }

        first = new Node();
        first.item = item;
        first.prev = null;

        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node oldLast = last;

        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item");
        }

        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item i;

        if (size == 0) {
            throw new NoSuchElementException("Cannot remove item from an empty deque!");
        }

        i = first.item;
        first = first.next;

        size--;

        if (isEmpty()) {
            last = null;
        }
        else {
            first.prev = null;
        }
        return i;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item i;

        if (size == 0) {
            throw new NoSuchElementException("Cannot remove item from an empty deque!");
        }

        i = last.item;
        last = last.prev;
        size--;

        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }
        return i;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        // run test cases
        runTestCases();
    }

    //============= private helper methods ==================
    private static void runTestCases() {
        String temp;
        // create constructor
        Deque<String> dq = new Deque<>();

        // verify empty deque
        dq.printTestStep("At the beginning. Nothing is done.");
        dq.printTestItrSizeOut(dq);
        dq.printTestStep("Is the Deque empty?");
        StdOut.println("[Out]: " + dq.isEmpty());

        // verify a single first add and a single first removal
        dq.printTestStep("Add P1 to the first of empty deque");
        dq.addFirst("P1");
        dq.printTestItrSizeOut(dq);

        dq.printTestStep("Remove the first item from deque");
        temp = dq.removeFirst();
        StdOut.println("[Out]: " + temp);
        dq.printTestItrSizeOut(dq);

        // verify a single first add and a single last removal
        dq.printTestStep("Add P1 to the first of empty deque");
        dq.addFirst("P1");
        dq.printTestItrSizeOut(dq);

        dq.printTestStep("Remove the last item from deque");
        temp = dq.removeLast();
        StdOut.println("[Out]: " + temp);
        dq.printTestItrSizeOut(dq);

        // verify a single last add and a single last removal
        dq.printTestStep("Add P1 to the last of empty deque");
        dq.addLast("P1");
        dq.printTestItrSizeOut(dq);

        dq.printTestStep("Remove the last item from deque");
        temp = dq.removeLast();
        StdOut.println("[Out]: " + temp);
        dq.printTestItrSizeOut(dq);

        // verify a single last add and a single first removal
        dq.printTestStep("Add P1 to the last of empty deque");
        dq.addLast("P1");
        dq.printTestItrSizeOut(dq);

        dq.printTestStep("Remove the first item from deque");
        temp = dq.removeFirst();
        StdOut.println("[Out]: " + temp);
        dq.printTestItrSizeOut(dq);

        // verify combination of first and last adds
        dq.printTestStep("To an empty deque, add P1, P2 to first and P3, P4, P5 to last");
        dq.addFirst("P1");
        dq.addFirst("P2");
        dq.addLast("P3");
        dq.addLast("P4");
        dq.addLast("P5");
        dq.printTestItrSizeOut(dq);

        // verify combination of first and last removals
        dq.printTestStep("From the existing deque, remove 2 items from head and 1 item from tail");
        StdOut.println("[Out]: First item removed from head is " + dq.removeFirst());
        StdOut.println("[Out]: Second item removed from head is " + dq.removeFirst());
        StdOut.println("[Out]: First item removed from tail is " + dq.removeLast());
        dq.printTestItrSizeOut(dq);
    }

    private void printTestStep(String desc) {
        StdOut.println("[Tst]: " + desc);
    }

    private void printTestItrSizeOut(Deque<String> deq) {
        // Iterator<String> it = deq.iterator();
        StdOut.print("[Out]: Iterated items: ");
        // while (it.hasNext()) {
        //     String s = it.next();
        //     StdOut.print(s + " ");
        // }
        for (String s : deq) {
            StdOut.print(s + " ");
        }
        StdOut.println("\n[Out]: Deque size is " + deq.size() + "\n");
    }
}
