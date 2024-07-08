import java.io.*;
import java.util.*;

public class Main {
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static int mod = (int) (1e9 + 7);
//    static int mod = 998244353;

    static int INF = 0x3f3f3f3f;
    static int[][] d = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static int N = 101010, idx = 1;


    public static void main(String[] args) {
        AReader sc = new AReader();
        int t = 1;
//        t = sc.nextInt();
        while (t-- > 0) {
            String s = sc.next();
            char[] c = s.toCharArray();
            if (c[0] != '<' || c[s.length() - 1] != '>' || s.length() <= 2) {
                pw.println("No");
            } else {
                boolean flag = true;
                for (int i = 1; i < s.length() - 1; i++) {
                    if (c[i] != '=') {
                        flag = false;
                        break;
                    }
                }
                pw.println(flag ? "Yes" : "NO");
            }
            pw.close();
        }
    }

    static class AReader {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");

        private String innerNext() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return null;
            }
        }

        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String s = innerNext();
                if (s == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(s);
            }
            return true;
        }

        public String nextLine() {
            return innerNext();
        }

        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
