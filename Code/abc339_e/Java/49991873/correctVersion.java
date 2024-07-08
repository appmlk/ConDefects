import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    static int[] tree = new int[500005];
    static int[] a = new int[500005];
    static int N = 500000;
    public static void main(String[] args) throws IOException {
        int n = input.nextInt();
        int d = input.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int num = input.nextInt();
            int L = Math.max(1, num -d);
            int R = Math.min(N, num + d);
            int res = query(L, R);
            if (res+1 > a[num]){
                update(num, res+1);
                a[num] = res+1;
                ans = Math.max(ans, res+1);
            }
        }
        out.println(ans);
        out.flush();
        out.close();
        br.close();
    }
    public static int lowbit(int x){
        return x & (-x);
    }
    public static void update(int x, int value){
        while(x <= N){
            tree[x] = Math.max(value, tree[x]);
            for(int i = 1; i > lowbit(x); i <<= 1){
                tree[x] = Math.max(tree[x], tree[x - i]);
            }
            x+=lowbit(x);
        }
    }
    public static int query(int L, int R){
        int ans = 0;
        while (L <= R){
            ans = Math.max(ans, a[R]);
            R--;
            while (R - L >= lowbit(R)){
                ans = Math.max(ans, tree[R]);
                R-=lowbit(R);
            }
        }
        return ans;
    }


    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static Input input = new Input(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Input {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public Input(InputStream stream) {
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

        public String nextLine() {
            String str = null;
            try {
                str = reader.readLine();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public Double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }
    }

}
