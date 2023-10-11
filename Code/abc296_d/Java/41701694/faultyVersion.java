

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw=new PrintWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
//		solve01();
		solve02();
//		solve03();
//		solve04();
//		solve05();
		
		
		
		
		pw.flush();
	}

	private static void solve02() throws IOException {
		String[] s=br.readLine().split(" ");
		long n=Long.parseLong(s[0]);
		long m=Long.parseLong(s[1]);
//		System.out.println(n*n);
		if((m+n-1)/n>n) {
			pw.println(-1);
			return;
		}
//		System.out.println(Math.sqrt(m));
		long ans=Long.MAX_VALUE;
		for(long i=1;i<=Math.min(n, Math.sqrt(m));i++) {
			long r=(m+i-1)/i;
			if(r<=n) {
				ans=Math.min(i*r, ans);
			}
		}
		pw.println(ans);
		
		
	}

	private static void solve01() throws IOException {
		String[] s=br.readLine().split(" ");
		int n=Integer.parseInt(s[0]);
		int x=Integer.parseInt(s[1]);
		long[] nums=new long[n];
		s=br.readLine().split(" ");
		Set<Long> st=new HashSet<Long>();
		for(int i=0;i<n;i++) {
			nums[i]=Integer.parseInt(s[i]);
			st.add(nums[i]);
		}
		for(int i=0;i<n;i++) {
			long t=x+nums[i];
			if(st.contains(t)) {
				pw.println("Yes");
				return;
			}
			t=nums[i]-x;
			if(st.contains(t)) {
				pw.println("Yes");
				return;
			}
		}
		pw.println("No");
		
	}

	

	

	





	
	
	
	
	
}
