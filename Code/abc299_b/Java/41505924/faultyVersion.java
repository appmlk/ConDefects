import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        
        int[] C = new int[N];
        for (int i = 0; i < N; i++) {
            C[i] = sc.nextInt();
        }

        int[] R = new int[N];
        for (int i = 0; i < N; i++) {
            R[i] = sc.nextInt();
        }
        
        int max = 0;
        int maxI = 0;
        for (int i = 0; i < N; i++) {
            if (C[i] == T && R[i] > max) {
                max = R[i];
                maxI = i;
            }
        }
        
        if (max == 0) {
            for (int i = 0; i < N; i++) {
                if (C[i] == C[0] && R[i] > max) {
                    max = R[i];
                    maxI = i;
                }
            }
        }
        
        System.out.println(maxI);
    }
}