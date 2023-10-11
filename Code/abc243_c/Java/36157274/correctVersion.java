import java.util.Scanner;
import java.util.TreeMap;

// javac C.java && java C
public class Main {
    static int N;
    static int[] X,Y;
    static char[] S;
    static TreeMap<Integer, Pair> map = new TreeMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        X = new int[N];
        Y = new int[N];
        for(int i=0; i<N; i++) {
            X[i] = sc.nextInt();
            Y[i] = sc.nextInt();
        }
        S = sc.next().toCharArray();
        sc.close();

        for(int i=0; i<N; i++){
            int y = Y[i];
            if(!map.containsKey(y)) map.put(y, new Pair(-1,Integer.MAX_VALUE));
            Pair p = map.get(y);
            if(S[i] == 'L'){
                p.xl = Math.max(p.xl, X[i]);
            }else{
                p.xr = Math.min(p.xr, X[i]);
            }

            if(p.xl > p.xr){
                System.out.println("Yes");
                return;
            }
        }

        System.out.println("No");
    }
    static class Pair {
        int xl;
        int xr;
        public Pair(int a, int b){
            this.xl = a;
            this.xr = b;
        }
    }
}