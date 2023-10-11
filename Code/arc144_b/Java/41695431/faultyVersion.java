import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner();
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }
        int left = 0;
        int right = Integer.MAX_VALUE / 2;;
        while (right - left > 1) {
            int m = (left + right) / 2;
            int count = 0;
            for (int x : values) {
                if (x <= m) {
                    count += (m - x + a - 1) / a;
                } else {
                    count -= (x - m) / b;
                }
            }
            if (count <= 0) {
                left = m;
            } else {
                right = m;
            }
        }
        System.out.println(left);
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