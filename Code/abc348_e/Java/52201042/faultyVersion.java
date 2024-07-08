// package contest.my;

import java.io.*;
import java.util.*;

import static java.lang.Math.*;
public class Main {

    static final int MOD = 1000000007;
    static FastReader se = new FastReader();
    static Reader s = new Reader();
    public static InputStream inputStream = System.in;
    public static OutputStream outputStream = System.out;
    public static PrintWriter out = new PrintWriter(outputStream);

    /*
     ____                _   _             _
     | __ ) _   _  __ _  | | | |_   _ _ __ | |_ ___ _ __
     |  _ \| | | |/ _` | | |_| | | | | '_ \| __/ _ \ '__|
     | |_) | |_| | (_| | |  _  | |_| | | | | ||  __/ |
     |____/ \__,_|\__, | |_| |_|\__,_|_| |_|\__\___|_|
                  |___/
     */


    public static void main(String[] args) throws IOException {
        int Te = 1;
//        int Te = s.nextInt();
        while (Te-- >0){
            solve();
            out.flush();
        }
        out.close();
    }

    static List<Integer>[] edges;
    static int N;
    static long[] weight;
    static long[] child;
    static long[] dis;
    static boolean[] visited;
    static long ret = 0;
    static long sum;
    // code
    static void solve() throws IOException {
        N = s.nextInt();
        edges = new List[N];
        child = new long[N];
        dis = new long[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }
        weight = new long[N];
        for (int i = 0; i < N-1; i++) {
            int v1 = s.nextInt()-1, v2 = s.nextInt()-1;
            edges[v1].add(v2);
            edges[v2].add(v1);
        }

        for (int i = 0; i < N; i++) {
            weight[i] = s.nextLong();
        }
        sum = Arrays.stream(weight).sum();
        dfs(0,0);
        for (int i = 0; i < N; i++) {
            ret += weight[i]*dis[i];
        }
        visited = new boolean[N];
        visited[0] = true;
        dfs2(0,ret);
        out.println(ret);
    }

    static void dfs2(int cur,long score){
        for(int next:edges[cur]){
            if(visited[next]) continue;
            visited[next] = true;
            long newScore = score + sum - 2*child[next];
            ret = min(ret,newScore);
            dfs2(next,newScore);
        }
    }

