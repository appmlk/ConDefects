
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	
	 	public static int psi(String n) {
	 		return Integer.parseInt(n);
	 	}

	    public static long psl(String n) {
	        return Long.parseLong(n);
	    }

	    public static String str_int(int n) {
	       return String.valueOf(n);
	    }

	   public static String str_lg(long n) {
	       return String.valueOf(n);
	   }

	    
	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

//        int t = psi(rd.readLine());
//        while (t-- > 0) {
            
            int n = psi(rd.readLine());
            
            String [] a1 = rd.readLine().split(" ");
                      
           int [] a = new int[n];
           for (int i = 0; i < n; i++) a[i] = psi(a1[i]);
      
          	int [] arr = new int[n];
          	
          	int cnt = 1;
          	int cnt_cycles = 0;
          	
        	for (int i = 0; i < n; i++) {
        		if (arr[i] != 0) continue;
        		
        		int ind = i;
        		boolean is_cycle = false;
        		
        		while (true) {
        			
        			if (arr[ind] == cnt) {
        				is_cycle = true;
        				break;
        			}
        			
        			if (arr[ind] != 0) break;
        			
        			arr[ind] = cnt;
        			
        			if (a[ind] - 1 == ind) break;
        		
        			ind = a[ind] - 1;
        		}
        		
        		
        		if (!is_cycle) {
        			cnt++;
        			continue;
        		}
        		
        		while (arr[ind] != -1) {
        			arr[ind] = -1;
        			ind = a[ind] - 1;
        		}
        		
        		cnt_cycles++;
        		cnt++;
        		
        	}
        	
        	
        	if (cnt_cycles > 0) {
        		
        		int ans = 0;		
        		for (int i = 0;  i< n; i++) {
        			if (arr[i] == -1) ans++;
        			if (i + 1 == a[i]) ans++;
        		}
        		
        		out.println(ans);
        		
        	} else {
        		
        		cnt = 1;
        		
        		int [] arr1 = new int[n];
        		
        		for (int i = 0; i < n; i++) {
        			if (arr1[i] != 0) continue;
        			
        			int ind = i;
        			
        			while (arr1[ind] == 0) {
        				arr1[ind] = cnt;
        				ind = a[ind] - 1;
        			}
        			
        			int color = arr1[ind];
        			
        			ind = i;
        			
        			while (arr1[ind] != color) {
        				arr1[ind] = color;
        				ind = a[ind] - 1;
        			}
        			
        			cnt++;
        		}
        		
        		Arrays.sort(arr1);
        		
        		int cnt_colors = 1;
        		
        		for (int i = 1; i < n; i++) {
        			cnt_colors += (arr1[i] != arr1[i - 1]) ? 1 : 0;
        		}
        		out.println(cnt_colors);
        			       		
        	}
        	
        	
//        }
        

        out.flush();

	}

}
