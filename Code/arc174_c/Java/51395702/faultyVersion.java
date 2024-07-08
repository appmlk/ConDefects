//make sure to make new file!
import java.io.*;
import java.util.*;

public class Main{
   
   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      long n2 = n*n;
      
      //fines
      long p1 = 0L;
      long p2 = 0L;
      
      //probability p1 and p2 are the first player respectively
      long pp1 = 0L;
      long pp2 = 1L;
      
      for(long k = 1; k < n; k++){
         long k2 = (k*k + MOD)%MOD;
         
         long nki = modInverse((n+k+MOD)%MOD,MOD);
         long n2mk2 = n2 - k2;
         if(n2mk2 < 0) n2mk2 += MOD;
         long n2mk2i = modInverse(n2mk2,MOD);
         
         long both = (n2 * n2mk2i + MOD)%MOD;
         both--;
         if(both < 0) both += MOD;
         
         p1 = (p1 + both + MOD)%MOD;
         p2 = (p2 + both + MOD)%MOD;
         
         //k/(n+k) chance that player 1 is fined an extra time
         long p1last = (k * nki + MOD)%MOD;
         long p2last = (n * nki + MOD)%MOD;
         
         p1 = (p1 + p1last * pp1 + MOD)%MOD;
         p2 = (p2 + p1last * pp2 + MOD)%MOD;
         
         //adjust pp1 and pp2
         long npp11 = (pp1 * p1last + MOD)%MOD;
         long pp1i = 1L-pp1;
         if(pp1i < 0) pp1i += MOD;
         long npp12 = (pp1i * p2last + MOD)%MOD;
         pp1 = (npp11 + npp12 + MOD)%MOD;
         
         long npp21 = (pp2 * p1last + MOD)%MOD;
         long pp2i = 1L-pp2;
         if(pp2i < 0) pp2i += MOD;
         long npp22 = (pp2i * p2last + MOD)%MOD;
         pp2 = (npp21 + npp22 + MOD)%MOD;
         /*
         out.println(k);
         out.println("1: " + npp11 + " " + npp12);
         out.println("2: " + npp21 + " " + npp22);
         out.println(p1last + " " + p2last);
         out.println(pp1 + " " + pp2);
         */
      }
      
      
      out.println(p1 + " " + p2);
      
      
      
      
      
      
      out.close();
   }
   
   
   //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0;
       long x = 1; 
     
       if (m == 1) 
         return 0; 
     
       while (a > 1) 
       { 
           // q is quotient 
           long q = a / m; 
           long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m;
           a = t; 
           t = y; 
     
           // Update y and x 
           y = x - q * y; 
           x = t; 
       } 
     
       // Make x positive 
       if (x < 0) 
          x += m0; 
       return x; 
   } 
   
      
}