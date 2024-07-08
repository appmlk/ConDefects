import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        int t = 1;
        //t = nextInt();
        while (t-- > 0) {
            solve();
        }
        close();
    }

    private static void solve() {
        int n = nextInt();
        int[] cnt = new int[10];
        char[] a = nextCharArr();

        for (int i = 0; i < n; i++) {
            cnt[a[i] - '0']++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 9; i >= 0; i--) {
            if (cnt[i] > 0) {
                sb.append(String.valueOf(i).repeat(cnt[i]));
            }
        }

        long num = Long.parseLong(sb.toString());

        int ans = 0;

        for (long i = 0; i * i <= num; i++) {
            int[] cnt2 = new int[10];
            for (char c : String.valueOf(i * i).toCharArray()) {
                cnt2[c - '0']++;
            }

            boolean ok = true;

            for (int j = 1; j < 10; j++) {
                if (cnt[j] != cnt2[j]) {
                    ok = false;
                }
            }

            if (ok) {
                // println(String.valueOf(i * i));
                ans++;
            }
        }

        println(ans);
    }
































    private static final int INF = 1_000_000_000;

    private static class MultiSet<T> {
        private HashMap<T, Integer> storage;
        private int size;

        public MultiSet() {
            storage = new HashMap<>();
            size = 0;
        }

        public void add(T el) {
            storage.merge(el, 1, Integer::sum);
            size++;
        }

        public void add(T el, int cnt) {
            storage.merge(el, cnt, Integer::sum);
            size += cnt;
        }

        public void remove(T el) {
            if (storage.containsKey(el)) {
                int newCnt = storage.merge(el, -1, Integer::sum);

                size--;
                if (newCnt == 0) {
                    storage.remove(el);
                }
            }
        }

        public int count(T el) {
            if (storage.containsKey(el)) {
                return storage.get(el);
            } else {
                return 0;
            }
        }

        public int size() {
            return storage.size();
        }

        public boolean isEmpty() {
            return storage.isEmpty();
        }

        public boolean contains(T el) {
            return storage.containsKey(el);
        }

        @Override
        public String toString() {
            return storage.toString();
        }
    }

    private static class TreeMultiSet<T> {
        private TreeMap<T, Integer> storage;
        private int size;

        public TreeMultiSet() {
            storage = new TreeMap<>();
            size = 0;
        }

        public TreeMultiSet(Comparator<T> comp) {
            storage = new TreeMap<>(comp);
            size = 0;
        }

        public void add(T el) {
            storage.merge(el, 1, Integer::sum);
            size++;
        }

        public void add(T el, int cnt) {
            storage.merge(el, cnt, Integer::sum);
            size += cnt;
        }

        public void remove(T el) {
            if (storage.containsKey(el)) {
                int newCnt = storage.merge(el, -1, Integer::sum);

                size--;
                if (newCnt == 0) {
                    storage.remove(el);
                }
            }
        }

        public void removeAll(T el) {
            storage.remove(el);
        }

        public T pollLast() {
            T key = storage.lastKey();
            remove(key);
            return key;
        }

        public T pollFirst() {
            T key = storage.firstKey();
            remove(key);
            return key;
        }

        public int count(T el) {
            if (storage.containsKey(el)) {
                return storage.get(el);
            } else {
                return 0;
            }
        }

        public T first() {
            return storage.firstKey();
        }

        public T last() {
            return storage.lastKey();
        }

        public T higher(T el) {
            return storage.higherKey(el);
        }

        public T lower(T el) {
            return storage.lowerKey(el);
        }

        public T floor(T el) {
            return storage.floorKey(el);
        }

        public T ceiling(T el) {
            return storage.ceilingKey(el);
        }

        public int size() {
            return storage.size();
        }

        public boolean isEmpty() {
            return storage.isEmpty();
        }

        public boolean contains(T el) {
            return storage.containsKey(el);
        }

        @Override
        public String toString() {
            return storage.toString();
        }
    }

    private static BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static PrintWriter pw = new PrintWriter(System.out);

    private static String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    private static String nextLine() {
        String s = "";
        try {
            if (st != null && st.hasMoreTokens())
                s = st.nextToken("\n");
            else
                s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static char[] nextCharArr() {
        return nextLine().toCharArray();
    }

    private static int nextInt() {
        return Integer.parseInt(next());
    }

    private static int[] nextIntArr(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nextInt();
        }
        return res;
    }

    private static int[] nextIntArr(int n, int offset) {
        int[] res = new int[n + offset];
        for (int i = offset; i < res.length; i++) {
            res[i] = nextInt();
        }
        return res;
    }

    private static int[][] nextInt2dArr(int rows, int cols) {
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = nextInt();
            }
        }
        return res;
    }

    private static long[] nextLongArr(int n) {
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = nextLong();
        }
        return res;
    }

    private static long[] nextLongArr(int n, int offset) {
        long[] res = new long[n + offset];
        for (int i = offset; i < res.length; i++) {
            res[i] = nextLong();
        }
        return res;
    }

    private static long[][] nextLong2dArr(int rows, int cols) {
        long[][] res = new long[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = nextLong();
            }
        }
        return res;
    }

    private static long nextLong() {
        return Long.parseLong(next());
    }

    private static double nextDouble() {
        return Double.parseDouble(next());
    }

    private static void printf(String format, Object... args) {
        pw.printf(format, args);
    }

    private static void print(Object o) {
        pw.print(o);
    }

    private static void println() {
        pw.println();
    }

    private static void println(Object o) {
        pw.println(o);
    }

    private static void println(int[] a) {
        StringBuilder sb = new StringBuilder();

        for (int i : a) {
            sb.append(i);
            sb.append(" ");
        }
        println(sb);
    }

    private static void println(int[][] a) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                sb.append(a[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        print(sb);
    }

    private static void println(long[] a) {
        StringBuilder sb = new StringBuilder();

        for (long i : a) {
            sb.append(i);
            sb.append(" ");
        }
        println(sb);
    }

    private static void println(long[][] a) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                sb.append(a[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        print(sb);
    }

    private static void sort(int[] a) {
        List<Integer> b = new ArrayList<>();
        for (int el : a) {
            b.add(el);
        }
        Collections.sort(b);
        for (int i = 0; i < a.length; i++) {
            a[i] = b.get(i);
        }
    }

    private static void sort(long[] a) {
        List<Long> b = new ArrayList<>();
        for (long el : a) {
            b.add(el);
        }
        Collections.sort(b);
        for (int i = 0; i < a.length; i++) {
            a[i] = b.get(i);
        }
    }

    private static void sort(int[] a, Comparator<Integer> comp) {
        List<Integer> b = new ArrayList<>();
        for (int el : a) {
            b.add(el);
        }
        Collections.sort(b, comp);
        for (int i = 0; i < a.length; i++) {
            a[i] = b.get(i);
        }
    }

    private static void sort(long[] a, Comparator<Long> comp) {
        List<Long> b = new ArrayList<>();
        for (long el : a) {
            b.add(el);
        }
        Collections.sort(b, comp);
        for (int i = 0; i < a.length; i++) {
            a[i] = b.get(i);
        }
    }

    private static <T extends Comparable<T>> void sort(T[] a) {
        List<T> temp = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            temp.add(a[i]);
        }

        Collections.sort(temp);

        for (int i = 0; i < a.length; i++) {
            a[i] = temp.get(i);
        }
    }

    private static <T> void sort(T[] a, Comparator<? super T> comparator) {
        List<T> temp = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            temp.add(a[i]);
        }

        Collections.sort(temp, comparator);

        for (int i = 0; i < a.length; i++) {
            a[i] = temp.get(i);
        }
    }

    private static int[][] copy(int[][] orig) {
        int[][] temp = new int[orig.length][];

        for (int j = 0; j < orig.length; j++) {
            temp[j] = Arrays.copyOf(orig[j], orig[j].length);
        }
        return temp;
    }

    private static void close() {
        pw.flush();
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.close();
    }
}


