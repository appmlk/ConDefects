import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
        Main m = new Main();
        m.run();
    }

    long mod = 998244353L;
    public void run(){
        char ch[] = new Scanner(System.in).next().toCharArray();
        int rev[] = new int[26];
        int  n = ch.length;
        for(int i =0; i< ch.length; i++){
            rev[ch[i] - 'a']++;
        }


        long ans[] = new long[n+1];
        ans[0] = 1;

        for(int c = 0 ; c < 26; c++){
            long next[] = new long[n+1];
            for(int x = 0; x <= rev[c]; x++){
                for(int i =0 ; i + x <= n ;i++){
                    next[i+x] += (ans[i] * choose(i+x,x)) % mod;
                    next[i+x] = next[i+x] % mod;
                }
            }
            ans = next;
//            System.out.println(Arrays.toString(ans));
        }

        long res = 0;
        for(int i =1 ;i <= n; i++){
            res = res + ans[i];
            res = res % mod;
        }
//        System.out.println(Arrays.toString(ans));
        System.out.println(res);
    }

    long[][] map = new long[5001][5001];
    long choose(int n,int r){
        if(map[n][r] != 0){
            return map[n][r];
        }
        if(n == r) {
            return 1;
        }
        if(r == 0){
            return 1;
        }

        long ans = choose(n, r-1) + choose(n-1, r);
        map[n][r] = ans % mod;
        return map[n][r];
    }
}