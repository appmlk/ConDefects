
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        String s = sc.next();
        //贡献法，计算每个 E 的贡献
        int[][] left = new int[2][3];
        int[][] right = new int[2][3];
        int[][][] calc = new int[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int t = 0;
                    while (t == i || t == j || t == k) t++;
                    calc[i][j][k] = t;
                }
            }
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'M') {
                right[0][a[i]]++;
            } else if (chars[i] == 'X') {
                right[1][a[i]]++;
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'M') {
                right[0][a[i]]--;
                left[0][a[i]]++;
            } else if (chars[i] == 'X') {
                right[1][a[i]]--;
                left[1][a[i]]--;
            } else {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        ans +=(long) left[0][j] * right[1][k] * calc[a[i]][j][k];
                    }
                }
            }
        }
        System.out.println(ans);


    }
}
