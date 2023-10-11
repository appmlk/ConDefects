import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner();
        int n = sc.nextInt();
        int mm = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
            graph.add(new ArrayList<>());
        }
        long[] org = new long[n];
        for (int i = 0; i < mm; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
            org[a] += values[b];
            org[b] += values[a];
        }
        long left = -1;
        long right = Long.MAX_VALUE / 2;
        while (right - left > 1) {
            long[] costs = (long[])org.clone();
            boolean[] selected = new boolean[n];
            ArrayDeque<Integer> deq = new ArrayDeque<>();
            long m = (left + right) / 2;
            int count = n;
            for (int i = 0; i < n; i++) {
                if (costs[i] <= m) {
                    deq.add(i);
                    selected[i] = true;
                    count--;
                }
            }
            while (deq.size() > 0 && count > 0) {
                int x = deq.poll();
                for (int y : graph.get(x)) {
                    if (!selected[y]) {
                        costs[y] -= values[x];
                        if (costs[y] <= m) {
                            deq.add(y);
                            selected[y] = true;
                            count--;
                        }
                    }
                }
            }
            if (count == 0) {
                right = m;
            } else {
                left = m;
            }
        }
        System.out.println(right);
    }
} 
class Utilities {
    static String arrayToLineString(Object[] arr) {
        return Arrays.stream(arr).map(x -> x.toString()).collect(Collectors.joining("\n"));
    }
    
    static String arrayToLineString(int[] arr) {
        return String.join("\n", Arrays.stream(arr).mapToObj(String::valueOf).toArray(String[]::new));
    }
}
class Scanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");
    StringBuilder sb = new StringBuilder();
    
    public Scanner() throws Exception {
        
    }
    
    public int nextInt() throws Exception {
        return Integer.parseInt(next());
    }
    
    public long nextLong() throws Exception {
        return Long.parseLong(next());
    }
    
    public double nextDouble() throws Exception {
        return Double.parseDouble(next());
    }
    
    public int[] nextIntArray() throws Exception {
        return Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
    
    public String next() throws Exception {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
    
}