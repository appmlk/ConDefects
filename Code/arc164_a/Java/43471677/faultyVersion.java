import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner();
        ArrayList<Long> threes = new ArrayList<>();
        long x = 1;
        while (x <= 1000000000000000000L) {
            threes.add(x);
            x *= 3;
        }
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            long n = sc.nextLong();
            long k = sc.nextLong();
            long count = 0;
            for (int i = threes.size() - 1; i >= 0; i--) {
                count += n / threes.get(i);
                n %= threes.get(i);
            }
            if (k % 2  == count % 2) {
                sb.append("Yes\n");
            } else {
                sb.append("No\n");
            }
        }
        System.out.print(sb);
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