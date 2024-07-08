import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        long a = input.nextLong();
        long b = input.nextLong();
        long[] arr = exgcd(a, b);
        if (2 % arr[0] != 0) out.println(-1);
        else{
            out.println(-arr[2] / arr[0] * 2+ " " + arr[1] / arr[0] * 2);
        }
        out.flush();
        out.close();
        br.close();
    }
    public static long[] exgcd(long a, long b){
        if (b == 0) return new long[]{a, 1, 0};
        long[] arr = exgcd(b, a % b);
        long k = a / b;
        return new long[]{arr[0], arr[2], arr[1] - k * arr[2]};
    }


    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static Input input = new Input(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Input {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public Input(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public char[] nextChars(){return next().toCharArray();}
        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
