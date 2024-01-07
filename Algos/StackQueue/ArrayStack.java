import java.util.Iterator;

public class ArrayStack <Item> implements Iterable<Item> {
    private Item[] itemArr;
    private int N; //stack size

    public ArrayStack(){
        itemArr = (Item[]) new Object[1];
        N = 0; 
    }

    private void resize(int capacity){
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < itemArr.length; i++){
            copy[i] = itemArr[i];
        }
        itemArr = copy;
    }

    private class ArrayIterator implements Iterator<Item>{
        private int current = N;
        public boolean hasNext(){
            return current > 0;
        }
        public Item next(){
            return itemArr[--current];
        }
    }

    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }    

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        if(N == itemArr.length){
            resize(2 * itemArr.length);
        }
        itemArr[N++] = item;        
    }

    public Item pop(){
        if(N == 0){
            throw new NullPointerException("Cannot pop an empty stack!");
        }
        Item i = itemArr[--N];
        itemArr[N] = null;
        if(N > 0 && N == itemArr.length/4){
            resize(itemArr.length/2);
        }
        return i;
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
        Iterator<String> it = st.iterator();
        while(it.hasNext()){
            String s = it.next();
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
