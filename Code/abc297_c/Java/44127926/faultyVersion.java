import java.math.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();
        sc.nextLine();
        int tCnt = 0;
        for (int i = 0; i < H; i++) {
            String[] tArr = sc.nextLine().split("");
            tCnt = 0;
            for (int j = 0; j < tArr.length; j++) {
                if ("T".equals(tArr[j])) {
                    tCnt += 1;
                    if (tCnt == 2) {
                        tArr[j-1] = "P";
                        tArr[j] = "C";
                        tCnt = -1;
                    }
                } else {
                    tCnt = 0;
                }
            }
            String answer = "";
            for (String s:
                 tArr) {
                answer += s;
            }
            System.out.println(answer);
        }

    }
}
