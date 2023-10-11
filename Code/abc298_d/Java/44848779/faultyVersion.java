import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Solution().solve();
    }
}



class Solution {
    Read sc = new Read();
    public void solve() throws IOException {
        int T = 1;
        int mod = 998244353;
        long ivn10 = qmi(10, mod - 2, mod);
        while (T -- > 0) {
            int q = sc.nextInt();
            long x = 1;
            Deque<Long> queue = new ArrayDeque<>();
            queue.offerFirst(x);
            long mul = 1;
            for (int i = 0; i < q; i++) {
                int type = sc.nextInt();
                if (type == 1) {
                    mul *= 10;
                    mul %= mod;
                    int b = sc.nextInt();
                    queue.offerLast((long)b);
                    x *= 10;
                    x += b;
                    x %= mod;
                }else if (type == 2) {
                    long t = queue.pollFirst();
                    x = (x - (t * mul % mod) + mod) % mod;
                    mul *= ivn10;
                    mul %= mod;
                }else {
                    sc.println(x);
                }
            }
        }
        sc.bw.flush();
        sc.bw.close();
    }

    private long qmi(int a, int p, int mod) {
        long res = 1;
        while (p > 0) {
            if ((p & 1) == 1) res = res * a % mod;
            p >>= 1;
            a = (int)((long)a * a) % mod;
        }
        return res;
    }

}





class Read{
    BufferedReader bf;
    StringTokenizer st;
    BufferedWriter bw;
    public Read(){
        bf=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer("");
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public String nextLine() throws IOException{
        return bf.readLine();
    }
    public String next() throws IOException{
        while(!st.hasMoreTokens()){
            st=new StringTokenizer(bf.readLine());
        }
        return st.nextToken();
    }
    public char nextChar() throws IOException{
        //确定下一个token只有一个字符的时候再用
        return next().charAt(0);
    }
    public int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    public long nextLong() throws IOException{
        return Long.parseLong(next());
    }
    public double nextDouble() throws IOException{
        return Double.parseDouble(next());
    }
    public float nextFloat() throws IOException{
        return Float.parseFloat(next());
    }
    public byte nextByte() throws IOException{
        return Byte.parseByte(next());
    }
    public short nextShort() throws IOException{
        return Short.parseShort(next());
    }
    public BigInteger nextBigInteger() throws IOException{
        return new BigInteger(next());
    }
    public void println() throws IOException {
        bw.newLine();
    }
    public void println(int[] arr) throws IOException{
        for (int value : arr) {
            bw.write(value + " ");
        }
        println();
    }
    public void println(int l, int r, int[] arr) throws IOException{
        for (int i = l; i <= r; i ++) {
            bw.write(arr[i] + " ");
        }
        println();
    }
    public void println(int a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
    }
    public void print(int a) throws IOException{
        bw.write(String.valueOf(a));
    }
    public void println(String a) throws IOException{
        bw.write(a);
        bw.newLine();
    }
    public void print(String a) throws IOException{
        bw.write(a);
    }
    public void println(long a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
    }
    public void print(long a) throws IOException{
        bw.write(String.valueOf(a));
    }
    public void println(double a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
    }
    public void print(double a) throws IOException{
        bw.write(String.valueOf(a));
    }
    public void print(char a) throws IOException{
        bw.write(String.valueOf(a));
    }
    public void println(char a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
    }
}

