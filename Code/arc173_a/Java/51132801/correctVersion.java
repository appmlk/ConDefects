import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(reader.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(reader.readLine());
            long K = Long.parseLong(st.nextToken());
            writer.write(Long.toString(solve(K)));
            writer.newLine();
        }
        writer.flush();
    }

    static long solve(long K) {
        long left = 0;
        long right = (long)1e18;
        while (right - left > 1) {
            long mid = left + (right - left) / 2;
            if (smallerNeq(mid) < K) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static long smallerNeq(long x) {
        String s = Long.toString(x);
        int n = s.length();
        long result =  0;
        for (int i = 1; i < n; i++) {
            result += pow(9, i);
        }
        for (int i = 0; i < n; i++) {
            result += (s.charAt(i) - '0' - ((i == 0 || s.charAt(i - 1) < s.charAt(i)) ? 1 : 0)) * pow(9, n - i - 1);
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                break;
            }
        }
        return result;
    }

    static long pow(long x, long y) {
        long result = 1;
        for (int i = 0; i < y; i++) {
            result *= x;
        }
        return result;
    }

    static boolean isNeq(long x) {
        String s = Long.toString(x);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    static long brute(long K) {
        long counter = 0;
        long x = 0;
        while (counter < K) {
            x++;
            if (isNeq(x)) {
                counter++;
                if (x != solve(counter)) {
                    System.out.println(x);
                }
            }
        }
        return x;
    }
}
