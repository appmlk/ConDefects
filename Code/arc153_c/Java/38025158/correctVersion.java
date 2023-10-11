import java.io.*;
import java.util.*;

public class Main {
	
	static IOHandler sc = new IOHandler();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = sc.nextInt();
		
		int [] arr = sc.readArray(n);
		
		if (n == 1) {
			System.out.println("Yes");
			System.out.println(0);
			return;
		}
		
		int [] prefix = new int [n];
		int sum = 0;
		long neg = 0;
		long pos = 0;
		
		for (int i = n - 1; i >= 0; --i) {
			sum += arr[i];
			prefix[i] = sum;
			
			if (prefix[i] > 0)
				pos += prefix[i];
			else if (prefix[i] < 0)
				neg += Math.abs(prefix[i]);
		}
		
		
		long [] res = solve(prefix, 1);
		
		prefix[0] *= -1;
		
		if (res == null) {
			res = solve(prefix, -1);
			
		}
		
		if (res == null) {
			System.out.println("No");
			return;
		}
		
		System.out.println("Yes");
		
		StringBuilder sb = new StringBuilder();
		
		for (long num : res) {
			sb.append(num);
			sb.append(' ');
		}
		
		System.out.println(sb);
	}
	
	private static long [] solve(int [] prefix, int mul) {
		long neg = 0;
		long pos = 0;
		
		for (int num : prefix) {
			if (num < 0)
				neg += Math.abs(num);
			else
				pos += num;
		}
		
		if (Math.min(pos, neg) == 0) {
			return null;
		}
		
		long [] res = new long [prefix.length];
		long lSum = 0;
		
		int n = prefix.length;
		int idx = 0;
		
		long [] test = new long [n];
		
		
		for (int i = 0; i < n - 1; ++i) {
			if (prefix[i] == 0) 
				continue;
			
			if ((prefix[n - 1] < 0) != (prefix[i] < 0) ) {
				idx = i;
				break;
			}
		}
		
		long sum = 0;
		
		Arrays.fill(test, 1);
		
		//System.out.println(Arrays.toString(prefix));
		
		for (int i = 0; i < n - 1; ++i) {
			if (i == idx) 
				continue;
			
			sum += prefix[i];
		}
		
		long eSum;
		long absSum, absPrefix;
		
		long rem;
		
		
		eSum = sum + prefix[idx];
		
		//System.out.println(eSum);
		
		if (eSum == 0 || (eSum < 0) != (prefix[idx] < 0) ) {
			absSum = Math.abs(sum);
			absPrefix = Math.abs(prefix[idx]);
			
			rem = absSum / absPrefix;
			rem += 3;
			test[idx] = rem;
			eSum = sum + test[idx] * prefix[idx];
		}
		
		sum = eSum;
		//System.out.println(sum);
		
		test[n - 1] = Math.abs(sum);
		
		//System.out.println(idx);
		
		//System.out.println(Arrays.toString(test));
		
		
		for (int i = 0; i < n; ++i) {
			lSum += test[i];
			
			if (i == 0)
				lSum *= mul;
			
			res[i] = lSum;
		}
		
		return res;
	}
	
	private static long gcd(long a, long b) {
		if (a % b == 0)
			return b;
		
		return gcd(b, a % b);
	}
	
	private static class IOHandler {
      BufferedReader br;
      StringTokenizer st;
 
      public IOHandler() {
         br = new BufferedReader(new InputStreamReader(System.in));
      }
 
      String next() {
          while (st == null || !st.hasMoreElements()) {
              try {
                  st = new StringTokenizer(br.readLine());
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          return st.nextToken();
      }
 
      int nextInt() {
          return Integer.parseInt(next());
      }
      
      int [] readArray(int n) {
    	  int [] res = new int [n];
    	  
    	  for (int i = 0; i < n; ++i)
    		  res[i] = nextInt();
    	  
    	  return res;
      }
 
      long nextLong() {
          return Long.parseLong(next());
      }
 
      double nextDouble() {
          return Double.parseDouble(next());
      }
 
      String nextLine(){
          String str = "";
		  try {
		     str = br.readLine();
		  } catch (IOException e) {
		     e.printStackTrace();
		  }
		  return str;
      }

   }

}

