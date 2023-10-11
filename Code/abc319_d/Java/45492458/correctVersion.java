

import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

	static class Pair {
		long x;
		int y;

		Pair(long x, int y) {
			this.x = x;
			this.y = y;

		}
	}

	static class Compare {

		void compare(Pair arr[], int n) {
			// Comparator to sort the pair according to second element
			Arrays.sort(arr, new Comparator<Pair>() {
				@Override
				public int compare(Pair p1, Pair p2) {
					if (p1.x != p2.x) {
						return (int) (p1.x - p2.x);
					} else {
						return (int) (p1.y - p2.y);
					}
//		            	return Double.compare(a[0], b[0]);
				}
			});

//		        for (int i = 0; i < n; i++) { 
//		            System.out.print(arr[i].x + " " + arr[i].y + " "); 
//		        } 
//		        System.out.println(); 
		}
	}

	static class Scanner {
		BufferedReader br;
		StringTokenizer st;

		public Scanner() {
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

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
   
	static boolean function(int a[],long mid,int m) {
		
		long till = 0;
		int fill = 0;
		
		int i = 0;
		
		while(i < a.length) {
			if(a[i] > mid) {
				break;
			}
			if(a[i] + till <= mid) {
				till += a[i] + 1;
				i++;
			}
			else {
				fill++;
				till = 0;
			}
		}
		
		return (i == a.length && fill + 1 <= m);
	}
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner();
		StringBuilder res = new StringBuilder();

		int tc = 1;

		while (tc-- > 0) {

			int n = sc.nextInt();
			int m = sc.nextInt();
			
			int a[] = new int[n];
			
			for(int i=0;i<n;i++) {
				a[i] = sc.nextInt();
			}
			
			long l = 1;
			long r = (long)(1e15);
			long ans = -1;
			
			while(l <= r) {
				
				long mid = l + (r - l)/2;
				
				if(function(a,mid,m)) {
					ans = mid;
					r = mid - 1;
				}
				else {
					l = mid + 1;
				}
			}
			
			res.append(ans);
		}

		System.out.println(res);
	}
}
