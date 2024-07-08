import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    
        var sc = new Scanner(System.in);
        
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        var x = new int[m];
        for(int i = 0; i < m; i++){
            x[i] = Integer.parseInt(sc.next());
        }
        
        var bit = new BinaryIndexedTree(3*n);
        for(int i = 0; i < m-1; i++){
            int a = Math.min(x[i], x[i+1]);
            int b = Math.max(x[i], x[i+1]);
            
            if(b-a < n+a-b){
                bit.add(n+a, n+b, (n+a-b) - (b-a));
            }else{
                bit.add(b, n+a, (b-a) - (n+a-b));
                bit.add(n+b, 2*n+a, (b-a) - (n+a-b));
            }
        }
        
        int l = 0;
        for(int i = 0; i < m-1; i++){
            int a = Math.min(x[i], x[i+1]);
            int b = Math.max(x[i], x[i+1]);
            l += Math.min(b-a, n+a-b);
        }
        
        long ans = Long.MAX_VALUE;
        for(int i = n; i < 2*n; i++){
            ans = Math.min(l + bit.sum(i, i+1), ans);
        }
        System.out.println(ans);
    }
    
    static class BinaryIndexedTree {
        int n;
        long[] p, q;
        
        public BinaryIndexedTree(int n){
            this.n = n;
            p = new long[n+1];
            q = new long[n+1];
        }
        void add(int l, int r, long x){
            add(p, l, -x*(l-1));
            add(p, r, x*(r-1));
            add(q, l, x);
            add(q, r, -x);
        }
        void add(long[] bit, int i, long x){
            while(i <= n){
                bit[i] += x;
                i += i & -i;
            }
        }
        long sum(int l, int r){
            return sum(r-1) - sum(l-1);
        }
        long sum(int i){
            return sum(p, i) + sum(q, i)*i;
        }
        long sum(long[] bit, int i){
            long s = 0;
            while(i > 0){
                s += bit[i];
                i -= i & -i;
            }
            return s;
        }
    }
}