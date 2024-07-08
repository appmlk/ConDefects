import java.io.*;
import java.util.Arrays;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static final int N = 2 * (int) 1e6;
    static final int M = 26;
    static int index;
    static int[] cnt;
    static int[][] mos;
    static long ans;

    public static void main(String[] args) throws Exception {

        solve();

        pw.close();
        br.close();
    }

    public static void solve() throws Exception {

        ans=0;
        cnt= new int[N];
        mos = new int[N][M];

        int n=nextInt();
        for (int i = 0; i < n; i++) add(nextString());

        pw.println(ans);

    }

    public static int inquire(String str) {
        int p = 0;
        for (int i = 0, end = str.length(); i < end; ++i) {
            int val = str.charAt(i) - 'a';
            if (0 == mos[p][val]) return 0;
            p = mos[p][val];
        }
        return cnt[p];
    }

    public static void add(String str) {
        int p = 0;
        for (int i = 0, end = str.length(); i < end; ++i) {
            int val = str.charAt(i) - 'a';
            if (0 == mos[p][val]) mos[p][val] = ++index;
            p = mos[p][val];
            ans+=cnt[p];
            ++cnt[p];
        }
        return;
    }


    public static String nextString() throws Exception {
        st.nextToken();
        return st.sval;
    }

    public static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }

    public static long nextLong() throws Exception {
        st.nextToken();
        return (long) st.nval;
    }
}