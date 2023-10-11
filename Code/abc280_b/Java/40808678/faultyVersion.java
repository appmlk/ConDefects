import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");
        br.close();

        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(strs[i]);
        }

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                a[0] = s[0];
                continue;
            }
            a[i] = s[i] - a[i - 1];
        }

        for (int num : a) {
            System.out.print(num + " ");
        }
    }
}