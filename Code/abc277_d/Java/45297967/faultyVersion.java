import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        TreeMap<Integer, Long> counts = new TreeMap<>();
        long total = 0;
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            total += x;
            counts.put(x, counts.getOrDefault(x, 0L) + 1);
            counts.put(x + m, counts.getOrDefault(x + m, 0L) + 1);
        }
        Integer key = -1;
        long ans = 0;
        while (key < m) {
            key = counts.higherKey(key);
            long current = (key % m) * counts.get(key);
            while (counts.containsKey(key + 1)) {
                key++;
                current += (key % m) * counts.get(key);
            }
            ans = Math.max(ans, current);
        }
        System.out.println(total - ans);
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