// Pre-submit:
// Write a few simple test cases if sample is not enough.
// Are time limits close? If so, generate max cases.
// Is the memory usage fine?
// Could anything overflow?
// Make sure to submit the right file.
//
// Wrong answer:
// Print your solution! Print debug output, as well.
// Are you clearing all data structures between test cases?
// Can your algorithm handle the whole range of input?
// Read the full problem statement again.
// Do you handle all corner cases correctly?
// Have you understood the problem correctly?
// Any uninitialized variables?
// Any overflows?
// Confusing N and M, i and j, etc.?
// Are you sure your algorithm works?
// What special cases have you not thought of?
// Are you sure the STL functions you use work as you think?
// Add some assertions, maybe resubmit.
// Create some testcases to run your algorithm on.
// Go through the algorithm for a simple case.
// Go through this list again.
// Explain your algorithm to a teammate.
// Ask the teammate to look at your code.
// Go for a small walk, e.g. to the toilet.
// Is your output format correct? (including whitespace)
// Rewrite your solution from the start or let a teammate do it.
//
// Runtime error:
// Have you tested all corner cases locally?
// Any uninitialized variables?
// Are you reading or writing outside the range of any vector?
// Any assertions that might fail?
// Any possible division by 0? (mod 0 for example)
// Any possible infinite recursion?
// Invalidated pointers or iterators?
// Are you using too much memory?
// Debug with resubmits (e.g. remapped signals, see Various).
//
// Time limit exceeded:
// Do you have any possible infinite loops?
// What is the complexity of your algorithm?
// Are you copying a lot of unnecessary data? (References)
// How big is the input and output? (consider scanf)
// Avoid vector, map. (use arrays/unordered_map)
// What do your teammates think about your algorithm?
//
// Memory limit exceeded:
// What is the max amount of memory your algorithm should need?
// Are you clearing all data structures between test cases?
