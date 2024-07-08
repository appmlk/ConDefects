import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.*;

public class Main {
    //static Reader sc = new Reader();
    static Scanner sc = new Scanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);
    //static Debugger debug = new Debugger();
    //static Debug debug = new Debug();
    //static int mod = (int)(1e9 + 7);
    static int mod = 998244353;

    /**Code Starts From Here**/
    public static void main(String[] args) throws IOException {
        int t = 1;
        //int t = sc.nextInt();
        while (t-- > 0) {
            Attack();
        }
        sc.close();
        out.flush();
    }

    public static void Attack() throws IOException {
        int n = sc.nextInt();
        ArrayList<Pair> res = new ArrayList<Pair>();
        for(int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            res.add(new Pair(start, end));
        }
        Collections.sort(res, new sorting());
        long total = 0;
        for(int i = 0; i < res.size() - 1; i++) {
            Pair current = res.get(i);
            int ans = binary_search(current, i + 1, res.size() - 1, res);
            if(ans == -1) continue;
            total += (ans - i);
        }
        out.println(total);
    }

    public static int binary_search(Pair current, int s, int e, ArrayList<Pair> res) {
        int low = s;
        int high = e;
        int ans = -1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(res.get(mid).start <= current.end) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }

    static class Pair {
        int start;
        int end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class sorting implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(first.start, second.start);
            if(op1 != 0) return op1;
            return Integer.compare(first.end, second.end);
        }
    }


    /**Code Ends Here (No Need To Scroll Down)**/
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public String readLine() throws IOException {
            byte[] buf = new byte[64];
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {ret = ret * 10 + c - '0';}
            while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }
        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {ret = ret * 10L + c - '0';}
            while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }
        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {ret = ret * 10 + c - '0';}
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') while ((c = read()) >= '0' && c <= '9') ret += (c - '0') / (div *= 10);
            if (neg) return -ret;
            return ret;
        }
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }
        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }
        public char[] next() throws IOException {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) <= ' ') ;
            do {sb.append((char) c);}
            while ((c = read()) > ' ');
            return sb.toString().toCharArray();
        }
        public int nextInt2() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {ret = ret * 10 + c - '0';}
            while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }
        public void close() throws IOException {
            if (din == null) return;
            din.close();
        }
    }

    static long fast_pow(long a, long p, long mod) {
        long res = 1;
        while (p > 0) {
            if (p % 2 == 0) {
                a = ((a % mod) * (a % mod)) % mod;
                p /= 2;
            }
            else {
                res = ((res % mod) * (a % mod)) % mod;
                p--;
            }
        }
        return res;
    }
    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

    //Factorials and Inverse Factorials;
    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];
    static boolean[] isPrime;
    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }
    static long nCk(int n, int k) {
        //use precompFacts first;
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    //Prime Generator;
    static void Generate_Primes(int upto) {
        // Sieve of Eratosthenes:
        isPrime = new boolean[upto + 1];
        int[] smallestFactorOf = new int[upto + 1];
        Arrays.fill(smallestFactorOf, 1);
        Arrays.fill(isPrime, true);
        isPrime[1] = isPrime[0] = false;
        for (long i = 2; i < upto + 1; i++) {
            if (isPrime[(int) i]) {
                smallestFactorOf[(int) i] = (int) i;
                // Mark all the multiples greater than or equal
                // to the square of i to be false.
                for (long j = i; j * i < upto + 1; j++) {
                    if (isPrime[(int) j * (int) i]) {
                        isPrime[(int) j * (int) i] = false;
                        smallestFactorOf[(int) j * (int) i] = (int) i;
                    }
                }
            }
        }
    }

    static long modinv(long x) {return fast_pow(x, mod - 2, mod);}
    static long add(long a, long b) {a += b; if (a >= mod) a-= mod; return a;}
    static long mul(long a, long b) {return (long) ((long) ((a % mod) * 1L * (b % mod)) % mod);}
    static long Div(long x, long y) {return mul(x, modinv(y));}
    static long mod(long a, long b) {long r = a % b;return r < 0 ? r + b : r;}
    static long sub(long x, long y) {long z = x - y; if (z < 0)  z += mod;return z;}
    static long GCD(long x, long y) {if(y == 0) return x;return GCD(y, x % y);}
    static long LCM(long a, long b) {return (a / GCD(a, b)) * b;}


    public static void sort(int[] arr) {
        //because Arrays.sort() uses quicksort which is dumb
        //Collections.sort() uses merge sort
        ArrayList<Integer> ls = new ArrayList<>();
        for (Integer x : arr) ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i);
    }
    public static void sort(long[] arr) {
        //because Arrays.sort() uses quicksort which is dumb
        //Collections.sort() uses merge sort
        ArrayList<Long> ls = new ArrayList<>();
        for (Long x : arr) ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i);
    }

    static class Unique_Pair {
        int first;
        int second;
        Unique_Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Unique_Pair pair = (Unique_Pair) o;
            return first == pair.first && second == pair.second;
        }
        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    static class MultiSet<T> {
        TreeMap<T, Integer> frequency;
        TreeSet<T> set;
        int size;
        public MultiSet() {
            set = new TreeSet<>();
            frequency = new TreeMap<>();
            size = 0;
        }
        public MultiSet(Comparator<T> cmp) {
            set = new TreeSet<>(cmp);
            frequency = new TreeMap<>(cmp);
            size = 0;
        }
        public void add(T elem) {
            if (frequency.get(elem) == null || frequency.get(elem) == 0) {
                frequency.put(elem, 0);
                set.add(elem);
            }
            frequency.put(elem, frequency.get(elem) + 1);
            size++;
        }
        public void remove(T elem) {
            if (!set.contains(elem)) return;
            frequency.put(elem, frequency.get(elem) - 1);
            if (frequency.get(elem) == 0) set.remove(elem);
            size--;
        }
        public boolean contains(T elem) {
            return set.contains(elem);
        }
    }

    @SuppressWarnings("serial")
    static class CountMap<T> extends TreeMap<T, Integer>{
        CountMap() {}
        CountMap(T[] arr) {this.putCM(arr);}
        public Integer putCM(T key) {
            if (super.containsKey(key)) return super.put(key, super.get(key) + 1);
            else return super.put(key, 1);
        }
        public Integer removeCM(T key) {
            Integer count = super.get(key);
            if (count == null) return -1;
            if (count == 1) return super.remove(key);
            else  return super.put(key, super.get(key) - 1);
        }
        public Integer getCM(T key) {
            Integer count = super.get(key);
            if (count == null) return 0;
            return count;
        }
        public void putCM(T[] arr) {
            for (T ele : arr) this.putCM(ele);
        }
    }
}