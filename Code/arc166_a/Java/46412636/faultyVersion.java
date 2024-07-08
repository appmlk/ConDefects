import java.util.Scanner;

/**
 * @ProjectName: study3
 * @FileName: Ex5
 * @author:HWJ
 * @Data: 2023/10/8 20:08
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            int m = input.nextInt();
            String s1 = input.next();
            String s2 = input.next();
            char[] str1 = s1.toCharArray();
            char[] str2 = s2.toCharArray();
            int start = 0;
            boolean a = true;
            int j = 0;
            for (; j < m; j++) {
                if (!a) {
                    break;
                }
                if (str1[j] == 'C' && str2[j] == 'C') {
                    a = f(str1, str2, start, j - 1);
                    start = j + 1;
                }
                if (str1[j] != 'C' && str2[j] == 'C') {
                    a = false;
                }
            }
            a = a && f(str1, str2, start, j - 1);
            if (a) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }


    }

    public static boolean f(char[] str1, char[] str2, int start, int end) {
        int cnta = 0;
        int cntb = 0;
        for (int i = start; i <= end; i++) {
            if (str1[i] == 'B' || str1[i] == 'C') {
                cnta++;
            }
            if (str2[i] == 'B') {
                cntb++;
            }
            if (cnta < cntb) {
                return false;
            }
        }
        cnta = 0;
        cntb = 0;
        for (int i = start; i <= end; i++) {
            if (str1[i] == 'A' || str1[i] == 'C') {
                cnta++;
            }
            if (str2[i] == 'A') {
                cntb++;
            }
            if (cnta < cntb) {
                return false;
            }
        }
        return true;
    }

}
