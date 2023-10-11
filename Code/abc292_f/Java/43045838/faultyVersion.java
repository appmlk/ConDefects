import java.util.*;
import java.io.*;

public class Main {
    static FastScanner sc = new FastScanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static StringBuilder sb = new StringBuilder();
    static long mod = 998244353;

    public static void main(String[] args) throws Exception {
        solve();
        pw.flush();
    }
    
    public static void solve(){
        double A = sc.nextDouble();
        double B = sc.nextDouble();
        if(A > B){
            double tmp = A;
            A = B;
            B = tmp;
        }
        double ans = 0d;
        double left = 0d;
        double right = 30.1d;
        double diff = 1e-8;
        while(right - left > diff){
            double i = (right+left)/2d;
            double rad = Math.toRadians(i);
            double len = A/Math.cos(rad);
            double height = len*Math.sin(rad);
            double rad2 = Math.toRadians(120d-(90d-i));
            double height2 = len*Math.cos(rad2);
            if(height+height2 > B){
                right = i;
            }else{
                ans = Math.max(ans,len);
                left = i;
            }
            
        }
        pw.println(ans);
    }

    static class GeekInteger {
        public static void save_sort(int[] array) {
            shuffle(array);
            Arrays.sort(array);
        }

        public static int[] shuffle(int[] array) {
            int n = array.length;
            Random random = new Random();
            for (int i = 0, j; i < n; i++) {
                j = i + random.nextInt(n - i);
                int randomElement = array[j];
                array[j] = array[i];
                array[i] = randomElement;
            }
            return array;
        }

        public static void save_sort(long[] array) {
            shuffle(array);
            Arrays.sort(array);
        }

        public static long[] shuffle(long[] array) {
            int n = array.length;
            Random random = new Random();
            for (int i = 0, j; i < n; i++) {
                j = i + random.nextInt(n - i);
                long randomElement = array[j];
                array[j] = array[i];
                array[i] = randomElement;
            }
            return array;
        }

    }
}

class BIT {
    int size;
    long[] bit;

    public BIT(long[] b) {
        this.bit = b;
        this.size = b.length;
    }

    public BIT(int sz) {
        this.size = sz + 2;
        this.bit = new long[size + 2];
    }

    void add(int pos, long x) {
        pos++;
        while (pos <= size) {
            bit[pos] += x;
            pos += (pos & -pos);
        }
    }

    long sum(int pos) {
        long s = 0;
        pos++;
        while (pos > 0) {
            s += bit[pos];
            pos -= (pos & -pos);
        }
        return s;
    }
}

class FastScanner {
    private BufferedReader reader = null;
    private StringTokenizer tokenizer = null;

    public FastScanner(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = null;
    }

    public FastScanner(FileReader in) {
        reader = new BufferedReader(in);
        tokenizer = null;
    }

    public String next() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public String nextLine() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken("\n");
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String[] nextArray(int n) {
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = next();
        return a;
    }

    public int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = nextInt();
        return a;
    }

    public long[] nextLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = nextLong();
        return a;
    }
}