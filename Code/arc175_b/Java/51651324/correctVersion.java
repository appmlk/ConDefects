import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        st = new StringTokenizer(reader.readLine());
        String S = st.nextToken();
        int open = 0;
        int sum = 0;
        int minSum = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                open++;
                sum++;
            } else {
                sum--;
            }
            minSum = Math.min(minSum, sum);
        }
        if (open >= N) {
            writer.write(Long.toString(B * (open - N) + Math.min(A, 2 * B) * ((Math.abs(minSum) + 1) / 2)));
        } else {
            writer.write(Long.toString(B * (N - open) + Math.min(A, 2 * B) * ((2L * (open - N) - minSum + 1) / 2)));
        }
        writer.newLine();
        writer.flush();
    }
}
