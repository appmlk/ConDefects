import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int[][] a = new int[h][w];
        int[][] b = new int[h][w];

        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                a[i][j] = sc.nextInt();

        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                b[i][j] = sc.nextInt();

        int[] p = new int[h];
        for (int i = 0; i < h; i++) p[i] = i;
        int[] q = new int[w];
        for (int i = 0; i < w; i++) q[i] = i;
        do {
            do {
                boolean ok = true;
                label:
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (a[p[i]][q[j]] != b[i][j]) {
                            ok = false;
                            break label;
                        }
                    }
                }

                if (ok) {
                    int ans = 0;
                    for (int i = 0; i < h; i++) {
                        for (int j = 0; j < i; j++) {
                            if (p[j] > p[i]) ans++;
                        }
                    }

                    for (int i = 0; i < w; i++) {
                        for (int j = 0; j < i; j++) {
                            if (q[j] > q[i]) ans++;
                        }
                    }
                    System.out.println(ans);
                    return;
                }
            } while (nextPermutation(q));
        } while (nextPermutation(p));
        System.out.println(-1);
    }

    static boolean nextPermutation(int[] arr) {
        int len = arr.length;
        int index = len - 2;

        while (index >= 0 && arr[index] >= arr[index + 1]) index--;

        if (index < 0) return false;

        int target = index + 1;
        for (int i = target; i < arr.length; i++) {
            if (arr[i] > arr[index]) target = i;
        }

        int tmp = arr[index];
        arr[index] = arr[target];
        arr[target] = tmp;
        Arrays.sort(arr, index + 1, arr.length);

        return true;
    }
}