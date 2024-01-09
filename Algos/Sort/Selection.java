public class Selection {
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 0; i < N; i++){
            int min = i;
            for(int j = i+1; j < N; j++){
                if(less(a[j], a[min])){
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static boolean less(Comparable w, Comparable y){
        return w.compareTo(y) < 0;
    }

    public static void exch(Comparable a[], int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a){
        boolean ret = true;
        if(a.length > 1) {
            for(int i = 1; i < a.length; i++){
                if(less(a[i], a[i-1])){
                    ret = false;
                    break;
                }
            }
        }
        return ret;
    }

    public static void shuffle(Comparable[] a){
        int N = a.length;
        for(int i = 0; i < N; i++){
            int r = (int)(Math.random() * i) + 1;
            exch(a, i, r);
        }
    }

    //Tester
    public static void main(String[] args) {
        Integer[] tstArr = new Integer[30];
        for(int i = 0; i < tstArr.length; i++){
            tstArr[i] = i + 1;
        }
        shuffle(tstArr);
        for(Integer i : tstArr){
            System.out.print(i + " ");
        }

        System.out.println("\n");

        sort(tstArr);
        for(Integer i : tstArr){
            System.out.print(i + " ");
        }
    }
    
}
