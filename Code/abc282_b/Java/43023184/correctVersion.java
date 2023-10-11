import java.util.StringTokenizer;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main{
    public static void main(String[] args)throws Exception{
        FastReader fr = new FastReader();
        int n = fr.nextInt();
        int m = fr.nextInt();
        int ans = 0;
        String[] s = new String[n];

        for (int i = 0; i < n; i++){
            s[i] = fr.next();   
        }
        fr.close();

        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
              	boolean bool = true;
                for (int k = 0; k < m; k++){
                    if (s[i].charAt(k) == 'x' && s[j].charAt(k) == 'x'){
                    bool = false;
                }
                }
                if (bool == true) ans ++;
            }
        }
        System.out.println(ans);
    }
}

class FastReader {
    BufferedReader br = null;
    StringTokenizer st = new StringTokenizer("");

    public FastReader() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
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

    String nextLine() throws IOException {
        return br.readLine();
    }
    public void close() throws Exception {
        br.close();
    }
}