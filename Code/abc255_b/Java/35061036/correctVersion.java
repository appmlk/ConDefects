import java.util.*;
import static java.lang.System.out;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int K = sc.nextInt();
        int A[] = new int[K];
        int X[] = new int[N];
        int Y[] = new int[N];
        for(int i = 0;i < K;i++)A[i] = sc.nextInt();
        for(int i = 0;i < N;i++){
            X[i] = sc.nextInt();
            Y[i] = sc.nextInt();
        }

        double ans = 0;
        for(int i = 0;i < N;i++){
            double min = Double.MAX_VALUE;
            for(int j = 0;j < K;j++){
                double dist = Math.sqrt((1.0 * X[i] - X[A[j] - 1]) * (1.0 * X[i] - X[A[j] - 1]) + (1.0 * Y[i] - Y[A[j] - 1]) * (1.0 * Y[i] - Y[A[j] - 1]));
                min = Math.min(min, dist);
            }
            if(min != Double.MAX_VALUE)ans = Math.max(min, ans);
        }
        out.println(ans);
        
    }
}
