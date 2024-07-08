//make sure to make new file!
import java.io.*;
import java.util.*;

public class Main{
   
   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long ml = (long)m;
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      int num0 = 0;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(array[k] == 0) num0++;
      }
      
      if(m >= n){
         out.println(exp(ml,num0));
         out.close();
         return;
      }
      
      //first n, number of distinct values (max m+1 distinct values)
      long[][] dp = new long[n][m+2];
      ml = (long)m;
      if(array[0] == 1) dp[0][1] = 1L;
      else dp[0][1] = ml;
      
      for(int k = 0; k < n-1; k++){
         for(int j = 1; j <= m+1; j++){
            if(dp[k][j] == 0L) continue;
            
            if(array[k+1] == 1){
               //must add a distinct value
               if(j+1 <= m+1){
                  dp[k+1][j+1] = (dp[k+1][j+1] + dp[k][j] + MOD)%MOD;
               }
            } else {
               //add a distinct value that is not mex
               long mul = ml+1 - j - 1L;
               if(mul > 0 && j+1 <= m+1){
                  dp[k+1][j+1] = (dp[k+1][j+1] + dp[k][j]*mul + MOD)%MOD;
               }
               
               //add same value
               dp[k+1][j] = (dp[k+1][j] + dp[k][j]*(long)j + MOD)%MOD;
               
            }
         }
      }
      
      long answer = 0L;
      for(int j = 1; j <= m+1; j++){
         answer = (answer + dp[n-1][j] + MOD)%MOD;
      }
      out.println(answer);
         
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static long exp(long base, int power){
      if(power == 0) return 1;
      if(power == 1) return (base + MOD) % MOD;
      long ans = exp(base,power/2);
      ans = (ans*ans + MOD) % MOD;
      if(power%2 == 1) ans = (ans*base + MOD) % MOD;
      return (ans + MOD) % MOD;
   }
      
}