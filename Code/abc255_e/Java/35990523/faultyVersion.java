import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * https://atcoder.jp/contests/abc255/tasks/abc255_e
 */
public class Main {

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        // write here
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[]dict=new int[n];
        int[]luck=new int[m];
        for(int i=0;i<n-1;i++){
            dict[i]=sc.nextInt();
        }
        Map<Long,Integer> result=new HashMap<>();
        for (int i = 0; i < m; i++) {
            luck[i] = sc.nextInt();
            result.put((long) luck[i], 1);
        }
        //a0=a0;a0=s0-a1;a0=s0-s1+a2;a0=s0-s1+s2-a3....
        long cur=0,sign=1;
        for (int value : dict) {
            cur += (sign * value);
            sign = -sign;
            for (int j = 0; j < m; j++) {
                result.compute(cur + sign * luck[j], (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
            }
        }
        out.println(result.values().stream().max(Comparator.comparingInt(a->a)).get());
        out.close();
    }

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
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

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}

