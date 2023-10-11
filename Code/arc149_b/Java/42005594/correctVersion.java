import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    static int MOD = (int) 1e9+7;
    public static void main(String[] args) throws IOException {
        int n = readInt();
        List<int[]> prA = new ArrayList<>();
        List<int[]> prB = new ArrayList<>();
        int[] A = new int[n], B = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = readInt();
            prA.add(new int[] {A[i], i});
        }
        for (int i = 0; i < n; i++) {
            B[i] = readInt();
            prB.add(new int[] {B[i], i});
        }
        int ans = 0;
        // A fully sorted
        prA.sort(Comparator.comparingInt(v -> v[0]));
        List<Integer> lst = new ArrayList<>();
        for (int[] v : prA) {
            lst.add(B[v[1]]);
        }
        ans = Math.max(ans, n + LIS(lst));

        // B fully sorted
        prB.sort(Comparator.comparingInt(v -> v[0]));
        lst = new ArrayList<>();
        for (int[] v : prB) {
            lst.add(A[v[1]]);
        }
        ans = Math.max(ans, n + LIS(lst));
        System.out.println(ans);
    }
    static int LIS(List<Integer> a) {
        List<Integer> lst = new ArrayList<>();
        for (int v : a) {
            if (lst.isEmpty() || lst.get(lst.size() - 1) < v) {
                lst.add(v);
            } else {
                int p = Collections.binarySearch(lst, v);
                if (p < 0) p = -p - 1;
                lst.set(p, v);
            }
        }
        return lst.size();
    }
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    static long readLong() throws IOException {
        return Long.parseLong(next());
    }
    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }
    static double readDouble() throws IOException {
        return Double.parseDouble(next());
    }
    static char readCharacter() throws IOException {
        return next().charAt(0);
    }
    static String readLine() throws IOException {
        return br.readLine().trim();
    }
    static int readLongLineInt() throws IOException{
        int x = 0, c;
        while((c = br.read()) != ' ' && c != '\n')
            x = x * 10 + (c - '0');
        return x;
    }
}
