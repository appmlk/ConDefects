
import java.io.*;
import java.util.*;

public class Main {
    public static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    public static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    //如果数字太大会有精度丢失
    public static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    //如果数字太大会有精度丢失
    public static double nextDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    //字符串不能以数字开头，字符串不能是特殊符号，空格(| _ % ^..... )
    public static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int N = 200010;
    static int mod = 998244353;
    static int[] arr = new int[N];
    static long[] offset = {0,10,100,1000,10000,100000,1000000,10000000,100000000,1000000000,10000000000L};
    static int count(int num){
        int res = 0;
        while(num > 0){
            num /= 10;
            res++;
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        int n = nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }
        long ans = 0;
        long sum = arr[0];
        //求第 i 个数字对答案的贡献
        for (int i = 1,num,len; i < n; i++) {
            num = arr[i];
            len = count(num);
            ans = (ans + sum * offset[len] % mod + (long)num * i % mod) % mod;
            sum = (sum + num) % mod;
        }
        pw.println(ans);
        pw.flush();
    }
}