import java.lang.*;
import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    static int mod = 998244353;
    static int lowbit(int x) {
        return x&(-x);
    }
    
    static void update(int x,int v,int[] a) {
        int n = a.length;
        while(x<n) {
            a[x] = ((a[x]+v) % mod +mod) % mod;
            x += lowbit(x);
        }
    }
    
    static int getsum(int m,int[] arr) {
        int res = 0;
        while(m>0) {
            res += arr[m];
            res %= mod;
            m -= lowbit(m);
        }
        return (res+mod) % mod;
    }
    
    public static void deal(int n,int[] a,int q,int[][] qu) {
        int[] b = new int[n+1];
        int[] c = new int[n+1];
        int[] d = new int[n+1];
        for(int i=1;i<=n;i++) {
            update(i,a[i],b);
            long t = a[i];
            t = t * i % mod;
            update(i,(int)t,c);
            t = t * i % mod;
            update(i,(int)t,d);
        }
        long inv2 = mod-mod/2*1%mod;
        for(int i=0;i<q;i++) {
            if(qu[i][0] == 1) {
                long diff = ((qu[i][2]-a[qu[i][1]]) % mod+mod) % mod;
                update(qu[i][1], (int)diff, b);
                diff = diff * qu[i][1] % mod;
                update(qu[i][1], (int)diff, c);
                diff = diff * qu[i][1] % mod;
                update(qu[i][1], (int)diff, d);
                a[qu[i][1]] = qu[i][2];
            } else {
                long t1 = getsum(qu[i][1],b);
                long t2 = getsum(qu[i][1],c);
                long t3 = getsum(qu[i][1],d);
                long t = (t3-t2*(2L*qu[i][1]+3) % mod +t1*(qu[i][1]+1) % mod *(qu[i][1]+2) % mod) %mod;
                t *= inv2;
                t = (t % mod +mod) % mod;
                out.println(t);
            }
        }
    }
    
	public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] a = new int[n+1];
        int[][] b = new int[q][3];
        for(int i=0;i<n;i++) a[i+1] = sc.nextInt();
        for(int i=0;i<q;i++) {
            b[i][0] = sc.nextInt();
            b[i][1] = sc.nextInt();
            if(b[i][0] == 1) {
                b[i][2] = sc.nextInt();
            }
        }
        deal(n,a,q,b);
        out.close();
    }
    
    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;
    
    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;
        
        public MyScanner() {
                br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next() {
                while (st == null || !st.hasMoreElements()) {
                        try {
                                st = new StringTokenizer(br.readLine());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
                return st.nextToken();
        }
        
        int nextInt() {
                return Integer.parseInt(next());
        }
        
        long nextLong() {
                return Long.parseLong(next());
        }
        
        double nextDouble() {
                return Double.parseDouble(next());
        }
        
        String nextLine(){
                String str = "";
    try {
            str = br.readLine();
    } catch (IOException e) {
            e.printStackTrace();
    }
    return str;
        }
        
    }
}