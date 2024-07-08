	import java.io.*;
	import java.lang.*;
	import java.util.*;
	public class Main {
		static HashMap<Long,Double> hm;
		static int dp[][][];
		public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub
					BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
//			PrintWriter out = new PrintWriter(System.out);
			FastReader sc=new FastReader();
			int t=1;
			while(t--!=0) {
			   hm=new HashMap<>();
			   long n=sc.nextLong();
			   long a=sc.nextInt();
			   long x=sc.nextInt();
			   long y=sc.nextInt();
			   log.write(eval(n,a,x,y)+"\n");
                
			}
			log.flush();
		      
//             }
			
		}
static double eval(long n,long a,long x,long y) {
	if(n==0)return 0;
	if(hm.containsKey(n))return hm.get(n);
	long sm=6*y;
	for(int i=2;i<=6;i++) {
		sm+=eval(n/i,a,x,y);
	}
	double ans=Math.min(x+eval(n/a,a,x,y),sm/(double)5);
	hm.put(n,ans);
	return ans;
}
static boolean fill(boolean vis[][],int i,int j,int x,int y) {
	int c1=0;
	for(int l=i;l<=i+x-1;l++) {
		for(int r=j;r<=j+y-1;r++) {
			if(vis[l][r])c1++;
		}
	}
	if(c1>0 && c1<x*y)return false;
	for(int l=i;l<=i+x-1;l++) {
		for(int r=j;r<=j+y-1;r++) {
			if(vis[l][r])vis[l][r]=false;
			else vis[l][r]=true;
		}
	}
    return true;
}

public static class pair{
	int a,b;
	public pair(int a,int b) {
		this.a=a;
		this.b=b;
	}
}
public static class pairr{
	int a,b;
	public pairr(int a,int b) {
		this.a=a;
		this.b=b;
	}
}
public static class FastReader {
	
	BufferedReader b;
	StringTokenizer s;
	public FastReader() {
		b=new BufferedReader(new InputStreamReader(System.in));
	}
	String next() {
		while(s==null ||!s.hasMoreElements()) {
			try {
				s=new StringTokenizer(b.readLine());
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		return s.nextToken();
	}
	public int nextInt() {
		return Integer.parseInt(next());
	}
	public long nextLong() {
		return Long.parseLong(next());
	}
	public double nextDouble() {
		return Double.parseDouble(next());
	}
	String nextLine() {
		String str="";
		try {
			str=b.readLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	boolean hasNext() {
	    if (s != null && s.hasMoreTokens()) {
	        return true;
	    }
	    String tmp;
	    try {
	        b.mark(1000);
	        tmp = b.readLine();
	        if (tmp == null) {
	            return false;
	        }
	        b.reset();
	    } catch (IOException e) {
	        return false;
	    }
	    return true;
	}
}
	}
