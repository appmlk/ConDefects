import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] S = new String[3];
        String[] T = new String[3];
        String[][] rgb = new String[3][2];
        for (int i = 0; i < 3; i++) {
            S[i] = sc.next();
            rgb[i][0] = S[i];
        }
        for (int i = 0; i < 3; i++) {
            T[i] = sc.next();
            rgb[i][1] = T[i];

        }
        int dcnt = 0;
        for (int i = 0; i < 3; i++) {
            if (!rgb[i][0].equals(rgb[i][1])) {
                dcnt++;
            }
        }
        String result = dcnt % 3 == 0 ? "Yes" : "No";

        System.out.println(result);
        sc.close();
    }

}
