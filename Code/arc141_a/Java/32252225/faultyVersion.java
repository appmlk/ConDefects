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

        int t = sc.ni();while(t-->0)
            solve();


        w.close();
    }

    static void solve() throws IOException {
        char[] strr = sc.ns().toCharArray();
        long given = Long.parseLong(String.valueOf(strr));
        int n = strr.length;
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(strr[i]+"");
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n-1; i++) {
            sb.append(9);
        }
        long best = Long.parseLong(sb.toString());
        for(int i = 0; i < n-1; i++) {
            if(n%(i+1)!=0) continue;
            sb = new StringBuilder(String.valueOf(strr, 0, (i+1)));
            int q = n/(i+1);
            StringBuilder res = new StringBuilder();
            for(int j = 0; j < q; j++) {
                res.append(sb);
            }
            long curr = Long.parseLong(res.toString());
            if(curr <= given) {
                best = Math.max(best, curr);
                continue;
            }
            if(sb.charAt(sb.length()-1)=='0') continue;
            long prev = Long.parseLong(sb.toString());
            prev--;
            sb = new StringBuilder(Long.toString(prev));
            res = new StringBuilder();
            for(int j = 0; j < q; j++) {
                res.append(sb);
            }
            curr = Long.parseLong(res.toString());
            if(curr <= given) {
                best = Math.max(best, curr);
            }
        }
        w.p(best);
    }

}