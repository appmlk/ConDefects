import java.util.*;
import java.lang.*;
import java.io.*;

public class Main
{   
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

    public static int pw_int(int a, int pw) {
        return (int) Math.pow(a, pw);
    }

    public static long pw_lg(int a, int pw) {
        return (long) Math.pow(a, pw);
    }

    public static long gcd(long a, long b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}


    public static void  print(String s) {
		System.out.println(s);
	}
    

    // public static boolean in_grid(int n, int m, int r, int c ) {
    //     if (r < 0 || r > n - 1 || c < 0 || c > m - 1) return false;
    //     return true;
    // }


	public static void main (String[] args) throws Exception
	{

		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);


        // int t = psi(rd.readLine());
        int mod =  (int) Math.pow(10, 9) + 7;

        // for (int tt = 0; tt < t; tt++) {
            
            int n = psi(rd.readLine());
            // String [] arr1 = rd.readLine().split(" ");
            // int [] arr = new int[5 * n];

            // for (int i = 0; i < 5 * n; i++) arr[i] = psi(arr1[i]);

            String s = rd.readLine();

            Set<String> st = new HashSet<>();

            int x = 0;
            int y = 0;

            st.add(str_int(x) + "," + str_int(y));
            
            // System.out.println(str_int(x) + "," + str_int(y));
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == 'R') {
                    x++;
                } else if (s.charAt(i) == 'L') {
                    x--;
                } else if (s.charAt(i) == 'U') {
                    y++;
                } else {
                    y--;
                }

                String el = str_int(x) + "," + str_int(y);
                // System.out.println(str_int(x) + "," + str_int(y));
                st.add(el);
            }

            if (st.size() == n + 1) {
                out.print("No");
            } else {
                out.print("Yes");
            }
            
        // }

        out.flush();         

	}
}



