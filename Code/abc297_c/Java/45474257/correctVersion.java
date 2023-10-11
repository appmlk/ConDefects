import java.util.Scanner;


public class Main {

    static int max = 0;
    static char[][] res;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int H = scanner.nextInt();
        int W = scanner.nextInt();

        char[][] arr = new char[H][W];
        res = new char[H][W];

        for (int i = 0; i < H; i++) {
            String s = scanner.next();
            for (int j = 0; j < W; j++) {
                arr[i][j] = s.charAt(j);
                res[i][j] = arr[i][j];
            }
        }

        solve(arr, 0, 0, H, W, 0);


        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j]);
            }

            System.out.println();
        }

    }

    static void solve(char[][] arr, int y, int x, int H, int W, int count) {
        if (y >= H) {
            if (max < count) {
                max = count;

                for (int i = 0; i < arr.length; i++) {
                    res[i] = arr[i].clone();
                }
            }

            return;
        }

        if (x < W - 1 && y < H) {
            if (arr[y][x] == 'T' && arr[y][x + 1] == 'T') {
                arr[y][x] = 'P';
                arr[y][x + 1] = 'C';

                solve(arr, y, x + 2, H, W, count + 1);

                arr[y][x] = 'T';
                arr[y][x + 1] = 'T';
            } else {
                solve(arr, y, x + 1, H, W, count);
            }
        } else if (x >= W - 1 && y < H) {
            solve(arr, y + 1, 0, H, W, count);
        }
    }


}
