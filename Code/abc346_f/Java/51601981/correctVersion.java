//make sure to make new file!
import java.io.*;
import java.util.*;

public class Main{
   
   public static long n;
   public static int sn;
   public static int tn;
   
   public static ArrayList<ArrayList<Integer>> sind;
   public static ArrayList<ArrayList<Integer>> tind;
   public static long[] sfreq;
   
   public static int[] s;
   public static int[] t;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      //BufferedReader f = new BufferedReader(new FileReader("Fgen.txt"));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Long.parseLong(f.readLine());
      String sin = f.readLine();
      sn = sin.length();
      String tin = f.readLine();
      tn = tin.length();
      
      sind = new ArrayList<>(26);
      tind = new ArrayList<>(26);
      for(int k = 0; k < 26; k++){
         sind.add(new ArrayList<Integer>());
         tind.add(new ArrayList<Integer>());
      }
      
      s = new int[sn];
      sfreq = new long[26];
      for(int k = 0; k < sn; k++){
         s[k] = sin.charAt(k)-'a';
         sind.get(s[k]).add(k);
         sfreq[s[k]]++;
      }
      t = new int[tn];
      for(int k = 0; k < tn; k++){
         t[k] = tin.charAt(k)-'a';
         tind.get(t[k]).add(k);
      }
      
      long l = 0L;
      long r = 100000000000000000L;    //10^17
      long ans = -1L;
      
      while(l <= r){
         long mid = l + (r-l)/2L;
         
         if(check(mid)){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      out.println(ans);
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static boolean check(long x){
      if(x == 0L) return true;
      //see how many repetitions of s you need to do each character of t x times
      
      long srep = 0L;
      int i = Integer.MAX_VALUE;           //index of last used character
      for(int k = 0; k < tn; k++){
         if(sfreq[t[k]] == 0L) return false;
         
         long currep = x;
         
         //binary search for first point in current repetition that can be used
         int l = 0;
         int r = (int)sfreq[t[k]]-1;
         int ans = -1;
         while(l <= r){
            int mid = l + (r-l)/2;
            
            if(sind.get(t[k]).get(mid) > i){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         //fill what you can using rest of repetition
         if(ans == -1){
            srep++;
         } else {
            long rem = sfreq[t[k]]-1 - ans + 1;
            if(currep <= rem){
               //can use all in current repetition
               i = sind.get(t[k]).get(ans + (int)currep-1);
               continue;
            } else {
               //not enough
               currep -= rem;
               srep++;
            }
         }
         
         //currep > 0
         srep += currep / sfreq[t[k]] - 1;
         if(currep % sfreq[t[k]] == 0L){
            i = sind.get(t[k]).get((int)sfreq[t[k]]-1);
         } else {
            srep++;
            i = sind.get(t[k]).get((int)(currep % sfreq[t[k]] - 1L));
         }
         
         if(srep > n) return false;
               
      }
      
      //System.out.println(x + " " + srep);
      return srep <= n;
      
   }
      
}