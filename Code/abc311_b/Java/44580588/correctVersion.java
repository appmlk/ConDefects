import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int D = sc.nextInt();

        String[][] str = new String[N][D];
        String[] str2 = new String[D];

        for (int i = 0; i < N; i++) {

            String s = sc.next();
            String str3[] = s.split("");

            for (int j = 0; j < D; j++) {
                str[i][j] = str3[j];
            }

        }

        for (int i = 0; i < D; i++) {

            int count = 0;

            for (int j = 0; j < N; j++) {
                if (str[j][i].equals("o")){
                    count++;
                }
            }

            if (count == N) {
                str2[i] = "o";
            } else {
                str2[i] = "x";
            }
            
        }

        int ans = 0;
        for (int i = 0; i < D; i++) {
            if (str2[i].equals("o")) {

                int ans2 = 1;

                if (i < D - 1) {
                for (int j = i + 1; j < D;j++) {
                    if (str2[j].equals("o")) {
                        ans2++;
                    } else {
                        break;
                    }
                }
                }

                ans = Math.max(ans, ans2);
            }
        }
        System.out.println(ans);

    }
}
