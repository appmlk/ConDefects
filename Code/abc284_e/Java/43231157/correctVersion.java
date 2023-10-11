import java.util.*;
import java.io.*;

public class Main {
    static FastScanner sc = new FastScanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        //int T = sc.nextInt();
        //for(int i = 0; i < T; i++)solve();
        
        solve();
        pw.flush();
    }
    
    static int MAX = (int)1e6;
    static int ans = 0;
    static boolean[] used;
    static ArrayList<ArrayList<Integer>> map;
    public static void solve() {
        int N = sc.nextInt();
        int M = sc.nextInt();
        map = new ArrayList<>();
        for(int i = 0; i < N; i++){
            map.add(new ArrayList<>());
        }   
        for(int i = 0; i < M; i++){
            int s = sc.nextInt()-1;
            int t = sc.nextInt()-1;
            map.get(s).add(t);
            map.get(t).add(s);
        }
        used = new boolean[N];
        used[0] = true;
        dfs(0);
        pw.println(Math.min(MAX,ans));
    }
    
    public static void dfs(int v){
        ans++;
        if(ans >= MAX) return;
        for(int next : map.get(v)){
            if(!used[next]){
                used[next] = true;
                dfs(next);
                used[next] = false;
            }
        }
    }
}

/**
 * refercence : https://github.com/NASU41/AtCoderLibraryForJava/blob/master/ContestIO/ContestScanner.java
 */
class FastScanner {
    private BufferedReader reader = null;
    private StringTokenizer tokenizer = null;

    public FastScanner(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = null;
    }

    public FastScanner(FileReader in) {
        reader = new BufferedReader(in);
        tokenizer = null;
    }

    public String next() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public String nextLine() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken("\n");
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String[] nextArray(int n) {
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = next();
        return a;
    }

    public int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = nextInt();
        return a;
    }

    public long[] nextLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = nextLong();
        return a;
    }
}
