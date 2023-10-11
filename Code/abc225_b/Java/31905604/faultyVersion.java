import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        
        int[] L = new int [N + 1];
        for (int i = 0; i < 2*N - 2; i++) {
            int idx = in.nextInt();
            L[idx - 1] += 1;
        }
        
        String ans = "No";
        for (int i = 0; i < N - 1; i++) {
            if (L[i] == N - 1) ans = "Yes";
        }
        
        System.out.println(ans);
    }
}
