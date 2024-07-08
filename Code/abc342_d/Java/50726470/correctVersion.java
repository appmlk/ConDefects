
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StreamTokenizer in = new StreamTokenizer(br);
        in.nextToken();
        int n  = (int)in.nval;
        long ans = 0;
        int cnt0 = 0;
        int[] count = new int[200001];
        for (int i = 0; i < n; i++) {
            in.nextToken();
            int num  = (int)in.nval;
            if(num == 0){
                ans += i;
                cnt0++;
                continue;
            }
            //分解质因数，消掉偶数的质因子，留下质因子是奇数的
            for(long j=2;j*j <= num;j++){
                long k = j*j;
                //分解质因数
                while(num % k == 0){
                    num /= k;
                }
            }
            //1 num >= 1
            // num == 1,表示num本身就是 一个平方数，它可以和之前的平方数结合,之前的平方数分解后 num == 1
            // num > 1 需要和 num 类型的结合。
            //2.任何一个数可以和 0 相乘 结果 为 0
            ans += count[num] + cnt0;
            count[num]++;
        }
        pw.println(ans);
        pw.flush();
        pw.close();
    }
}