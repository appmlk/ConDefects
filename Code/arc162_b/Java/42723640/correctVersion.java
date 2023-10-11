
import java.io.*;
import java.util.*;


public class Main {
    public void solve() throws Exception {
        int n = nextInt();
        int[] p = new int[n+1];
        for (int i = 0; i < n; i++) {
            p[i+1]=nextInt();
        }
        int r=0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (p[i+1]>p[j+1])r++;
            }
        }
        if (r%2==1){
            out.println("No");
            return;
        }
        List<int[]> res=new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int idx=0;
            for (int j = i; j <= n; j++) {
                if (p[j]==i) {
                    idx=j;
                    break;
                }
            }
            if (idx==i) continue;
            if (idx==n) {
                res.add(new int[]{n-1,n-3});
                int t=p[n-2];
                p[n-2]=p[n-1];
                p[n-1]=p[n];
                p[n]=t;
                idx=n-1;
            }
            res.add(new int[]{idx,i-1});
            int t1=p[idx],t2=p[idx+1];
            for (int j = idx+1; j >= i+2; j--) {
                p[j]=p[j-2];
            }
            p[i]=t1;
            p[i+1]=t2;
        }
        if (res.size()>2000) {
            throw new RuntimeException();
        }
        for (int i = 1; i <= n; i++) {
            if (i!=p[i]) throw new RuntimeException();
        }
        out.println("Yes");
        out.println(res.size());
        for (int[] re : res) {
            out.println(re[0]+" "+re[1]);
        }
    }

    public static void main(String[] args) throws Exception {
//        int t=nextInt();
//        for (int i = 0; i < t; i++) {
            new Main().solve();
//        }
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

