
import java.io.*;
import java.util.*;

public class Main implements Runnable {
    void solve() {
        int v1=nextInt(),v2=nextInt(),v3=nextInt();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                for (int k = 0; k <= 7; k++) {
                    for (int a = -7; a <= i+7; a++) {
                        for (int b = -7; b <= j+7; b++) {
                            for (int c = -7; c <= k+7; c++) {
                                int u2 = sec2(i, j, k);
                                u2+=sec2(a,b,c);
                                u2+=sec2(a-i,b-j,c-k);

                                //[i,7]
                                int dx = Math.max(0, Math.min(a + 7, 7) - Math.max(i, a));
                                int dy = Math.max(0, Math.min(b + 7, 7) - Math.max(j, b));
                                int dz = Math.max(0, Math.min(c + 7, 7) - Math.max(k, c));
                                int u3=dx*dy*dz;
                                u2 -= u3 * 3;
                                int u1 = 7*7*7*3 - u2 * 2 - u3 * 3;
                                if (u1==v1&&u2==v2&&u3==v3){
                                    out.println("Yes");
                                    for (Integer integer : Arrays.asList(0, 0, 0, i, j, k, a, b, c)) {
                                        out.print(integer + " ");
                                    }
                                    out.println();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        out.println("No");
    }

    int sec2(int a,int b,int c) {
        int dx=Math.min(a+7,7) - Math.max(a,0);
        int dy=Math.min(b+7,7) - Math.max(b,0);
        int dz=Math.min(c+7,7) - Math.max(c,0);
        return Math.max(dx*dy*dz,0);
    }

    public static void main(String[] args) throws Exception {
        new Thread(null, new Main(), "CustomThread", 1024 * 1024 * 100).start();
    }

    @Override
    public void run() {
        new Main().solve();
        out.flush();
    }

    static PrintWriter out = new PrintWriter(System.out, false);
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

