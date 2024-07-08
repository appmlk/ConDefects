import java.io.*;
import java.util.*;

public class Main {
    //--------------------------INPUT READER---------------------------------//
    static class fs {
        public BufferedReader br;
        StringTokenizer st = new StringTokenizer("");

        public fs() { this(System.in); }
        public fs(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }
        String next() {
            while (!st.hasMoreTokens()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }

        int ni() { return Integer.parseInt(next()); }
        long nl() { return Long.parseLong(next()); }
        double nd() { return Double.parseDouble(next()); }
        String ns() { return next(); }

        int[] na(long nn) {
            int n = (int) nn;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = ni();
            return a;
        }

        long[] nal(long nn) {
            int n = (int) nn;
            long[] l = new long[n];
            for(int i = 0; i < n; i++) l[i] = nl();
            return l;
        }
    }
    //-----------------------------------------------------------------------//

    //---------------------------PRINTER-------------------------------------//
    static class Printer {
        static PrintWriter w;
        public Printer() {this(System.out);}
        public Printer(OutputStream os) {
            w = new PrintWriter(os);
        }
        public void p(int i) {w.println(i);}
        public void p(long l) {w.println(l);}
        public void p(double d) {w.println(d);}
        public void p(String s) { w.println(s);}
        public void pr(int i) {w.print(i);}
        public void pr(long l) {w.print(l);}
        public void pr(double d) {w.print(d);}
        public void pr(String s) { w.print(s);}
        public void pl() {w.println();}
        public void close() {w.close();}
    }
    //-----------------------------------------------------------------------//

    //--------------------------VARIABLES------------------------------------//
    static fs sc = new fs();
    static OutputStream outputStream = System.out;
    static Printer w = new Printer(outputStream);
    static long lma = Long.MAX_VALUE, lmi = Long.MIN_VALUE;
    static int ima = Integer.MAX_VALUE, imi = Integer.MIN_VALUE;
    static long mod = 1000000007;
    //-----------------------------------------------------------------------//

    //--------------------------ADMIN_MODE-----------------------------------//
    private static void ADMIN_MODE() throws IOException {
        String DIR = "ADMIN";
        try { DIR = System.getProperty("user.dir"); }
        catch (Exception e) { }

        if(new File(DIR).getName().equals("LOCAL")) {
            w = new Printer(new FileOutputStream("output.txt"));
            sc = new fs(new FileInputStream("input.txt"));
        }
    }
    //-----------------------------------------------------------------------//

    //----------------------------START--------------------------------------//
    public static void main(String[] args)
            throws IOException {

        ADMIN_MODE();

        //int t = sc.ni();while(t-->0)
            solve();


        w.close();
    }

    static List<List<Integer>> graph;
    static boolean[] vis;
    static List<int[]> ans;

    static void solve() throws IOException {
        graph = new ArrayList<>();
        int n = sc.ni();
        int m = sc.ni();

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            int a = sc.ni(), b = sc.ni();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        vis = new boolean[n+1];
        ans = new ArrayList<>();

        dfs(1);

        for(int[] i: ans) {
            w.p(i[0]+" "+i[1]);
        }

        vis = new boolean[n+1];
        ans = new ArrayList<>();

        ArrayDeque<Integer> q = new ArrayDeque<>();
        int[] pt = new int[n+1];
        q.add(1);

        while(!q.isEmpty()) {
            int at = q.pollFirst();
            if(vis[at]) continue;

            if(at != 1) {
                ans.add(new int[] {at, pt[at]});
            }

            vis[at] = true;

            for(int i: graph.get(at)) {
                pt[i] = at;
                q.addLast(i);
            }
        }

        for(int[] i: ans) {
            w.p(i[0]+" "+i[1]);
        }
    }

    static void dfs(int at) {
        vis[at] = true;

        for(int i: graph.get(at)) {
            if(vis[i]) continue;

            ans.add(new int[] {at, i});
            dfs(i);
        }
    }
}