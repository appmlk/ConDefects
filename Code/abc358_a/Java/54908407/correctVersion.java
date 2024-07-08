import java.util.*;
import java.io.*;

class Main {
	
	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader()
		{
			br = new BufferedReader(
				new InputStreamReader(System.in));
		}

		// for each token
		String next()
		{
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		// Scanner parseInt
		int nextInt() { return Integer.parseInt(next()); }

		// Scanner parseLong
		long nextLong() { return Long.parseLong(next()); }


		// Scanner double
		double nextDouble()
		{
			return Double.parseDouble(next());
		}

		// Scanner string 
		String nextLine()
		{
			String str = "";
			try {
				str = br.readLine();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}

	public static String solve(String s, String t){
		return s.equals("AtCoder") && t.equals("Land")? "Yes": "No";
	}
	
	public static void main(String agrgs[]){
        FastReader sc = new FastReader();
		int test = 1; //sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while(test --> 0){
			// int n = sc.nextInt();
			// int arr[] = new int[n];
			// for(int i=0; i<n; i++)
				// arr[i] = sc.nextInt();
			String s = sc.next();
			String t = sc.next();
			String res = solve(s, t);
			sb.append(res+"\n");
		}
		System.out.println(sb.toString());
	}
}
