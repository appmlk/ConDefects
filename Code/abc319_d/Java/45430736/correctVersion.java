//make sure to make new file!
import java.io.*;
import java.util.*;

public class Main{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      long max = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         max = Math.max(max,array[k]);
      }
      
      long l = max;
      long r = 200000001000000L;
      long ans = -1;
      
      while(l <= r){
         long mid = l + (r-l)/2L;
         
         //see if width = mid works
         
         int lines = 1;
         long curline = 0;
         
         for(int k = 0; k < n; k++){
            if(curline + array[k] > mid){
               lines++;
               curline = array[k]+1;
            } else {
               curline += array[k]+1;
            }
         }
         
         if(lines <= m){
            ans = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      out.println(ans);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}