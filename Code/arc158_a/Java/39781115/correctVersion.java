//  A - +3 +5 +7

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    // https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in)
            );
        }

        String next() {
            while(st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }


            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try {
                if(st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        FastReader s = new FastReader();
        int n = s.nextInt();
        while(n-- > 0) {
            long a = s.nextInt(), b = s.nextInt(), c = s.nextInt();
            if((a % 2 != b % 2 || b % 2 != c % 2) || (a + b + c) % 3 != 0) {
                System.out.println(-1);
            } else {
                long m = (a + b + c)/3;
                long d = Math.abs(a - m) +  Math.abs(b - m) + Math.abs(c - m);
                if(d % 4 != 0) {
                    System.out.println(-1);
                } else {
                    System.out.println(d/4);
                }
            }
        }
    }
}