    // 记录深度及 子树 权值和
    static void dfs(int cur,int dep){
        dis[cur] = dep;
        visited[cur] = true;
        long sum = weight[cur];
        for(int next:edges[cur]){
            if(visited[next])continue;
            dfs(next,dep+1);
            sum += weight[next];
        }
        child[cur] = sum;
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Tri{
        int a;
        int b;
        int c;

        public Tri(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    /**********************/
    static int[][] mv = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
/*********************/

 /************************************************/
    static class FastReader { BufferedReader br;StringTokenizer st;public FastReader() {br = new BufferedReader(new InputStreamReader(System.in));}String next() {while (st == null || !st.hasMoreElements()) try {                    st = new StringTokenizer(br.readLine());                } catch (IOException e) {                    e.printStackTrace();}return st.nextToken();}int nextInt() {return Integer.parseInt(next());}long nextLong() {return Long.parseLong(next());}double nextDouble() {return Double.parseDouble(next());}String nextLine() {String str = "";try {if (st.hasMoreTokens()) str = st.nextToken("\n"); else str = br.readLine();} catch (IOException e) {e.printStackTrace();}return str;}public int[] readIntArray(int n, int s, int e) {int[] ar = new int[n];for (int i = s; i < e; ++i) ar[i] = se.nextInt();return ar;}public long[] readIntArrayLONG(int n, int s, int e) {long[] ar = new long[n];for (int i = s; i < e; ++i) ar[i] = se.nextLong();return ar;}int[][] next2DInt(int n, int m, int s, int e) {int[][] arr = new int[n][];for (int i = s; i < e; i++) arr[i] = se.readIntArray(m, 0, m);return arr;}long[][] next2DLong(int n, int m, int s, int e) {long[][] arr = new long[n][];for (int i = s; i < e; i++) arr[i] = se.readIntArrayLONG(m, 0, m);return arr;}List<Long> asLongList() throws IOException {List<Long> list = new ArrayList<>();String s = se.next();String[] str = s.split(" ");for (String string : str) list.add(Long.parseLong(string));return list;}List<Integer> asIntList() throws IOException {List<Integer> list = new ArrayList<>();String s = se.next();String[] str = s.split(" ");for (String string : str) list.add(Integer.parseInt(string));return list;}}static class Reader { final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;public Reader() {din = new DataInputStream(System.in);buffer = new byte[BUFFER_SIZE];bufferPointer = bytesRead = 0;}public Reader(String file_name) throws IOException {din = new DataInputStream(new FileInputStream(file_name));buffer = new byte[BUFFER_SIZE];bufferPointer = bytesRead = 0;}public String readLine() throws IOException {byte[] buf = new byte[64];int cnt = 0, c;while ((c = read()) != -1) {if (c == '\n') break;buf[cnt++] = (byte) c;}return new String(buf, 0, cnt);}public int nextInt() throws IOException {int ret = 0;byte c = read();while (c <= ' ') c = read();boolean neg = (c == '-');if (neg) c = read();do ret = ret * 10 + c - '0'; while ((c = read()) >= '0' && c <= '9');if (neg) return -ret;return ret;}public long nextLong() throws IOException {long ret = 0;byte c = read();while (c <= ' ') c = read();boolean neg = (c == '-');if (neg) c = read();do ret = ret * 10L + c - '0'; while ((c = read()) >= '0' && c <= '9');if (neg) return -ret;return ret;}public double nextDouble() throws IOException {double ret = 0, div = 1;byte c = read();while (c <= ' ') c = read();boolean neg = (c == '-');if (neg) c = read();do ret = ret * 10 + c - '0'; while ((c = read()) >= '0' && c <= '9');if (c == '.') while ((c = read()) >= '0' && c <= '9') ret += (c - '0') / (div *= 10);if (neg) return -ret;return ret;}private void fillBuffer() throws IOException {bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);if (bytesRead == -1) buffer[0] = -1;}private byte read() throws IOException {if (bufferPointer == bytesRead) fillBuffer();return buffer[bufferPointer++];}public char[] next() throws IOException {StringBuilder sb = new StringBuilder();byte c;while ((c = read()) <= ' ') ;do sb.append((char) c); while ((c = read()) > ' ');return sb.toString().toCharArray();}public int nextInt2() throws IOException {int ret = 0;byte c = read();while (c <= ' ') c = read();boolean neg = (c == '-');if (neg) c = read();do ret = ret * 10 + c - '0'; while ((c = read()) >= '0' && c <= '9');if (neg) return -ret;return ret;}public void close() throws IOException {if (din == null) return;din.close();}public int[] readIntArray(int n, int se, int e) throws IOException {int[] ar = new int[n];for (int i = se; i < e; ++i) ar[i] = s.nextInt();return ar;}public long[] readIntArrayLONG(int n, int se, int e) throws IOException {long[] ar = new long[n];for (int i = se; i < e; ++i) ar[i] = s.nextLong();return ar;}int[][] next2DInt(int n, int m, int se, int e) throws IOException {int[][] arr = new int[n][];for (int i = se; i < e; i++) arr[i] = s.readIntArray(m, 0, m);return arr;}long[][] next2DLong(int n, int m, int se, int e) throws IOException {long[][] arr = new long[n][];for (int i = se; i < e; i++) arr[i] = s.readIntArrayLONG(m, 0, m);return arr;}List<Long> asLongList(int n) throws IOException {List<Long> list = new ArrayList<>(n);for (int i = 0; i < n; i++) list.add(s.nextLong());return list;}List<Integer> asIntList(int n) throws IOException {List<Integer> list = new ArrayList<>(n);for (int i = 0; i < n; i++) list.add(s.nextInt());return list;}}

    public static long gcd(long a, long b) {        if (b == 0) return a;        return gcd(b, a % b);    }    public static long LCM(long u, long v) {        return (u / gcd(u, v)) * v;    }    public static void debugL(long[] a, long s) {        for (long i = s; i < a.length; i++) {            System.out.print(a[(int) i] + " ");        }    }    public static void debugI(int[] a, long s) {        for (long i = s; i < a.length; i++) {            System.out.print(a[(int) i] + " ");        }    }    static int max3(int a,int b,int c){        return max(max(a,b),c);    }    static long max3(long a,long b,long c){        return max(max(a,b),c);    }    static int min3(int a,int b,int c){        return min(min(a,b),c);    }    static long min3(long a,long b,long c){        return min(min(a,b),c);    }    public static long sumofarray(int[] arr) {        long n = arr.length, sum = 0;        for (long i = 0; i < n; i++) sum += arr[(int) i];        return sum;    }    public static long power(long x, long y, long p) {        long res = 1;        x = x % p;        while (y > 0) {            if (y % 2 == 1) res = (res * x) % p;            y = y >> 1;            x = (x * x) % p;        }        return res;    }    public static String numToBinary(long num) {        String str = "";        while (num > 0) {            if ((num & 1) == 1) str += '1'; else str += '0';            num >>= 1;        }        return str;    }


    public static void println(long c) {out.println(c);}public static void print(long c) {out.print(c);}public static void print(int c) {out.print(c);}public static void println(int x) {out.println(x);}public static void print(String s) {out.print(s);}public static void println(String s) {out.println(s);}public static void println(boolean b) {out.println(b);}public static void println(char x) {out.println(x);}public static void reverseArray(int[] a) {int n = a.length, arr[] = new int[n];for (int i = 0; i < n; i++) arr[i] = a[n - i - 1]; for (int i = 0; i < n; i++) a[i] = arr[i]; }public static void reverseArray(long[] a) {int n = a.length; long arr[] = new long[n];for (int i = 0; i < n; i++) arr[i] = a[n - i - 1]; for (int i = 0; i < n; i++) a[i] = arr[i]; }


}
