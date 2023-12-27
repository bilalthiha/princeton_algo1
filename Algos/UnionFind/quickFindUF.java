package UnionFind;
public class quickFindUF {
    private int[] id;

    public quickFindUF(int N){
        id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for(int i = 0; i < id.length; i++){
            if(id[i] == pid){
                id[i] = qid;
            }
        }
    }

    public static void main(String[] args) {
        //demo
        quickFindUF quf = new quickFindUF(6);
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
