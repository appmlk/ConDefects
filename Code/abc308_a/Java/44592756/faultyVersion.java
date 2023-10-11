import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] S = new int[8];

        for (int i = 0; i < 8; i++) {
            S[i] = sc.nextInt();
        }
        
        int count = 0;

        for (int i = 0; i < 8; i++) {
            if (S[i] % 25 == 0) {
                count++;
            }
            if (100 <= S[i] && S[i] <= 675) {
                count++;
            }
            if (i < 7 && S[i] < S[i + 1]) {
                count++;
            }
        }

        if (count == 23) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
