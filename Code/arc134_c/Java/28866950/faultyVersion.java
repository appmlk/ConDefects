import java.lang.*;
import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    static long c(int n,int k,long[] inv,long mod) {
        long t = 1;
        for(int i=1;i<k;i++) {
            t = t*(n+i) % mod;
        }
        t = t*inv[k-1] % mod;
        return t;
    }
    
    static long quickM(long d,long m,long mod) {
        long res = 1L;
        long q = 1L;
        long t = d;
        while(q<=m) {
            if((m&q)>0) {
                res = res * t % mod;
            }
            q = q<<1;
            t = t * t % mod;
        }
        return res;
    }
    
    static void deal(int n,int k,int[] arr) {
        int sum = arr[0];
        for(int i=1;i<n;i++) {
            sum -= arr[i];
            if(sum<k) {
                out.println(0);
                return;
            }
        }
        long res = 1;
        long mod = 998244353;
        long[] inv = new long[k];
        long t = 1;
        for(int i=1;i<k;i++) {
            t = t * i % mod;
            inv[i] = quickM(t, mod-2, mod);
        }
        for(int i=1;i<n;i++) {
            res = res * c(arr[i],k,inv,mod) % mod;
        }
        res = res*c(sum-k,k,inv,mod) % mod;
        out.println(res);
    }

    
	public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();
        }
        deal(n,k,arr);
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