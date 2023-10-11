import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var sc = new Scanner(System.in);
        var n = sc.nextLong();
        var k = sc.nextLong();
        sc.close();
        System.out.println(arc142a(n, k));
        System.out.flush();
    }

    private static long arc142a(long n, long k){
        // Kになりうるxをリストアップ
        var hs = new HashSet<Long>();
        // Kを1,10.100...倍した値
        var tempK = k;
        while(tempK <= n){
            hs.add(tempK);
            tempK *= 10;
        }
        // Kを反転させた値を1,10.100...倍した値
        tempK = longReverse(k);
        while(tempK <= n){
            hs.add(tempK);
            tempK *= 10;
        }
        // 作成した値がf(x)=kとなればカウント
        var ans = 0L;
        for (var x : hs){
            if(func(x) == k) ans++;
        }
        return ans;
    }

    private static long func(long x){
        var res = x;
        x = longReverse(x);
        res = Math.min(res, x);
        x = longReverse(x);
        res = Math.min(res, x);
        return res;
    }

    private static long longReverse(long num){
        return Long.parseLong((new StringBuilder(Long.toString(num)).reverse().toString()));
    }
}
