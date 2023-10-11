import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[]args) throws IOException {
		Scanner sc=new Scanner(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=sc.nextInt();
		int[]a=new int[n];
		for(int i=0;i<n;i++)a[i]=sc.nextInt();
		long cur=1l<<a[0];
		for(int i=1;i<n;i++) {
			int f=a[i];
			long mn=1l<<f;
			long diff=cur-mn;
			if(diff<0) {
				cur=mn;continue;
			}
			diff++;//strict inc
			long delta=0;//ans = mn + delta (delta is smallest added value making ans>=diff)
			//delta>=1l<<(f+1)
			long lo=1,hi=1l<<(10-f);
			while(lo<=hi) {
				long mid=(lo+hi)>>1l;
				long num=mid<<(f+1);
				if(mn+num>cur) {
					delta=num;
					hi=mid-1;
				}else {
					lo=mid+1;
				}
			}
			cur=mn+delta;
		}
		out.println(cur);
		out.close();
	}
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		public boolean hasNext() {return st.hasMoreTokens();}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public double nextDouble() throws IOException {return Double.parseDouble(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready(); }
		

	}
}
