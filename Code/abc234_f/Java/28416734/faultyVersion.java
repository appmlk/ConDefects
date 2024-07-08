import java.io.*;
import java.util.*;

import static java.lang.Math.abs;


public class Main {
    long pow_mod(long x, long i, long mod){
	    if(i == 0){
	        return 1;
	    }
	    long temp = pow_mod(x, i >> 1, mod);
	    temp = temp * temp % mod;
	    if((i & 1 )!= 0){
	        temp = temp * x % mod;
	    }
	    return temp;
	}
    long M = 998244353;
    boolean multiCase = false;
    long [] A = new long[5001];
    long [] Ar = new long[5001];
    private void preparation() {
        A[0] = 1;
        Ar[0] = 1;
        for(int i = 1; i <= 5000; i++){
            A[i] = A[i-1] * i % M;
            Ar[i] = pow_mod(A[i], M-2, M);
        }

    }
    int [] cnt = new int[27];
    long [][] dp = new long[26][5001];
    long [] sufS;
    long dfs(int cur, int remain){
        if(remain == 0){
            return 1;
        }
        if(cur > 25 || remain < 0){
            return 0;
        }
        if(dp[cur][remain] != -1){
            return dp[cur][remain];
        }
        // if(cur == 25){
            // return Ar[min(cnt[cur], remain)];
        // }
        long res = 0;
        for(int i = 0; i <= cnt[cur]; i++){
            // print("cur = %d, i = %d%n", cur, i);
            res = res + (long)dfs(cur + 1, remain - i) * Ar[i] % M;
            // print("cur = %d, i = %d, res = %d %n", cur, i, res);
        }
        dp[cur][remain] = res;
        // print("cur = %d, r = %d res = %d%n", cur,remain, res);
        return res;
    }

    private void solve() throws Exception {
        rf();
        char [] s = rs2c();
        int n = s.length;
        for(char c : s){
            cnt[c - 'a'] ++;
        }
        fill(dp, -1);
        sufS = suffix(cnt);
        long ans = 0;
        // addAns(n);
        for(int i = 1; i <= n; i++){
            ans = add(ans, A[i] * dfs(0,i) % M);
            // addAns(ans);
        }
        addAns(ans);
    }

    private void clear() {

    }

    private void run() throws Exception {

        int T = 1;
        if (multiCase) {
            rf();
            T = ri();
        }

        preparation();
        while (T-- > 0) {
            solve();
            if (T != 0) {
                clear();
            }
        }
        printAns();
    }

    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    StringBuilder sb = new StringBuilder();
    BufferedReader infile = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer strT;

    public boolean ADD_ANSWER_FUNCTION;

    private void addAns(int a) {
        sb.append(a).append("\n");
    }

    private void addAns(long a) {
        sb.append(a).append("\n");
    }

    private void addAns(String s) {
        sb.append(s).append("\n");
    }

    private void addAns(int[] A, int x) {
        sb.append(A[0]);
        int t = min(A.length, x);
        for (int i = 1; i < t; i++) {
            sb.append(" ").append(A[i]);
        }
        sb.append("\n");
    }

    private void addAns(long[] A, int x) {
        sb.append(A[0]);
        int t = min(A.length, x);
        for (int i = 1; i < t; i++) {
            sb.append(" ").append(A[i]);
        }
        sb.append("\n");
    }

    private void addYes() {
        sb.append("Yes\n");
    }

    private void addNo() {
        sb.append("No\n");
    }

    private void rf() throws IOException {
        strT = new StringTokenizer(infile.readLine());
    }

    private int ri() {
        return Integer.parseInt(strT.nextToken());
    }

    private long rl() {
        return Long.parseLong(strT.nextToken());
    }

    private char[] rs2c() {
        return strT.nextToken().toCharArray();
    }

    private String rs() {
        return strT.nextToken();
    }


    private int[] readArr(int N, int start_pos, int suffix_num, int suffix_value) {
        int[] arr = new int[N + start_pos + suffix_num];
        for (int i = 0; i < N; i++) {
            arr[i + start_pos] = Integer.parseInt(strT.nextToken());
        }
        for(int i = N + start_pos; i < arr.length; i++){
            arr[i] = suffix_value;
        }
        return arr;
    }

