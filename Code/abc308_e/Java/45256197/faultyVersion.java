// package date0905;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n ;i++){
            arr[i] = sc.nextInt();
        }
        String s = sc.next();
        char[] cs = s.toCharArray();
        long ans = 0;
        int[] pre = new int[3] , suf = new int[3];
        int[] mex = {0,1,0,2,0,1,0,3};
        for(int i = 0 ; i < n ; i++){
            if(cs[i] == 'X') suf[arr[i]]++;
        }
        for(int i = 0 ; i < n ; i++){
            if(cs[i] == 'M') pre[arr[i]]++;
            else if(cs[i] == 'E'){
                for(int j = 0 ; j < 3 ; j++){
                    for(int k = 0 ; k < 3 ; k++){
                        ans += mex[(1 << arr[i]) | (1 << j)| (1 << k)] * pre[j] * suf[k];
                    }
                }
            }else{
                suf[arr[i]]--;
            }
        }
        System.out.println(ans);
    }
}
