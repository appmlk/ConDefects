import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = sc.nextInt();
        long k = sc.nextInt();
        HashSet<Long> set = new HashSet<>();
        for(int i = 0; i < n; i++)
        {
            long data = sc.nextLong();
            set.add(data);
        }
        long sum1 = 0;
        for(long i : set)
        {
            if(i < k)
                sum1 += i;
        }
        long sum2 = (k * (k + 1)) / 2;
        out.println(sum2 - sum1);
        out.close();
    }
    public static int[] getPrime(long n)
    {
        int ans[]= new int[(int) n];
        Arrays.fill(ans, 1);
        for(int i = 2; i <=n; i++)
        {
            if(ans[i] == 0) continue;
            for(int j = 2 * i; j <= n; j += i){
                ans[j] = 0;
            }
        }
        return ans;
    }
    public static long getGcd(long a,long b)
    {
        if(a == 0)
            return b;
        return getGcd(b % a, a);
    }


    public static PrintWriter out;

    //-----------MyScanner class for faster input----------
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