    private long[] readArr2(int N, int start_pos, int suffix_num, long suffix_value) {
        long[] arr = new long[N + start_pos + suffix_num];
        for (int i = 0; i < N; i++) {
            arr[i + start_pos] = Long.parseLong(strT.nextToken());
        }
        for(int i = N + start_pos; i < arr.length; i++){
            arr[i] = suffix_value;
        }
        return arr;
    }

    public boolean PRINT_FUNCTION;

    private void print(String format, Object... args) {
        System.out.printf((format), args);
    }

    // for interactive problem. flush output after print. need with tail %n if print Line Separator
    private void iprint(String format, Object... args) {
        System.out.printf((format), args);
        System.out.flush();
    }

    private void print(int[] arr, int x) {
        //for debugging only
        for (int i = 0; i < min(arr.length, x); i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private void print(long[] arr, int x) {
        //for debugging only
        for (int i = 0; i < min(arr.length, x); i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private void printAns() {
        System.out.print(sb);
    }

    public boolean ADD_WITH_MOD_FUNCTION;

    private int add(int a, int b) {
        return (int) add((long) a, (long) b);
    }

    private int add(int a, int b, int c) {
        return (int) add((long) a, (long) b, (long) c);
    }

    private int add(int a, int b, int c, int d) {
        return (int) add((long) a, (long) b, (long) c, (long) d);
    }

    private long add(long a, long b) {
        return (a % M + b % M) % M;
    }

    private long add(long a, long b, long c) {
        return (a % M + add(b, c)) % M;
    }

    private long add(long a, long b, long c, long d) {
        return (a % M + add(b, c, d)) % M;
    }

    private long mod(long x) {
        return (x % M + M) % M;
    }

    private int mod(int x) {
        return (int) (((x % M) + M) % M);
    }

    public boolean ELEMENT_COUNT_TO_MAP_FUNCTION;

    private HashMap<Integer, Integer> hash(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : A) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }

    private HashMap<Long, Integer> hash(long[] A) {
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        for (long i : A) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }

    private TreeMap<Integer, Integer> tree(int[] A) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i : A) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }

    private TreeMap<Long, Integer> tree(long[] A) {
        TreeMap<Long, Integer> map = new TreeMap<Long, Integer>();
        for (long i : A) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }

    public boolean SORT_FUNCTION;

    private int[] sort(int[] A) {
        int n = A.length;
        Random rnd = new Random();
        for (int i = 0; i < n; ++i) {
            int tmp = A[i];
            int randomPos = i + rnd.nextInt(n - i);
            A[i] = A[randomPos];
            A[randomPos] = tmp;
        }
        Arrays.sort(A);
        return A;
    }

    private long[] sort(long[] A) {
        int n = A.length;
        Random rnd = new Random();
        for (int i = 0; i < n; ++i) {
            long tmp = A[i];
            int randomPos = i + rnd.nextInt(n - i);
            A[i] = A[randomPos];
            A[randomPos] = tmp;
        }
        Arrays.sort(A);
        return A;
    }

    private String sort(String s) {
        char[] sc = s.toCharArray();
        Arrays.sort(sc);
        return new String(sc);
    }

    private char[] sort(char[] cs) {
        Arrays.sort(cs);
        return cs;
    }

    private int[][] sort(int[][] A, Comparator<int[]> c) {
        Arrays.sort(A, c);
        return A;
    }

    public boolean REVERSE_FUNCTION;

    private String reverse(String s) {
        StringBuilder p = new StringBuilder(s);
        p.reverse();
        return p.toString();
    }

    private long sum(int[] A) {
        long sum = 0;
        for (int i : A) {
            sum += i;
        }
        return sum;
    }

    private long sum(long[] A) {
        long sum = 0;
        for (long i : A) {
            sum += i;
        }
        return sum;
    }

    private void reverse(long[] A) {
        int n = A.length;
        long[] B = new long[n];
        for (int i = 0; i < n; i++) {
            B[i] = A[n - i - 1];
        }
        System.arraycopy(B, 0, A, 0, n);

    }

    private void reverse(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            B[i] = A[n - i - 1];
        }
        System.arraycopy(B, 0, A, 0, n);

    }


