import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            if (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static FastReader sc = new FastReader();

//    static class Pair implements Comparable<Pair> {
//        int a, b;
//
//        public Pair(int a) {
//            this.a = a;
//        }
//
//        @Override
//        public int compareTo(Pair o) {
//            if (a == o.a) return b - o.b;
//            return a - o.a;
//        }
//    }

    static final int N = 200010;

    static int n;

    static StringBuilder ans = new StringBuilder();

    static void solve() {
        sc.nextInt();
        String s = sc.next();
        char c = s.charAt(0);
        boolean flag = false;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) > c) {
                flag = true;
                break;
            } else if (s.charAt(i) == c && isGreater(s.substring(0, i), s.substring(i))) {
                flag = true;
                break;
            }
        }
        if (flag) ans.append("Yes\n");
        else ans.append("No\n");
    }

    static boolean isGreater(String s, String t) {
        int x = Math.min(s.length(), t.length());
        for (int i = 0; i < x; i++) {
            if (s.charAt(i) < t.charAt(i)) return true;
            else if (s.charAt(i) > t.charAt(i)) return false;
        }
        return s.length() < t.length();
    }

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            solve();
        }
        System.out.print(ans);
    }
}