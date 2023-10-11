import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i=0; i<t; i++) {
            long n = in.nextLong();
            System.out.println(solve(n) ? "Yes" : "No");
        }
    }
    private static boolean solve(long n) {
        // 素因数分解する
        // 試し割りでも sqrt(10^9) は 10^5 には収まるので間に合いそうな予感（怪しいけどTLEしたらエラトステネスさんの技法を採用しよう）
        long m = n;
        long sumOfFactors = 0;
        long i = 2;
        while(m>1) {
            if(m%i > 0) {
                i++;
                if(m<i*i) {
                    // 素数
                    sumOfFactors += m;
                    break;
                }
                continue;
            }
            m = m/i;
            int count = 1;
            while(m%i == 0) {
                m = m/i;
                count++;
            }
            sumOfFactors = i * count;
        }
        return sumOfFactors < n;
    }
}
