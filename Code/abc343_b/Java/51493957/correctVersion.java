import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) {
        int n = sc.nextInt();
        boolean f=false;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int r = sc.nextInt();
                if (r==1){
                    System.out.print(j+" ");
                    f=true;
                }
            }
            if (f) System.out.println();
            f=false;
        }
    }
    static class FastReader {
        StringTokenizer st;
        BufferedReader br;

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
    }
    static FastReader sc = new FastReader();

}
