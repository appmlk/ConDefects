import java.io.InputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        InputStream source = System.in;
        Scanner sc = new Scanner(source);


        int N = sc.nextInt();
        int T = sc.nextInt();


        int [] yoko = new int[N];
        int [] tate = new int[N];
        int naname_左上から右下 = 0;
        int naname_右上から左下 = 0;

        int[][] grid = new int[N][N];
        int count = 1;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                grid[i][j] = count;
                yoko[i] += count;
                tate[j] += count;
                if (i==j) {
                    naname_左上から右下 += count;
                }

                count++;
            }
        }
        naname_右上から左下 = naname_左上から右下;

        for (int i=0; i<T; i++) {
            int t = sc.nextInt();
            int x = (t-1) % N;
            int y = (t-1) / N;

            yoko[y] -= t;
            tate[x] -= t;
            if (x == y) {
                naname_左上から右下 -= t;
            }
            if (x+y == N-1) {
                naname_右上から左下 -= t;
            }

            for (int j=0; j<N; j++) {
                if (yoko[j] == 0) {
                    System.out.println(i+1);
                    return;
                }
                if (tate[j] == 0) {
                    System.out.println(i+1);
                    return;
                }
                if (naname_左上から右下 == 0) {
                    System.out.println(i+1);
                    return;
                }
                if (naname_右上から左下 == 0) {
                    System.out.println(i+1);
                    return;
                }
            }
        }
        System.out.println("-1");
    }
}


