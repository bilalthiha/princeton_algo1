public class Shell {
    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        //determine h (increment) using 3x+1
        while(h < N/3){
            h = (3*h) + 1;
        }

        while(h >= 1){
            for(int i = h; i < N; i++){
                for(int j = i; j >= h && less(a[j], a[j-h]); j-=h){
                    exch(a, j, j-h);
                }
            }
            h = h/3; //move to the next increment, i.e, next h-sort
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
