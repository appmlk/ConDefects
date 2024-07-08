import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
        }
        int[] rowCnt = new int[n];
        int[] columnCnt = new int[n];
        for (int i = 0; i < n; i++) {
            int row = 0;
            int column = 0;
            for (int j = 0; j < n; j++) {
                if (s[i].charAt(j) == 'o') {
                    row++;
                }
                if (s[j].charAt(i) == 'o') {
                    column++;
                }
            }
            rowCnt[i] = row;
            columnCnt[i] = column;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int y = Math.max(0, rowCnt[i] - 1);
            for (int j = 0; j < n; j++) {
                int x = Math.max(0, columnCnt[j] - 1);
                if (s[i].charAt(j) == 'o') {
                    ans += x * y;
                }
            }
        }
        System.out.println(ans);
    }
}