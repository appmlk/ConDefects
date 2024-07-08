import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner();
        int n = sc.nextInt();
        int size = 0;
        while ((1 << size) < n) {
            size++;
        }
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 1; i < (1 << size) && i <= n; i++) {
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) > 0) {
                    list.get(j).add(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\n");
        for (ArrayList<Integer> each : list) {
            sb.append(each.size());
            for (int x : each) {
                sb.append(" ").append(x);
            }
            sb.append("\n");
        }
        System.out.print(sb);
        System.out.flush();
        char[] inputs = sc.next().toCharArray();
        int ans = 0;
        for (int i = 0; i < size; i++) {
            if (inputs[size - i - 1] == '1') {
                ans += (1 << i);
            }
        }
        if (ans == 0) {
            ans = n;
        }
        System.out.println(ans);
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