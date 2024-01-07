import java.util.Iterator;

public class LinkedListStack<Item> implements Iterable<Item>{
    private Node first = null;
    private int size = 0;
    
    private class Node{
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator <Item>{
        private Node current = first;

        public boolean hasNext(){
            return current != null;
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

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size;
    }

    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public Item pop(){
        if(size == 0){
            throw new NullPointerException("Cannot pop an empty stack!");
        }
        Item item = first.item;
        first = first.next;
        size--;
        return item;       
    }
    
    //Test code
    public static void main(String[] args) {
        String out;
        LinkedListStack<String> st = new LinkedListStack<>();        
        
        System.out.println("Push a book, then a CD and finally a card");
        st.push("book");
        st.push("CD");
        st.push("card");

        System.out.println("Stuff in the stack..");
        // Iterator<String> it = st.iterator();
        // while(it.hasNext()){
        //     String s = it.next();
        //     System.out.println(s);
        // }
        for(String s: st){
            System.out.println(s);
        }
        System.out.println("Stack size is " + st.size());
        System.out.println();

        System.out.println("Pop twice and expect CD at second pop. Stack size should be 1");
        out = st.pop();
        out = st.pop();
        System.out.println("After popping twice, last popped item: " + out);
        System.out.println("Stack size is " + st.size());      

        System.out.println();

        System.out.println("Push a chocolate bar");
        st.push("choco bar");
        System.out.println("Pop twice and expect book at second pop. Stack size should be 0");
        out = st.pop();
        out = st.pop();
        System.out.println("After popping twice, last popped item: " + out);
        System.out.println("Stack size is " + st.size());

        System.out.println();
    }
}