
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        char[] chars = s.toCharArray();
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        char[] x = new char[q];
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < 2; j++) {
                queries[i][j] = sc.nextInt();
            }
            x[i] = sc.next().charAt(0);

        }
        int last = q - 1;//最后一次改变字母大小写的操作
        int type = -1;
        for (int i = q - 1; i >= 0; i--) {
            if (queries[i][0] != 1) {
                last = i;
                type = queries[i][0];
                break;
            }
        }
        for (int i = 0; i < last; i++) {
            if (queries[i][0] == 1) {
                chars[queries[i][1] - 1] = x[i];
            }
        }
        if (type == 2) {
            for (int i = 0; i < n; i++) {
                if (Character.isUpperCase(chars[i])) {
                    chars[i] = (char) (chars[i] ^ 32);
                }
            }
        } else if (type == 3) {
            for (int i = 0; i < n; i++) {
                if (Character.isLowerCase(chars[i])) {
                    chars[i] = (char) (chars[i] ^ 32);
                }
            }
        }
        for (int i = last + 1; i < q; i++) {
            chars[queries[i][1] - 1] = x[i];
        }
        StringBuffer sb = new StringBuffer();
        for (char c : chars) sb.append(c);
        System.out.println(sb);


    }

}
