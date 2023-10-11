import java.util.*;
import java.io.*;
class Main{
	static ArrayList<ArrayList<Integer>> list;
	static FastScanner fs = null;
	public static void main(String[] args) {
		fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int n = fs.nextInt();
		int a[] = new int[n];
		for(int i=0;i<n;i++){
			a[i] = fs.nextInt();
		}
		int flip = 0;
		boolean res = true;
		int st = 0;
		int end = n-1;
		while(end>=st){
			int d = flip%2;
			int c = 0;
			while (end>=0 && (d+a[end])%2==1) {
				end-=1;
				c+=1;	
			}
			if(c>0){
				if(end<=st){
					res = false;
					break;
				}
				if((a[st]+flip)%2==0){
					st+=1;
				}
				else{
					res = false;
					break;
				}
				flip+=1;
			}
			else{
				while (end>=0 && (d+a[end])%2==0) {
				end-=1;
				}
				if(st==end){
					res = false;
					break;
				}
			}
		}
		if(res)
			out.println("Yes");
		else
			out.println("No");
		out.close();
	}
	static void sort(int[] a) {
		ArrayList<Integer> l=new ArrayList<>();
		for (int i:a) l.add(i);
		Collections.sort(l);
		for (int i=0; i<a.length; i++) a[i]=l.get(i);
	}
	static class FastScanner {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer("");
		String next() {
			while (!st.hasMoreTokens())
				try {
					st=new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		int[] readArray(int n) {
			int[] a=new int[n];
			for (int i=0; i<n; i++) a[i]=nextInt();
			return a;
		}
		long nextLong() {
			return Long.parseLong(next());
		}
		double nextDouble() {
			return Double.parseDouble(next());
		}
	}
}