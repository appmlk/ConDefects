import java.util.*;
import java.io.*;

public class Main {
    static FastScanner sc = new FastScanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static StringBuilder sb = new StringBuilder();
    static long mod = (long) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        solve();
        pw.flush();
    }

    public static void solve() {
        int N = sc.nextInt();
        ArrayList<Long> arr = new ArrayList<>();
        arr.add(0L);arr.add(0L);
        long[] a = sc.nextLongArray(N);
        int ai = 1;
        for(int i = 0; i < N; i++){
            arr.add(a[i]-arr.get(ai)-arr.get(ai-1));
            ai++;
        }
        long v1 = 0;
        for(int i = 0; i < arr.size(); i += 3){
            v1 = Math.max(v1,-arr.get(i));
        }
        long v2 = 0;
        for(int i = 1; i < arr.size(); i += 3){
            v2 = Math.max(v2,-arr.get(i));
        }
        long v3 = Long.MAX_VALUE;
        for(int i = 2; i < arr.size(); i += 3){
            v3 = Math.min(v3,arr.get(i));
        }
        if(v1 + v2 > v3){
            pw.println("No");
            return;
        }
        //pw.println(v1 + " " + v2 + " " + v3);
        for(int i = 0; i < N+2; i++){
            if(i % 3 == 0){
                arr.set(i,arr.get(i)+v1);
            }else if(i % 3 == 1){
                arr.set(i,arr.get(i)+v2);
            }else{
                arr.set(i,arr.get(i)-v1-v2);
            }
        }
        pw.println("Yes");
        pw.println(arrToString(arr));
    }
    
    private static String arrToString(ArrayList<Long> arr){
        StringBuilder printer = new StringBuilder();
        for(long v : arr) printer.append(v).append(" ");
        return printer.toString().trim();
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
