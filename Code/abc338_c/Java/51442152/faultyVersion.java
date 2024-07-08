
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] Q = new int[N];
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            Q[i]=sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            A[i]=sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            B[i]=sc.nextInt();
        }
        int x_min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if(A[i]!=0) x_min=Math.min(x_min,Q[i]/A[i]);
        }
        int ans=0;
        for (int i = x_min; i >=0; i--) {
            int y_min=Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if(B[j]!=0) y_min=Math.min(y_min,(Q[j]-A[j]*i)/B[j]);
            }
            ans=Math.max(ans,y_min+x_min);
        }
        System.out.println(ans);
    }
}
