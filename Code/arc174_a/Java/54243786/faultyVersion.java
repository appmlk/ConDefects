import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int C = sc.nextInt();
        
        long[] Array = new long[N];
        
        long frag = 1;
        if (C < 0) frag *= -1;
        
        for (int i = 0; i < N; i++) {
            Array[i] = sc.nextInt() * frag;
        }
        
        // 累積和の配列
        long[] sumArray = new long[N+1];
        // indexまでの累積和の最小値を記録する配列
        long[] sumMinArray = new long[N+1];
        // 累積和の計算
        for (int i = 1; i < N+1; i++) {
            // 累積和の計算
            sumArray[i] = sumArray[i-1] + Array[i-1];
            // iまでの累積和の最小値
            sumMinArray[i] = Math.min(sumArray[i], sumMinArray[i-1]);
        }
        
        long partSum = sumArray[N];
        
        for (int i = 1; i < N+1; i++) {
            long sub = sumArray[i] - sumMinArray[i];
            partSum = Math.max(partSum, sub);
        }
        
        
        
        System.out.println((partSum * (C-1) + sumArray[N]) * frag);
        
        // // 尺取り法
        // for (int l = 0; l < N+1; l++) {
        //     for (int r = 0; r < N+1; r++) {
                
        //     }
        // }
    }
}