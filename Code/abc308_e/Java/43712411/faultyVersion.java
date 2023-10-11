

import java.io.*;
import java.util.*;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer stn = new StreamTokenizer(bf);
    static PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) throws IOException {
        solve();
        closeAndFlush();
    }


    public static void solve() throws IOException {
        int n = sc.nextInt();
        int[] nums = new int[n + 1];
        char[] chars = new char[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
        }
        chars = (" " + sc.next()).toCharArray();
        int m0 = 0, m1 = 0, m2 = 0, x0 = 0, x1 = 0, x2 = 0;
        int[][] ms = new int[n + 1][3], xs = new int[n + 1][3];
        //统计当前 e的前面有多少个 m和它后面有多少个 x
        for (int i = 1, j = n; i <= n; i++, j--) {
            if (chars[i] == 'E') {
                ms[i][0] = m0;
                ms[i][1] = m1;
                ms[i][2] = m2;
            } else if (chars[i] == 'M') {
                if (nums[i] == 0) m0++;
                else if (nums[i] == 1) m1++;
                else if (nums[i] == 2) m2++;
            }
            if (chars[j] == 'E') {
                xs[j][0] = x0;
                xs[j][1] = x1;
                xs[j][2] = x2;
            } else if (chars[j] == 'X') {
                if (nums[j] == 0) x0++;
                else if (nums[j] == 1) x1++;
                else if (nums[j] == 2) x2++;
            }
        }
        long sum = 0;
        for (int i = 2; i <= n - 1; i++) {
            if (chars[i] == 'E') {
                for (int j = 0; j < 3; j++) {
                    if (ms[i][j] == 0) continue;//没有m
                    for (int k = 0; k < 3; k++) {
                        if (xs[i][k] == 0) continue;//没有x
                        sum += mex(nums[i], j, k) * ms[i][j] * xs[i][k];
                    }
                }
            }
        }
        printWriter.println(sum);
    }

    //求出三个数中最小的且没有出现过的数
    public static int mex(int x, int y, int z) {
        for (int i = 0; i <= 2; i++) {
            if (x != i && y != i && z != i) return i;
        }
        return 3;
    }

    public static void closeAndFlush() throws IOException {
        printWriter.flush();
        printWriter.close();
        bf.close();
        sc.close();
    }

    public static int readInt() throws IOException {
        stn.nextToken();
        return (int) stn.nval;
//        return Integer.parseInt(readString());
    }

    public static String readString() throws IOException {
        return bf.readLine();
    }
}
