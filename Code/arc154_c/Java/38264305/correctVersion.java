//  C - Roller

import java.util.*;
import java.io.*;

public class Main {
    static void solve() {
        StringBuilder res = new StringBuilder();
        int n = sc.nextInt();
        int[] arr =sc.readIntArray(n);
        int[] brr =sc.readIntArray(n);
        int lim = n;
        while (lim>1&&brr[lim-1]==brr[0]) lim--;
        boolean g = false;
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[(i + 1) % n]) {
                g = true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            int k = i,c = 0,j=0;
            boolean f = false,is= true;
            for (j = 0; j < lim; ) {
                c=0;
                while(k<n+i-1&&arr[k%n]!=brr[j]){
                    k++;c++;
                }
                if(c>1) f = true;
                if(arr[k%n]!=brr[j]){
                    is = false;
                    break;
                }
                int p = brr[j];
                while (j<lim&&brr[j]==p) j++;
            }
            if(k<n+i-1)  f = true;
            if(is&&k<=n+i&&j==lim&&(i==0||f)) {
                print("Yes");
                return;
            }
        }
        print("No");
    }

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    static FastReader sc;
    static PrintWriter out;
    public static void main(String[] args) throws IOException {
        sc = new FastReader();
        out = new PrintWriter(System.out);
        int tt = sc.nextInt();
        for (int t = 1; t <= tt; t++) {
            solve();
        }
        out.close();
    }

    static <E> void print(E res) {
        out.println(res);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readIntArray(int n) {
            int[] res = new int[n];
            for (int i = 0; i < n; i++)
                res[i] = nextInt();
            return res;
        }

        long[] readLongArray(int n) {
            long[] res = new long[n];
            for (int i = 0; i < n; i++)
                res[i] = nextLong();
            return res;
        }
    }

}
