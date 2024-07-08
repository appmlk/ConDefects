

import java.io.*;
import java.util.*;

public class Main {

//    private static int[][] dirs = {{-1,-1}, {1, 1}, {-1, 1}, {1, -1}};
    private static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static long inf = (long) 1e13;
        private static long div = 998_244_353L;
//    private static long div = ((long)1e9) + 7;





    public static void main(String[] commands) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = in.readLine().split(" ");
        int N = Integer.parseInt(parts[0]);
        String str = in.readLine();

        char[] reversed = str.toCharArray();
        Arrays.sort(reversed);
        String max = new StringBuilder(new String(reversed)).reverse().toString();
        long maxNum = Long.parseLong(max);
        int[] digits = new int[10];
        for(int i = 0;i < str.length(); ++i) {
            digits[str.charAt(i) - '0']++;
        }
        int ans = 0;
        for(long i = 1;i * i <= maxNum; ++i) {
            long next = i * i;
            StringBuilder nextStr = new StringBuilder(String.valueOf(next));
            while(nextStr.length() < max.length()) {
                nextStr.insert(0, "0");
            }
            var finalNext = nextStr.toString();
            int[] currDigits = new int[10];
            for(int j = 0;j < finalNext.length(); ++j) {
                currDigits[finalNext.charAt(j) - '0']++;
            }

            boolean ok = true;
            for(int k = 0;k < 10; ++k) {
                if (currDigits[k] != digits[k]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
