import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Coder: SumitRaut
 * Date: 06-04-2022 12:38
 */


public class Main {
    public static int n;
    public static int[] ar;
    public static List<Integer>[] g;

    public static PriorityQueue<Integer> dfs(int u, int p) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int v : g[u]) {
            if (v != p) {
                PriorityQueue<Integer> child = dfs(v, u);
                if (pq.size() < child.size()) {
                    PriorityQueue<Integer> tmp = child;
                    child = pq;
                    pq = tmp;
                }
                for (int e : child) {
                    pq.add(e);
                }
            }
        }
        if (!pq.isEmpty()) pq.poll();
        pq.add(ar[u]);
        return pq;
    }

    public static void main(String[] args) throws IOException {
        fs = new FastReader();
        out = new PrintWriter(System.out);
        n = fs.nextInt();
        ar = new int[n];
        g = new LinkedList[n];
        ar[0] = -1;
        g[0] = new LinkedList<>();
        for (int i = 1; i < n; ++i) {
            ar[i] = fs.nextInt();
            g[i] = new LinkedList<>();
        }
        for (int i = 1; i < n; ++i) {
            int u = fs.nextInt() - 1, v = fs.nextInt() - 1;
            g[u].add(v);
            g[v].add(u);
        }
        PriorityQueue<Integer> res = dfs(0, -1);
        int ans = res.peek();
        out.println(ans);
        out.close();
    }

    public static PrintWriter out;
    public static FastReader fs;
    public static final Random random = new Random();

    public static void ruffleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            int oi = random.nextInt(n), tmp = a[oi];
            a[oi] = a[i];
            a[i] = tmp;
        }
        Arrays.sort(a);
    }

    public static class FastReader {
        private BufferedReader br;
        private StringTokenizer st = new StringTokenizer("");

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String file_name) throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file_name))));
        }

        public String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; ++i)
                a[i] = nextInt();
            return a;
        }

        public String nextLine() {
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
