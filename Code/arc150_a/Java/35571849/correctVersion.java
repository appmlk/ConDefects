
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * #
 *
 * @author pttrung
 */
public class Main {
    public static long MOD = 998244353;


    public static void main(String[] args) throws FileNotFoundException {
        // PrintWriter out = new PrintWriter(new FileOutputStream(new File(
        // "output.txt")));
        PrintWriter out = new PrintWriter(System.out);
        Scanner in = new Scanner();
        int T = in.nextInt();
        for (int z = 0; z < T; z++) {
            int n = in.nextInt();
            int k = in.nextInt();
            boolean ok = true;
            int first = -1;
            int last = -1;
            int zero = -1;
            String data = in.next();

            for (int i = 0; i < n && ok; i++) {
                if (data.charAt(i) == '1') {
                    if (first == -1) {
                        first = i;
                    }
                    if (zero != -1 && first != -1 && first < zero && zero < i) {
                        //System.out.println(first + " " + zero + " " + i);
                        ok = false;
                        break;
                    }
                    last = i;
                    continue;
                }
                if (data.charAt(i) == '?') {
                    continue;
                }
                zero = i;
            }
            //System.out.println(ok);
            if (!ok) {
                out.println("No");
                continue;
            }
            if (first == -1) {
                int cur = 0;
                int total = 0;
                for (int i = 0; i < n; i++) {
                    if (data.charAt(i) == '?') {
                        cur++;
                        continue;
                    }
                    if (cur == k) {
                        total++;
                        cur = 0;
                        continue;
                    }
                    if (cur > k) {
                        ok = false;
                        break;
                    }
                    cur = 0;
                }
                if (cur == k) {
                    total++;
                } else if (cur > k) {
                    ok = false;
                }
                out.println((ok && total == 1) ? "Yes" : "No");
                continue;
            }
            int a = first;
            int b = last;
            while (a > 0 && data.charAt(a - 1) == '?' && (last - a + 2) <= k) {
                a--;
            }
            while (b < n - 1 && data.charAt(b + 1) == '?' && (b - first + 2) <= k) {
                b++;
            }
            //System.out.println(a + " " + b);
            out.println(((b - a + 1) == k) ? "Yes" : "No");

        }
        out.close();
    }


    static int abs(int a) {
        return a < 0 ? -a : a;
    }


    public static int[] KMP(String val) {
        int i = 0;
        int j = -1;
        int[] result = new int[val.length() + 1];
        result[0] = -1;
        while (i < val.length()) {
            while (j >= 0 && val.charAt(j) != val.charAt(i)) {
                j = result[j];
            }
            j++;
            i++;
            result[i] = j;
        }
        return result;

    }

    public static boolean nextPer(int[] data) {
        int i = data.length - 1;
        while (i > 0 && data[i] < data[i - 1]) {
            i--;
        }
        if (i == 0) {
            return false;
        }
        int j = data.length - 1;
        while (data[j] < data[i - 1]) {
            j--;
        }
        int temp = data[i - 1];
        data[i - 1] = data[j];
        data[j] = temp;
        Arrays.sort(data, i, data.length);
        return true;
    }

    public static int digit(long n) {
        int result = 0;
        while (n > 0) {
            n /= 10;
            result++;
        }
        return result;
    }

    public static double dist(long a, long b, long x, long y) {
        double val = (b - a) * (b - a) + (x - y) * (x - y);
        val = Math.sqrt(val);
        double other = x * x + a * a;
        other = Math.sqrt(other);
        return val + other;

    }

    public static class Point {

        int x;
        int y;

        public Point(int start, int end) {
            this.x = start;
            this.y = end;
        }

        public String toString() {
            return x + " " + y;
        }

    }

    public static class FT {

        long[] data;

        FT(int n) {
            data = new long[n];
        }

        public void update(int index, long value) {
            while (index < data.length) {
                data[index] += value;
                index += (index & (-index));
            }
        }

        public long get(int index) {

            long result = 0;
            while (index > 0) {
                result += data[index];
                index -= (index & (-index));
            }
            return result;

        }
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static long pow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }

        long val = pow(a, b / 2);
        if (b % 2 == 0) {
            return (val * val) % MOD;
        } else {
            return (val * ((val * a) % MOD)) % MOD;

        }
    }

    static class Scanner {

        BufferedReader br;
        StringTokenizer st;

        public Scanner() throws FileNotFoundException {
            // System.setOut(new PrintStream(new BufferedOutputStream(System.out), true));
            br = new BufferedReader(new InputStreamReader(System.in));
            //br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt"))));
        }

        public String next() {

            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
            return st.nextToken();
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            st = null;
            try {
                return br.readLine();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }

        public boolean endLine() {
            try {
                String next = br.readLine();
                while (next != null && next.trim().isEmpty()) {
                    next = br.readLine();
                }
                if (next == null) {
                    return true;
                }
                st = new StringTokenizer(next);
                return st.hasMoreTokens();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
}