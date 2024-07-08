import java.util.*;
import java.io.*;

/*
    08-06-2024 18:35
*/

 class Main {
    /*-----------------------your solution goes here----------------------*/


    static void solve() throws IOException {
        String s = ns();
        long n = Long.parseLong(s);
        long r = pow(10,s.length());
        long gpSumNumerator = (pow(r,n)-1+mod)%mod;  // gpSum = a*(r^n-1)/(r-1);
        long gpSumDenominator = pow(((r-1+mod)%mod),mod-2);

        long GPSum = (gpSumNumerator * gpSumDenominator)%mod;
        long ans = ((n%mod) * GPSum)%mod; // since n has taken as common so multiply it with GP sum.
        pl(ans);
    }






    /*-------------------------------Main Method-----------------------------*/

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        long initialMemoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        boolean localFileReader = fileReader();
        int testcase = 1;
        while (testcase-- > 0) {
            solve();
        }

        if (localFileReader) {
            timeAndMemoryDetails(startTime, initialMemoryUsed);
        }
        close();
    }


    /*----------------------------Time and Memory------------------------------*/

    static void timeAndMemoryDetails(long startTime, long initialMemory) {
        final long MEGABYTE = 1024L * 1024L;
        final long millisecond = 1000_000L;
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        p("--------------------------\nExecution Time :");
        p((System.nanoTime() - startTime) / millisecond);
        p("ms \nMemory used    :");
        p((afterUsedMem - initialMemory) / MEGABYTE);
        pl("MB");
    }


    /*------------------------------Variables -----------------------------*/
    final static int imax = Integer.MAX_VALUE;
    final static int i_min = Integer.MIN_VALUE;
    final static long lmax = Long.MAX_VALUE;
    final static long l_min = Long.MIN_VALUE;
    final static int mod = 998244353;
    final static int maxArraySize = 2_000_001;
    static long[] fact;


    /*--------------------------------------FastIO Class-------------------*/
    static final private int BUFFER_SIZE = 1 << 16;
    static private DataInputStream din = new DataInputStream(System.in);
    static private final byte[] buffer = new byte[BUFFER_SIZE];
    static private int bufferPointer, bytesRead;


    /*-----------------local file reader ---------------------------------------*/
    public static boolean fileReader() throws IOException {
        String File = "someFile";
        try {
            File = System.getProperty("user.dir");
        } catch (Exception ignored) {
        }
        if (new File(File).getName().equals("CP")) {
            din = new DataInputStream(new FileInputStream("src/Input.txt"));
            out = new PrintWriter(new FileOutputStream("src/Output.txt"));
            return true;
        }
        return false;
    }

    /*-----------------------input methods-----------------------------------------*/
    static String next() throws IOException {
        StringBuilder buf = new StringBuilder();
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == ' ' || c == '\n' || c == '\r' || c == '\0') {
                if (cnt == 0) continue;
                else break;
            } else
                buf.append((char) c);
            cnt++;
        }
        return buf.toString();
    }

    static char nc() throws IOException {
        int c;
        while ((c = read()) == '\n' || c == '\0' || c == '\r' || c == ' ') ;
        return (char) c;
    }

    static char[] nca(int n) throws IOException {
        char[] a = new char[n];
        for (int i = 0; i < n; i++) {
            a[i] = nc();
        }
        return a;
    }


    static int ni() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg)
            return -ret;
        return ret;
    }

    static long nl() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg)
            return -ret;
        return ret;
    }

    static double nd() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();

        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg)
            return -ret;
        return ret;
    }


    static String ns() throws IOException {
        return next();
    }

    static public String nextLine() throws IOException {
        StringBuilder buf = new StringBuilder();
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n' || c == '\r' || c == '\0') {
                if (cnt != 0) break;
                else continue;
            } else
                buf.append((char) c);
            cnt++;
        }
        return buf.toString();
    }

    static int[] nia(int n) throws IOException {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = ni();
        }
        return a;
    }

    static long[] nla(int n) throws IOException {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nl();
        }
        return a;
    }

    static double[] nda(int n) throws IOException {
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = nd();
        }
        return a;
    }

    static String[] nsa(int n) throws IOException {
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = ns();
        }
        return s;
    }

    static private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0,
                BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }

    static private byte read() throws IOException {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }


    /*---------------------------------output methods-----------------------------------------------*/

    static PrintWriter out = new PrintWriter(System.out);

    /**
     * print single space.
     */
    static void ps() {
        out.print(' ');
    }

    static void pl(int n) {
        out.println(n);
    }

    static void pl(long n) {
        out.println(n);
    }

    static void pl(char c) {
        out.println(c);
    }

    static void pl(double n) {
        out.println(n);
    }

    static void pl(String s) {
        out.println(s);
    }

    /**
     * print YES for true and NO for false.
     */
    static void pl(boolean flag) {
        pl(flag ? "YES" : "NO");
    }

    /**
     * Print new line
     */
    static void PL() {
        out.println();
    }

    static <E> void pl(List<E> a) {
        for (E e : a) {
            p(e);
        }
        PL();
    }

    static void pl(Object o) {
        out.println(o);
    }

    static <E> void p(List<E> a) {
        for (E e : a) {
            p(e);
        }
    }

    static void p(int n) {
        out.print(n);
        ps();
    }

    static void p(long n) {
        out.print(n);
        ps();
    }

    static void p(double d) {
        out.print(d);
        ps();
    }

    static void p(char c) {
        out.print(c);
        ps();
    }

    static void p(String s) {
        out.print(s);
        ps();
    }

    static void p(Object o) {
        out.print(o);
        ps();
    }

    static void pl(int[] a) {
        for (int e : a) {
            p(e);
        }
        PL();
    }

    static void pl(long[] a) {
        for (long e : a) {
            p(e);
        }
        PL();
    }

    static void pl(String[] a) {
        for (String e : a) {
            p(e);
        }
        PL();
    }

    static void pl(char[] a) {
        for (char e : a) {
            p(e);
        }
        PL();
    }

    static void pl(double[] a) {
        for (double e : a) {
            p(e);
        }
        PL();
    }

    static void pl(boolean[] a) {
        for (boolean e : a) {
            out.print(e);
            ps();
        }
        PL();
    }

    static void pl(Object[] a) {
        for (Object e : a) {
            p(e);
        }
        PL();
    }

    static void pl(int[][] a) {
        for (int[] e : a) {
            pl(e);
        }
    }

    static void pl(long[][] a) {
        for (long[] e : a) pl(e);
    }

    static void pl(double[][] a) {
        for (double[] e : a) {
            pl(e);
        }
    }

    static void pl(char[][] a) {
        for (char[] e : a) pl(e);
    }

    static void pl(Object[][] s) {
        for (Object[] e : s) pl(e);
    }

    /**
     * Closes the stream and releases any system resources associated with it.
     * Closing a previously closed stream has no effect.
     */
    static void close() throws IOException {
        if (din != null) din.close();
        out.close();
    }




    /*---------------------------------Frequently used methods------------------------------------------*/

    static void reverse(int[] a) {
        int n = a.length - 1, temp;
        for (int i = 0; i < (n + 1) / 2; i++) {
            temp = a[i];
            a[i] = a[n - i];
            a[n - i] = temp;
        }
    }

    static void reverse(long[] a) {
        long temp;
        int n = a.length - 1;
        for (int i = 0; i < (n + 1) / 2; i++) {
            temp = a[i];
            a[i] = a[n - i];
            a[n - i] = temp;
        }
    }

    static void reverse(double[] a) {
        double temp;
        int n = a.length - 1;
        for (int i = 0; i < (n + 1) / 2; i++) {
            temp = a[i];
            a[i] = a[n - i];
            a[n - i] = temp;
        }
    }

    static double rootX(double a, double b, double c) {
        double d = (b * b) - 4 * a * c;
        return (Math.sqrt(d) - b) / (2 * a);
    }

    static double rootY(double a, double b, double c) {
        double d = (b * b) - 4 * a * c;
        return (-Math.sqrt(d) - b) / (2 * a);
    }

    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    static int upperBound(int[] a, int key) {
        int low = 0, high = a.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] >= key) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;
    }

    static int lowerBound(int[] a, int key) {
        int low = 0, high = a.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= key) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return ans;
    }

    static <Number extends Comparable<Number>> int lowerBound(List<Number> a, Number key) {
        int low = 0, high = a.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a.get(mid).compareTo(key) <= 0) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return ans;
    }

    static <Number extends Comparable<Number>> int upperBound(List<Number> a, Number key) {
        int low = 0, high = a.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a.get(mid).compareTo(key) >= 0) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        int i = 1;
        while (++i <= Math.sqrt(n))
            if (n % i == 0) return false;
        return true;
    }//return true or false for prime check

    static boolean isPrime(long l) {
        if (l < 2) return false;
        long i = 1;
        while (++i <= Math.sqrt(l))
            if (l % i == 0) return false;
        return true;
    }

    static long pow(long a, long n) {
        long ans = 1L;
        while (n > 0L) {
            if ((n & 1L) != 0L) {
                ans = ((ans % mod) * a) % mod;
            }
            a = (a * a) % mod;
            n >>= 1L;
        }
        return ans % mod;
    }

    static long pow(long a, int n) {
        long ans = 1L;
        while (n > 0) {
            if ((n & 1L) != 0) {
                ans = ((ans % mod) * a) % mod;
            }
            a = (a * a) % mod;
            n >>= 1;
        }
        return ans % mod;
    }

    static String reverse(String s) {
        StringBuilder reversedString = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            reversedString.append(s.charAt(i));
        }
        return reversedString.reverse().toString();
    }//Reverse the string;

    static long nCr(long n, long r) {
        if ((n - r) < r) {
            return nCr(n, n - r);
        }
        long ans = 1;
        for (long i = 1; i <= r; i++, n--) {
            if (n % i == 0) {
                ans = n / i * ans;
            } else if (ans % i == 0) {
                ans = ans / i * n;
            } else {
                ans = (ans * n) / i;
            }
        }
        return ans;
    }

    static long rootFloor(long n) {
        double tentativeRoot = Math.sqrt(n);
        long low = (long) tentativeRoot - 3;
        long ans = 0;
        long high = low + 6;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if ((mid * mid) <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    static long rootHigher(long n) {
        return rootFloor(n) + 1;
    }

    static long rootCeil(long n) {
        double tentativeRoot = Math.sqrt(n);
        long low = (long) tentativeRoot - 3;
        long ans = 0;
        long high = low + 6;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if ((mid * mid) >= n) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    static long rootLower(long n) {
        return rootCeil(n) - 1;
    }

    static long inv(long val) {
        return pow(val, mod - 2);
    }

    static long nCrWithMod(int n, int r) {
        return ((fact[n] * inv(fact[r]) % mod) * inv(fact[n - r])) % mod;
    }

    static void factorial() {
        fact = new long[maxArraySize];
        fact[0] = 1;
        for (int i = 1; i < maxArraySize; i++) {
            fact[i] = (fact[i - 1] * i) % mod;
        }
    }
}
