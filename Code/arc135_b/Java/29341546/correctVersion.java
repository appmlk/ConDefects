import java.io.*;
import java.util.*;

public class Main{
  
  public static void main(String args[]) throws Exception{
   
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    
    int n = Integer.parseInt(br.readLine());
    int[] ar = new int[n];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    for(int i = 0; i < n; i++)
      ar[i] = Integer.parseInt(st.nextToken());
    
    int l = 0;
    int r = ar[n-1];
    int v1, v2;
    v1 = v2 = -1;
    
    while(l <= r){
      
      int mid = (l + r)/2;
      int res = check(ar, mid);
      
      if(res >= 0){
       
        v1 = mid;
        v2 = res;
        break;
        
      }else if(res == -1)
        r = mid-1;
      else
        l = mid+1;
      
    }
    
    if(v1 == -1)
      pw.println("No");
    else {

      pw.println("Yes");

      int[] res = new int[n+2];

      int lx = v1;
      int nx = v2;

      res[n+1] = lx;
      res[n] = nx;

      for(int i = ar.length-1; i >= 0; i--) {

        int nv = ar[i] - (lx+nx);

        res[i] = nv;

        lx = nx;
        nx = nv;

      }

      for(int i : res)
        pw.print(i + " ");

    }
    
    pw.close();
        
  }
  
  static int check(int[] ar, int v){
    
   int min = 0;
   int max = ar[ar.length-1]-v;
   int[] tmp = new int[]{v, 0, ar[ar.length-1]-v};
   int ind = 0;
    
   for(int i = ar.length-2; i >= 0; i--){
     
     if(ind == 0){
       
       tmp[0] += ar[i] - ar[i+1];
       
       if(tmp[0] < 0)
         return -2;
       
     }else if(ind == 1){
       
       tmp[1] += ar[i] - ar[i+1];
       
       min = Math.max(min, -tmp[1]);
       
     }else{
       
       tmp[2] += ar[i] - ar[i+1];
       
       max = Math.min(max, tmp[2]);
       
     }
     
     ind = (ind+1)%3;
     
   }
    
   if(min > max || max < 0)
     return -1;
   
   return Math.max(0, min);
    
  }
   
}