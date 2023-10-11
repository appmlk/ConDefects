
import java.io.*;
import java.util.*;

public class Main {
    int[] p;
    int[] ar;
    List<List<Integer>> g=new ArrayList<>();
    List<Set<Integer>> chv=new ArrayList<>();
    int[] slot;
    boolean win=false;
    int k,n;
    public void solve() throws Exception {
        n = nextInt();
        k=nextInt();
        p = new int[n+1];
        ar = new int[n+1];
        slot = new int[n+1];
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
            chv.add(new HashSet<>());
        }
        for (int i = 2; i <= n; i++) {
            p[i]=nextInt();
            g.get(p[i]).add(i);
        }
        for (int i = 1; i <= n; i++) {
            ar[i]=nextInt();
        }
        dfs(1);
        System.out.println(win?"Alice":"Bob");
    }

    void dfs(int i) {
        int s=ar[i]==-1?1:0;
        chv.get(i).add(ar[i]);
        for (Integer ch : g.get(i)) {
            dfs(ch);
            s += slot[ch];
            for (Integer vv : chv.get(ch)) {
                chv.get(i).add(vv);
            }
        }
        slot[i]=s;
        if (chv.get(i).contains(k)) {
            return;
        }
        int show=0;
        for (int j = 0; j < k; j++) {
            if (chv.get(i).contains(j)) {
                show++;
            }
        }
        if (show==k-1&&s==1||show==k&&s<=1) {
            win=true;
        }
    }

    public static void main(String[] args) throws Exception {
        int t=nextInt();
        for (int i = 0; i < t; i++) {
            new Main().solve();
        }
    }

    static PrintWriter out = new PrintWriter(System.out, true);
    static InputReader in = new InputReader(System.in);
    static String next() { return in.next(); }
    static int nextInt() { return Integer.parseInt(in.next()); }
    static long nextLong() { return Long.parseLong(in.next()); }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
    }
}

