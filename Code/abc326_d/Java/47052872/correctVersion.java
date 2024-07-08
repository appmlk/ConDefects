import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    //    private static int[][] dirs = {{-1,-1}, {1, 1}, {-1, 1}, {1, -1}};
    private static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static long inf = (long) 1e13;
    private static long div = 998_244_353L;
//    private static long div = ((long)1e9) + 7;

    private static String[] combs = new String[] {"ABC", "ACB", "CBA", "BCA", "BAC", "CAB"};

    private static boolean verify(char mtx[][], String r, String c) {
        int N = r.length();
        for(int row = 0;row < N; ++row) {
            char curr = r.charAt(row);
            for(int col = 0;col < N; ++col) {
                if (mtx[row][col] == '.') {
                    continue;
                }

                if (mtx[row][col] == curr) {
                    break;
                } else {
                    return false;
                }
            }
        }

        for(int col = 0;col < N; ++col) {
            char curr = c.charAt(col);
            for(int row = 0;row < N; ++row) {
                if (mtx[row][col] == '.') {
                    continue;
                } else if (mtx[row][col] == curr) {
                    break;
                } else {
                    return false;
                }
            }
        }


        for(int col = 0;col < N; ++col) {
            int[] count = new int[3];
            for(int row = 0;row < N; ++row) {
                if (mtx[row][col] == '.') {
                    continue;
                }
                count[mtx[row][col] - 'A']++;
                if (count[mtx[row][col] - 'A'] != 1) {
                    return false;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                sb.append(mtx[i][j]);
            }
            sb.append('\n');
        }
        System.out.println("Yes");
        System.out.println(sb.toString());
        return true;
    }

    private static boolean generate(int row, char[][] mtx, int N, String r, String c) {
        if (row == N) {
            return verify(mtx, r, c);
        }

        for(String comb:combs) {
            if (comb.charAt(0) != r.charAt(row)) {
                continue;
            }

            for(int i = 0;i < N; ++i) {
                mtx[row][i] = comb.charAt(0);
                for(int j = i + 1;j < N; ++j) {
                    mtx[row][j] = comb.charAt(1);
                    for(int k = j + 1; k < N; ++k) {
                        mtx[row][k] = comb.charAt(2);
                        boolean res = generate(row + 1, mtx, N, r, c);
                        if (res) {
                            return true;
                        }
                        mtx[row][k] = '.';
                    }
                    mtx[row][j] = '.';
                }
                mtx[row][i] = '.';
            }
        }
        return false;
    }

    public static void main(String[] commands) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = in.readLine().split(" ");
        int N = Integer.parseInt(parts[0]);
        String R = in.readLine();
        String C = in.readLine();
        char[][] mtx = new char[N][N];
        for(char[] row:mtx) {
            Arrays.fill(row, '.');
        }
        boolean res = generate(0, mtx, N, R, C);
        if (!res) {
            System.out.println("No");
        }
    }
}
