
import java.io.*;
import java.util.*;

public class Main {

    public static void solve() throws IOException{
        int n = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();
        Deque<Character> que = new ArrayDeque<>();
        String s = in.nextLine();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (que.isEmpty() || ch == '(') que.addLast(ch);
            else {
                if (que.peekLast() == '(') que.pollLast();
                else que.addLast(ch);
            }
        }
        List<Character> list = new ArrayList<>();
        while (!que.isEmpty()) list.add(que.pollFirst());
        if (list.size() == 0) {
            out.println(0);
            return;
        }
        //))()((((()()((
        //))((((((
        //)))(()
        if (list.get(0) == '(' && list.get(list.size()-1) == '(') {
            out.println(b * list.size() / 2);
        }else if (list.get(0) == ')' && list.get(list.size()-1) == ')') {
            out.println(b * list.size() / 2);
        }else {
            long sumR = 0; //)))((
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == ')') sumR++;
            }
            long sumL = list.size() - sumR;
            if (2L * b <= a) { //替换
                long ans = b * (sumR / 2) + b * (sumL / 2);
                sumR %= 2;
                out.println(ans + Math.min(a, b) * sumR);
                
            }else {
                long mi = Math.min(sumR, sumL);
                long mx = Math.max(sumR, sumL);
                if (mi % 2 == 0) {
                    long ans = mi / 2 * a;
                    mx -= mi;
                    out.println(ans + b * mx / 2);
                }else {
                    long ans = mi / 2 * a;
                    mx -= mi - 1;
                    if (2 * b >= a) {
                        mx--;
                        ans += a;
                    }
                    ans += b * mx / 2;
                    out.println(ans);
                }
            }
        }
    }

    static boolean MULTI_CASE = false;

    public static void main(String[] args) throws IOException {
        if (MULTI_CASE) {
            int T = in.nextInt();
            for (int i = 0; i < T; ++i) {
                solve();
            }
        } else {
            solve();
        }
        out.close();
    }

    static InputReader in = new InputReader();
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    static class InputReader {
        private StringTokenizer st;
        private BufferedReader bf;

        public InputReader() {
            bf = new BufferedReader(new InputStreamReader(System.in));
            st = null;
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(bf.readLine());
            }
            return st.nextToken();
        }

        public String nextLine() throws IOException {
            return bf.readLine();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}

/*
))((((((
1234
1456789
5
9
4
 */