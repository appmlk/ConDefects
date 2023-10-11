import java.util.*;import java.io.*;

public class Main {
    static String ss, io[];
    static int test, N = 200010, M = 1000000007;
    static int n, m, ans, t = 1, v[] = new int[N];
    static List<Integer>[] g = new List[N];
    static void dfs(int x){
        v[x] = t++;
        for (int y : g[x]){
            if (v[y]==0) dfs(y);
            else if (v[y] > v[x]) ans++; 
        }
    }
    static void solve() throws Exception{
        n = ni(); m = ni();
        for (int i = 1;i <= n;i++) g[i] = new ArrayList<>();
        for (int i = 0;i < m;i++){
            int x = ni(), y = ni();
            g[x].add(y);
            g[y].add(x);
        }
        for (int i = 1;i <= n;i++) if (v[i] == 0) dfs(i);
        out.println(ans);
    }
    public static void main(String[] args) throws Exception {
        test = 1;
        // test = ni(in.readLine());
        while (test-- > 0){
            solve();
        }out.flush();
    }
    static int ni() throws IOException{input.nextToken();return (int) input.nval;}
    static long nl() throws IOException{input.nextToken();return (long) input.nval;}
    static int ni(String x) {return Integer.parseInt(x);}
    static long nl(String x) {return Long.parseLong(x);}
    static int max(int a, int b) {return a > b ? a : b;}
    static long max(long a, long b) {return a > b ? a : b;}
    static int min(int a, int b) {return a < b ? a : b;}
    static long min(long a, long b) {return a < b ? a : b;}
    static int lg2(long a) {return (int)Math.ceil((Math.log(a)/Math.log(2)));}
    static int abs(int a) {return a > 0?a:-a;}
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer input = new StreamTokenizer(in);
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
}  