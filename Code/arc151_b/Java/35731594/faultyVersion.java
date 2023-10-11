import javax.print.DocFlavor;
import java.io.*;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    static long mod = 998244353;

    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(System.out);
        //StringBuilder ans = new StringBuilder();
        int n = nextInt();
        long m = nextLong();
        long[] p = new long[n + 1];
        p[0] = 1;
        p[1] = m % mod;
        for (int i = 2; i < n + 1; i++) {
            p[i] = (p[i - 1] * m) % mod;
        }
        long ans = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        HashMap<Integer, Integer> mpp = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int k = nextInt();
            mp.put(i, k);
            mpp.put(k, i);

        }
        long cur = 1;
        long k = (m * (m - 1) * quick(2, mod - 2))%mod;
        for (int i = 1, re = n; i <= n; i++) {
            if(mp.get(i) == i) {
                cur = (cur * m) % mod;
                re--;
                continue;
            }
            int index = mpp.get(i);
            re--;
            if(re > 0){
                ans = (ans + ((cur * k) % mod) * p[re - 1] % mod) % mod;
            }else{
                ans = (ans + (cur * k) % mod) % mod;
            }
            //System.out.println(ans);
            mp.put(index, mp.get(i));
            mpp.put(mp.get(i), index);
        }
        //System.out.println(Arrays.toString(p));
        pw.println(ans);
        pw.flush();
    }
    public static long quick(long a, long b){
        long ans = 1;
        while (b > 0){
            if((b & 1) == 1){
                ans = (ans * a) % mod;
            }
            a = (a * a) % mod;
            b >>= 1;
        }
        return ans;
    }



    static class unionFind{
        int num;
        int[] parent;
        public unionFind(int k){
            num = k;
            parent = new int[k];
            for (int i = 0; i < k; i++) {
                parent[i] = i;
            }
        }
        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int px = find(x);
            int py = find(y);
            if(px != py){
                parent[py] = px;
                num--;
            }
        }
    }

    public static void swap(int[] nums, int i , int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static int hash(int x, int y){
        return x * 10000 + y;
    }



    // <Fast Scanner> START -------------------------------------------------
    static InputStream in = System.in;
    static  byte[] buffer = new byte[1024];
    static int length = 0, p = 0;
    public static boolean hasNextByte () {
        if (p < length) return true;
        else {
            p = 0;
            try {length = in.read(buffer);}
            catch (Exception e) {e.printStackTrace();}
            if (length == 0) return false;
        }
        return true;
    }
    public static int readByte () {
        if (hasNextByte() == true) return buffer[p++];
        return -1;
    }
    public static boolean isPrintable (int n) {return 33<=n&&n<=126;}
    public static void skip () {
        while (hasNextByte() && !isPrintable(buffer[p])) p++;
    }
    public static boolean hasNext () {skip(); return hasNextByte();}
    public static String next () {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int temp = readByte();
        while (isPrintable(temp)) {
            sb.appendCodePoint(temp);
            temp = readByte();
        }
        return sb.toString();
    }
    public static int nextInt () {return Math.toIntExact(nextLong());}
    public static int[] nextInts (int n) {
        int[] ar = new int[n];
        for (int i=0; i<n; i++) ar[i] = nextInt();
        return ar;
    }
    public static long nextLong () {
        if (!hasNext()) throw new NoSuchElementException();
        boolean minus = false;
        int temp = readByte();
        if (temp == '-') {
            minus = true;
            temp = readByte();
        }
        if (temp<'0' || '9'<temp) throw new NumberFormatException();
        long n = 0;
        while (isPrintable(temp)) {
            if ('0'<=temp && temp<='9') {
                n *= 10;
                n += temp - '0';
            }
            temp = readByte();
        }
        return minus? -n : n;
    }
    // <Fast Scanner> END ----------------------------------------------------------
}
