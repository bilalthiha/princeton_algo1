package UnionFind;
public class weightedQUF {
    private int[] id;
    private int[] sz;

    public weightedQUF(int N){
        id = new int[N];
        sz = new int[N];

        for(int i = 0; i < N; i++){
            id[i] = i; //init all nodes as self-rooted
            sz[i] = 0; //int size of all nodes (roots)
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
        if(sz[i] < sz[j]){
            //consider weight before union
            //smaller tree is connected under the larger tree
            id[i] = j;
            sz[j] += sz[i];
        }
        else{
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    public static void main(String[] args) {
        //demo
        weightedQUF quf = new weightedQUF(6);
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
