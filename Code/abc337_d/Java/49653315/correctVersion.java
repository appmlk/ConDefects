import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int k = sc.nextInt();
        char [][] s = new char[h][w];
        for (int i = 0; i < h; i++) {
            s[i] = sc.next().toCharArray();
        }
        int ans = -1;
        for (int i = 0; i < h; i++) {
            int oCnt = 0;
            int xCnt = 0;
            int elseCnt = 0;
            for (int j = 0; j < w; j++){
                switch (s[i][j]) {
                    case 'x' -> xCnt++;
                    case 'o' -> oCnt++;
                    default ->  elseCnt++;
                }
                if (k <= j) {
                    switch (s[i][j-k]) {
                        case 'x' -> xCnt--;
                        case 'o' -> oCnt--;
                        default ->  elseCnt--;
                    }
                }
                int sumCnt = xCnt+oCnt+elseCnt;
                if (sumCnt == k && xCnt == 0) {
                    ans = Math.max(ans,oCnt);
                }
            }
        }

        for (int j = 0; j < w; j++) {
            int oCnt = 0;
            int xCnt = 0;
            int elseCnt = 0;
            for (int i = 0; i < h; i++){
                switch (s[i][j]) {
                    case 'x' -> xCnt++;
                    case 'o' -> oCnt++;
                    default ->  elseCnt++;
                }
                if (k <= i) {
                    switch (s[i-k][j]) {
                        case 'x' -> xCnt--;
                        case 'o' -> oCnt--;
                        default ->  elseCnt--;
                    }
                }
                int sumCnt = xCnt+oCnt+elseCnt;
                if (sumCnt == k && xCnt == 0) {
                    ans = Math.max(ans,oCnt);
                }
            }
        }
        System.out.println(ans == -1 ? ans : k-ans);
    }
}