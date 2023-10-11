import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n = nextInt();
        char[] s = next().toCharArray();
        char[] t = next().toCharArray();
        // 埋まってないもの
        int[] tCounts = new int[26];
        int tTotal = 0;
        // 取り出したもの（余り）
        int[] sCounts = new int[26];
        int sTotal = 0;
        int sl = 0;
        int tr = n-1;

        // Sの末尾が、Tの何文字目に該当するのか...
        for (int i = n - 1; i >= 0 && sl <= i; i--) {
            while (tr >= 0 && t[tr--] != s[i]) {
                int tri = t[tr+1] - 'a';
                if (sCounts[tri] > 0) {
                    sCounts[tri]--;
                    sTotal--;
                    continue;
                } else {
                    tCounts[tri]++;
                    tTotal++;
                }
            }

            while (sl < i && tTotal > 0) {
                int sli = s[sl] - 'a';
                if (tCounts[sli] > 0) {
                    tCounts[sli]--;
                    tTotal--;
                } else {
                    sCounts[sli]++;
                    sTotal++;
                }
                sl++;
            }
            tr--;
        }

        for (int i = 0; i <= tr; i++) {
            if (sCounts[t[i]-'a'] > 0) {
                sCounts[t[i]-'a']--;
                sTotal--;
            }
        }

        if (sTotal > 0 || tTotal > 0) {
            out.println(-1);
        } else {
            out.println(sl);
        }


        out.flush();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Scanner scanner = new Scanner(System.in);
    static String next() { return scanner.next(); }
    static int nextInt() {
        int res = 0;
        char[] chars = next().toCharArray();
        boolean minus = chars[0] == '-';
        int start = minus?1:0;
        for (int i = start; i < chars.length; i++) {
            res = res*10 + (chars[i]-'0');
        }
        return minus?-res:res;
    }
    static long nextLong() {
        long res = 0;
        char[] chars = next().toCharArray();
        boolean minus = chars[0] == '-';
        int start = minus?1:0;
        for (int i = start; i < chars.length; i++) {
            res = res*10 + (chars[i]-'0');
        }
        return minus?-res:res;
    }
    static double nextDouble() { return Double.parseDouble(next()); }
    static int[] nextIntArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) { array[i] = nextInt(); }
        return array;
    }
    static long[] nextLongArray(int n) {
        long[] array = new long[n];
        for (int i = 0; i < n; i++) { array[i] = nextLong(); }
        return array;
    }

}