    private int nextPowerOf2(int n) {
        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n++;

        return n;
    }

    private int highestPowerOf2(int x) {

        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;

        return x ^ (x >> 1);

    }

    private long highestPowerOf2(long x) {

        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        return x ^ (x >> 1);

    }

    public boolean ARRAY_MAX_MIN_FUNCTION;

    private int max(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int j : A) {
            max = Math.max(max, j);
        }
        return max;
    }

    private int min(int[] A) {
        int min = Integer.MAX_VALUE;
        for (int j : A) {
            min = Math.min(min, j);
        }
        return min;
    }

    private long max(long[] A) {
        long max = Long.MIN_VALUE;
        for (long l : A) {
            max = Math.max(max, l);
        }
        return max;
    }

    private long min(long[] A) {
        long min = Long.MAX_VALUE;
        for (long l : A) {
            min = Math.min(min, l);
        }
        return min;
    }

    public boolean PREFIX_SUFFIX_FUNCTION;

    private long[] prefix(long[] A) {
        long[] p = new long[A.length];
        p[0] = A[0];
        for (int i = 1; i < A.length; i++)
            p[i] = p[i - 1] + A[i];
        return p;
    }

    private long[] prefix(int[] A) {
        long[] p = new long[A.length];
        p[0] = A[0];
        for (int i = 1; i < A.length; i++)
            p[i] = p[i - 1] + A[i];
        return p;
    }

    private long[] suffix(long[] A) {
        long[] p = new long[A.length];
        p[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--)
            p[i] = p[i + 1] + A[i];
        return p;
    }

    private long[] suffix(int[] A) {
        long[] p = new long[A.length];
        p[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--)
            p[i] = p[i + 1] + A[i];
        return p;
    }

    public boolean FILL_ARRAY_FUNCTION;

    private void fill(int[] dp, int v) {
        Arrays.fill(dp, v);
    }

    private void fill(int[][] dp, int v) {
        for (int[] ints : dp) {
            Arrays.fill(ints, v);
        }
    }

    private void fill(int[][][] dp, int v) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], v);
            }
        }
    }

    private void fill(int[][][][] dp, int v) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    Arrays.fill(dp[i][j][k], v);
                }
            }
        }
    }

    private void fill(long[] dp, long v) {
        Arrays.fill(dp, v);
    }

    private void fill(long[][] dp, long v) {
        for (long[] longs : dp) Arrays.fill(longs, v);
    }

    private void fill(long[][][] dp, long v) {
        for (long[][] ones : dp) {
            for (long[] two : ones) {
                Arrays.fill(two, v);
            }
        }
    }

    private void fill(long[][][][] dp, long v) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    Arrays.fill(dp[i][j][k], v);
                }
            }
        }
    }

    public boolean MAX_AND_MIN_FUNCTION;
    // max and min for 2 ~ 4 arguments, argument Type int long float double

    private int min(int a, int b) {
        return Math.min(a, b);
    }

    private long min(long a, long b) {
        return Math.min(a, b);
    }

    private float min(float a, float b) {
        return Math.min(a, b);
    }

    private double min(double a, double b) {
        return Math.min(a, b);
    }


    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private float min(float a, float b, float c) {
        return Math.min(a, Math.min(b, c));
    }

    private double min(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }

    private long min(long a, long b, long c) {
        return Math.min(a, Math.min(b, c));
    }

    private int min(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    private long min(long a, long b, long c, long d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    private float min(float a, float b, float c, float d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    private double min(double a, double b, double c, double d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private long max(long a, long b) {
        return Math.max(a, b);
    }

    private float max(float a, float b) {
        return Math.max(a, b);
    }

    private double max(double a, double b) {
        return Math.max(a, b);
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private float max(float a, float b, float c) {
        return Math.max(a, Math.max(b, c));
    }

    private double max(double a, double b, double c) {
        return Math.max(a, Math.max(b, c));
    }

    private long max(long a, long b, long c) {
        return Math.max(a, Math.max(b, c));
    }

    private int max(int a, int b, int c, int d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    private long max(long a, long b, long c, long d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    private float max(float a, float b, float c, float d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    private double max(double a, double b, double c, double d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

}
