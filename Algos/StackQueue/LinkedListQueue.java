import java.util.Iterator;

public class LinkedListQueue<Item> implements Iterable<Item>{
    private Node first, last;
    private int size;

    private class Node{
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){            
            return current!= null;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }  
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    public LinkedListQueue(){
        first = null;
        last = null;
        size = 0;
    }


    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size;
    }

    public void enqueue(Item i){
        Node oldLast = last;

        last = new Node();
        last.item = i;
        last.next = null;

        if(isEmpty()){
            first = last;
        }
        else{
            oldLast.next = last;
        }
        size++;        
    }

    public Item dequeue(){
        Item i = first.item;
        first = first.next;

        if(isEmpty()){
            last = null;
        }
        
        size--;
        return i;
    }

    //Tester
    public static void main(String[] args) {
        LinkedListQueue<Integer> llq = new LinkedListQueue<>();
        Integer temp = 0;
        System.out.println("[Output]The queue size at very beginning: " + llq.size());

        System.out.println("Enqueue numbers 5 first, 6, 7 and 8 last");
        for(int i = 5; i < 9; i++){
            llq.enqueue(i);
        }

        Iterator<Integer> it = llq.iterator();
        System.out.println("[Output] Queued items are ");
        while(it.hasNext()){
            Integer i = it.next();
            System.out.println(i);
        }
        System.out.println("[Output] Queue size " + llq.size());

        System.out.println(); //format output with new line

        System.out.println("Dequeue 3 items and expect the latest dequeued item to be 7");
        for(int i = 0; i < 3; i++){
            temp = llq.dequeue();
        }
        System.err.println("[Output] After dequeuing 3 times, the latest dequeued item is " + temp);
        System.out.println("[Output] Queue size " + llq.size());

        System.out.println(); //format output with new line

        System.out.println("Dequeue the last item and expect the latest dequeued item to be 5");
        temp = llq.dequeue();
        System.err.println("[Output] After dequeuing the last item, the latest dequeued item is " + temp);
        System.out.println("[Output] Queue size " + llq.size());

        System.out.println(); //format output with new line
    }
}
