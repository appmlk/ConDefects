import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer stmInput = new StreamTokenizer(br);
    static class Pair{
        boolean flag;
        int idx, e;
        public Pair(boolean flag, int idx, int e){
            this.flag = flag;
            this.idx = idx;
            this.e = e;
        }
    }
    static int N = 100010, M = 200010;
    static Pair a[] = new Pair[N], b[] = new Pair[N];
    static Pair s[] = new Pair[M];
    static long s1[] = new long[M], s2[] = new long[M];
    static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    static int n, m, k;

    public static int readInt() throws IOException{
        stmInput.nextToken();
        return (int) stmInput.nval;
    }

    public static void solve() throws IOException{
        n = readInt();
        m = readInt();
        k = readInt();
        int idx = 0;
        for(int i = 1; i <= n; i++) {
            a[i] = new Pair(true, i, readInt());
            s[++idx] = a[i];
        }
        for(int i = 1; i <= m; i++) {
            b[i] = new Pair(false, i, readInt());
            s[++idx] = b[i];
        }

        for(int i = 0; i < k; i++) {
            int c = readInt(), d = readInt();
            if (map.get(c) == null) map.put(c, new HashSet<>());
            map.get(c).add(d);
        }

        Arrays.sort(s, 1, n + m + 1, (x, y) -> { return y.e - x.e;});

        for(int i = 1; i <= n + m; i++){
            s1[i] = s1[i - 1] + (s[i].flag ? 1 : 0);
            s2[i] = s2[i - 1] + (s[i].flag ? 0 : 1);
        }
        int l = 1, r = n + m;
        while (l < r){
            int mid = l + r >> 1;
            if (s1[mid] * s2[mid] > k) r = mid;
            else l = mid + 1;
        }
        int rmax = Math.min(r + 500, n + m);
        int ans = 0;
        List<Pair> la = new ArrayList<>(), lb = new ArrayList<>();
        for (int i = 1; i <= rmax; i++){
            if (s[i].flag) la.add(s[i]);
            else lb.add(s[i]);
        }

        for (Pair ea : la){
            for (Pair eb : lb){
                if (map.get(ea.idx) == null || !map.get(ea.idx).contains(eb.idx)) ans = Math.max(ans, ea.e + eb.e);
            }
        }
        pw.println(ans);
    }

    public static void main(String[] args) throws IOException {

        int T = 1;
        while(T-- != 0){
            solve();
        }

        pw.flush();
        pw.close();
        br.close();
    }

}