import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner();

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[][] Choco = new int[N][2];
        int[][] Box = new int[M][2];
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < N; i++)
                Choco[i][j] = scanner.nextInt();
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < M; i++)
                Box[i][j] = scanner.nextInt();
        }

        Arrays.sort(Choco, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(Box, Comparator.comparingInt(o -> o[0]));

        TreeMap<Integer,Integer> longerHeightBox = new TreeMap<Integer,Integer>();
        int i = N-1, j = M-1;
        boolean flag = true;
        while (i>=0) {
            while (j>=0 && Box[j][0] >= Choco[i][0]) {
                final int finalJ = j;
                longerHeightBox.merge(Box[j][1], 1, (old, it) -> Box[finalJ][1] + 1);
                j--;
            }
            Integer lower_bound = longerHeightBox.ceilingKey(Choco[i][1]);
            if (lower_bound == null) {
                flag = false;
                break;
            }
            longerHeightBox.put(lower_bound, longerHeightBox.get(lower_bound) - 1);
            if (longerHeightBox.get(lower_bound) == 0) {
                longerHeightBox.remove(lower_bound);
            }
            i--;
        }
        System.out.println(flag ? "Yes" : "No");

    }
}

@SuppressWarnings("unused")
class Scanner {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String[] buffer;
    private int pointer = 0;

    public Scanner() {
        readLine();
    }

    public int nextInt() {
        if (!hasNext()) throw new NoSuchElementException();
        return Integer.parseInt(buffer[pointer++]);
    }

    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        return Long.parseLong(buffer[pointer++]);
    }

    public double nextDouble() {
        if (!hasNext()) throw new NoSuchElementException();
        return Double.parseDouble(buffer[pointer++]);
    }

    public String nextString() {
        if (!hasNext()) throw new NoSuchElementException();
        return buffer[pointer++];
    }

    public int[] nextIntArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = nextInt();
        }

        return result;
    }

    public long[] nextLongArray(int size) {
        long[] result = new long[size];
        for (int i = 0; i < size; i++) {
            result[i] = nextInt();
        }

        return result;
    }

    public double[] nextDoubleArray(int size) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = nextInt();
        }

        return result;
    }

    private boolean hasNext() {
        String[] buffer = this.buffer;
        if (buffer == null) return false;
        if (pointer == buffer.length) return readLine();
        return true;
    }

    private boolean readLine() {
        try {
            String line = reader.readLine();
            if (line == null) {
                this.buffer = null;
                return false;
            }
            this.buffer = line.split(" ");
            pointer = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}