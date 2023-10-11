import java.io.*;

import java.util.*;
/*


*/

 
 public class Main{
	static FastReader sc=null;
	
	public static void main(String[] args) {
		sc=new FastReader();
		int n=sc.nextInt();
		int a=sc.nextInt(),b=sc.nextInt();
		
		int c[]=sc.readArray(n);
		
		ruffleSort(c);
		int l=c[0]-1,r=c[n-1]+2;
		while(l+1<r) {
			int mid=(l+r)/2;
			if(pos(c,a,b,mid))l=mid;
			else r=mid;
		}
		
		System.out.println(l);
	}
	static boolean pos(int c[],int a,int b,int k) {
		int n=c.length;
		
		int req=0,can=0;
		for(int i=0;i<n;i++) {
			if(c[i]>k) {
				int d=c[i]-k;
				can+=d/b;
			}
			else {
				int d=k-c[i];
				req+=(d+a-1)/a;
			}
		}
		
		return can>=req;
	}
	
	static int[] ruffleSort(int a[]) {
		ArrayList<Integer> al=new ArrayList<>();
		for(int i:a)al.add(i);
		Collections.sort(al);
		for(int i=0;i<a.length;i++)a[i]=al.get(i);
		return a;
	}
	
	static void print(int a[]) {
		for(int e:a) {
			System.out.print(e+" ");
		}
		System.out.println();
	}
	
	static class FastReader{
		
		StringTokenizer st=new StringTokenizer("");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String next() {
			while(!st.hasMoreTokens()) 
				try {
					st=new StringTokenizer(br.readLine());
				}
			   catch(IOException e){
				   e.printStackTrace();
			   }
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		int[] readArray(int n) {
			int a[]=new int[n];
			for(int i=0;i<n;i++)a[i]=sc.nextInt();
			return a;
		}
	}
	
	
}
