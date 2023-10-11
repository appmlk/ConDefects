import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        fs = new FastReader();
        out = new PrintWriter(System.out);
        int n = fs.nextInt();
        int ar[] = fs.readArray(n);
        int arr[] = Arrays.copyOf(ar, n);
        ruffleSort(ar);
        int p = 0;
        int pn = 0, k = 0;
        for (int i = 1; i <= n; ++i) {
            p += arr[i - 1] >= ar[n / 2] ? -1 : 1;
            if (p < pn) {
                pn = p;
                k = i;
            }
        }
        out.print(k + " ");
        long ans = 0;
        for (int i = n / 2; i < n; ++i) {
            ans += ar[i];
        }
        out.println(ans);
        out.close();
    }

    public static PrintWriter out;
    public static FastReader fs;
    public static final Random random = new Random();

    public static void ruffleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            int oi = random.nextInt(n), tmp = a[oi];
            a[oi] = a[i];
            a[i] = tmp;
        }
        Arrays.sort(a);
    }

    public static class FastReader {
        private BufferedReader br;
        private StringTokenizer st = new StringTokenizer("");

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String file_name) throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file_name))));
        }

        public String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; ++i)
                a[i] = nextInt();
            return a;
        }

        public String nextLine() {
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
