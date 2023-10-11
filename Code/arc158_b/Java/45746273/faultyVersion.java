import java.io.*;
import java.util.*;


public class Main {
    static FastIn in = new FastIn();
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static final double eps = 1e-8;
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    //----------------------------------TEMPLATE-----------------------------------------------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //TODO 记得必要时开long或BigInteger


    //int T = in.nextInt();



    void solve() {
        int n = in.nextInt();
        List<Double> list = new ArrayList<>();
        double mx = -1e18;
        double mi = 1e18;
        List<Double> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double v = in.nextInt();
            if (v > 0) list1.add(v);
            else list2.add(v);
        }

        if (list1.size() > 6) {
            for (int i = 0; i < 3; i++) {
                list.add(list1.get(i));
            }
            for (int i = list1.size() - 1; i >= list1.size() - 3; i--) {
                list.add(list1.get(i));
            }
        }else {
            list.addAll(list1);
        }
        if (list2.size() > 6) {
            for (int i = 0; i < 3; i++) {
                list.add(list2.get(i));
            }
            for (int i = list2.size() - 1; i >= list2.size() - 3; i--) {
                list.add(list2.get(i));
            }
        }else {
            list.addAll(list2);
        }


        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    mi = Math.min(mi, (list.get(i) + list.get(j) + list.get(k)) / (list.get(i) * list.get(j) * list.get(k)));
                    mx = Math.max(mx, (list.get(i) + list.get(j) + list.get(k)) / (list.get(i) * list.get(j) * list.get(k)));
                }
            }
        }
        out.printf("%.15f\n", mi);
        out.printf("%.15f\n", mx);

    }

    double calc(double x, double y, double z) {
        return (x + y + z) / (x * y * z);
    }






    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String... args) {
        new Main().solve();
        out.close();
    }
}

class FastIn extends In {
    private final BufferedInputStream reader = new BufferedInputStream(System.in);
    private final byte[] buffer = new byte[0x10000];
    private int i = 0;
    private int length = 0;

    public int read() {
        if (i == length) {
            i = 0;
            try {
                length = reader.read(buffer);
            } catch (IOException ignored) {
            }
            if (length == -1) {
                return 0;
            }
        }
        if (length <= i) {
            throw new RuntimeException();
        }
        return buffer[i++];
    }

    String next() {
        StringBuilder builder = new StringBuilder();
        int b = read();
        while (b < '!' || '~' < b) {
            b = read();
        }
        while ('!' <= b && b <= '~') {
            builder.appendCodePoint(b);
            b = read();
        }
        return builder.toString();
    }

    String nextLine() {
        StringBuilder builder = new StringBuilder();
        int b = read();
        while (b != 0 && b != '\r' && b != '\n') {
            builder.appendCodePoint(b);
            b = read();
        }
        if (b == '\r') {
            read();
        }
        return builder.toString();
    }

    int nextInt() {
        long val = nextLong();
        if ((int) val != val) {
            throw new NumberFormatException();
        }
        return (int) val;
    }

    @Override
    long nextLong() {
        int b = read();
        while (b < '!' || '~' < b) {
            b = read();
        }
        boolean neg = false;
        if (b == '-') {
            neg = true;
            b = read();
        }
        long n = 0;
        int c = 0;
        while ('0' <= b && b <= '9') {
            n = n * 10 + b - '0';
            b = read();
            c++;
        }
        if (c == 0 || c >= 2 && n == 0) {
            throw new NumberFormatException();
        }
        return neg ? -n : n;
    }
}

class In {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 0x10000);
    private StringTokenizer tokenizer;

    String next() {
        try {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
        } catch (IOException ignored) {
        }
        return tokenizer.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

}
