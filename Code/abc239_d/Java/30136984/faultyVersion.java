import java.util.*;
import java.io.*;

public class Main {
    public static boolean isPrime(int n){
        if(n<=1) return false;
        for(int i = 2; i<=n/2; i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        List<Integer> primes = new ArrayList<>();
        for(int i = 2; i<=200; i++){
            if(isPrime(i)) primes.add(i);
        }
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int cnt1 = 0;
        for(int i = a; i<=b; i++){
            int cnt = 0;
            for(int j = c; j<=d; j++){
                if(primes.contains(i+j)) cnt++;
            }
            if(cnt==0) cnt1++;
        }
        if(cnt1 != 0){
            System.out.println("Takanashi");
        } else{
            System.out.println("Aoki");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner()
        {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {

                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }

        long nextLong() { return Long.parseLong(next()); }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
