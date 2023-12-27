package UnionFind;
public class quickUnionUF {
    private int[] id;

    public quickUnionUF(int N){
        id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    private int root(int i){
        while(i != id[i]){
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    public static void main(String[] args) {
        //demo
        quickUnionUF quf = new quickUnionUF(6);
        quf.union(2, 3);
        System.out.println("Connect 2 and 3");
        quf.union(3, 5);
        System.out.println("Connect 3 and 5");
        quf.union(0, 1);
        System.out.println("Connect 0 and 1");

        System.out.print("Are 2 and 5 connected? : ");
        System.out.println(quf.connected(2, 5));

        System.out.print("Are 0 and 1 connected? : ");
        System.out.println(quf.connected(0, 1));

        System.out.print("Are 1 and 5 connected? : ");
        System.out.println(quf.connected(1, 5));
        
    }    
}
