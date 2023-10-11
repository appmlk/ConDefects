import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[]args) throws IOException {
        new P().solve();
    }
}
class P {
    int n;
    char[] A, B;
    void solve() throws IOException {
        n = in.nextInt();
        A = in.next().toCharArray();
        B = in.next().toCharArray();
        int[] f = new int[26];
        for (int i = 0; i < n; ++i) {
            f[A[i] - 'a']++;
            f[B[i] - 'a']--;
        }
        for (int i : f) {
            if (i != 0) {
                System.out.println(-1);
            }
        }
        int k = n - 1;
        for (int i = n - 1; i >= 0; --i) {
            if (A[k] == B[i]) {
                --k;
            }
        }
        System.out.println(k + 1);
    }
}
class in {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer = new StringTokenizer("");
    static String nextLine() throws IOException{
        return reader.readLine();
    }
    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}
