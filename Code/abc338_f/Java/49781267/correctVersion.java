import java.util.*;
import java.io.*;
import java.math.*;

public class Main{
    public static void main(String[] args) throws Exception {
        new Solution().SOLVE();
    }
}

class Solution {
    Read sc = new Read();
    public void SOLVE() throws IOException {
        int T = 1;
        while (T-- > 0) {
            solve();
        }
        sc.bw.flush();
        sc.bw.close();
    }

    void solve() throws IOException{
        int n = sc.nextInt();
        int m = sc.nextInt();
        int inf = 0x3f3f3f3f;
        long[][] e = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(e[i], inf);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            long w = sc.nextLong();
            e[u][v] = Math.min(e[u][v], w);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    e[j][k] = Math.min(e[j][k], e[j][i] + e[i][k]);
                }
            }
        }
        long[][] dp = new long[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], inf);
            dp[i][1 << i] = 0;
        }
//        for (int i = 0; i < 1 << n; i++) {
//            for (int j = 0; j < n; j++) {
//                if ((i >> j & 1) == 0) continue;
//                for (int k = 0; k < n; k++) {
//                    dp[k][i | (1 << k)] = Math.min(dp[k][i | 1 << k], dp[j][i] + e[j][k]);
//                }
//            }
//        }
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 0) continue;
                for (int k = 0; k < n; k++) {
                    if (((i >> k) & 1) == 0 || k == j) continue;
                    dp[j][i] = Math.min(dp[j][i], dp[k][i ^ (1 << j)] + e[k][j]);
                }
            }
        }
        long ans = inf;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[i][(1 << n) - 1]);
        }
        sc.println((ans < inf / 2) ? (ans + "") : "No");
    }


    private int gcd (int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

class Pair<P1, P2> {
    P1 a; P2 b;
    public Pair(P1 a, P2 b) {
        this.a = a; this.b = b;
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


