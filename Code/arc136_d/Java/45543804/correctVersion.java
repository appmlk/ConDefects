// package lingdaily.date0915;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }
        int[] f = new int[(int)1e6];
        for(int i = 0 ; i < n ; i++){
            f[arr[i]]++;
        }
        for(int p = 1 ; p < 1e6 ;p = p * 10){
            for(int i = 0 ; i < 1e6 ;i++){
                if(i / p % 10 > 0){
                    f[i] += f[i - p];
                }
            }
        }
        long ans = 0;
        next:for(int i = 0 ; i < n ; i++){
            int x = arr[i];
            ans += f[999999 - x];
            for(;x > 0 ; x /= 10){
                if(x % 10 > 4){
                    continue next;
                }
            }
            ans--;
        }
        System.out.println(ans / 2);
    }
}
