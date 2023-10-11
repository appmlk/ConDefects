
/*
 * Author: rickytsung
 * Date: 2023/2/9
 * Problem: ABC 256 Ex
 */

import java.util.*;
import java.time.*;
import java.io.*;
import java.math.*;
public class Main{
	public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	public static long ret,cnt;
	public static int reti,rd,n,m,ans;
	public static boolean neg,b1,b2,b3;
	public static final int mod=998244353,mox=998244352,mod2=1_000_000_007,ma=200005;
	public static Useful us=new Useful(mod);
	public static int[] A=new int[ma];
	public static int[] B=new int[ma];
	public static long[] Al=new long[ma];
	public static long[] Bl=new long[ma];
	public static ArrayList<ArrayList<Integer>> E=new ArrayList<>();
	public static void main(String[] args) throws Exception{
		final int n=readint();
		for(int i=0;i<n;i++) {
			int[] A=new int[3];
			for(int j=0;j<3;j++) {
				A[j]=readint();
			}
			Arrays.sort(A);
			long s=A[2]-A[0]; //2x+4y=s
			long t=A[2]-A[1]; //4x+2y=t
			long u=s+t;
			if(2*t-s<0) {
				u-=2*t-s;
			}
			if(2*s-t<0) {
				u=Math.max(u,s+t-(2*s-t));
			}
			if(s%2!=0||t%2!=0) {
				bw.write("-1\n");
				continue;
			}
			bw.write((u/6)+"\n");
			//break;
		}
		bw.flush();
	}
	

/*

 */
	public static int readint() throws Exception{
		reti=0;
		neg=false;
		while(rd<48||rd>57) {
			rd=br.read();
			if(rd=='-') {
				neg=true;
			}
		}
		while(rd>47&&rd<58) {
			reti*=10;
			reti+=(rd&15);
			rd=br.read();
		}
		if(neg)reti*=-1;
		return reti;
	}
	
	public static long readlong() throws Exception{
		ret=0;
		neg=false;
		while(rd<48||rd>57) {
			rd=br.read();
			if(rd=='-') {
				neg=true;
			}
		}
		while(rd>47&&rd<58) {
			ret*=10;
			ret+=(rd&15);
			rd=br.read();
		}
		if(neg)ret*=-1;
		return ret;
	}
	
	public static int pint(String in) {
		return Integer.parseInt(in);
	}
	
	public static long plong(String in) {
		return Long.parseLong(in);
	}
	
	public static void outn() {
		System.out.println();
	}
	
	public static void outn(long in) {
		System.out.println(in);
	}
	
	public static void outn(boolean in) {
		System.out.println(in);
	}
	
	public static void outn(String in) {
		System.out.println(in);
	}
	
	public static void out(long in) {
		System.out.print(in);
	}
	
	public static void out(boolean in) {
		System.out.print(in);
	}
	
	public static void out(String in) {
		System.out.print(in);
	}
}

/*

 */
 
/*
 
 */
class ooo{
	int x,y,z;
	ooo(int a,int b,int c){
		x=a;
		y=b;
		z=c;
	}
	
}

class Pii{
	int x,y;
	Pii(int a,int b){
		x=a;
		y=b;
	}
	@Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof Pii)) return false;
        Pii key = (Pii) o;
        return x==key.x&&y==key.y;
    }

    @Override
    public int hashCode() {
        long result=x;
        result=31*result+y;
        return (int)(result%998244353);
    }
}

class Pll{
	long x,y;
	Pll(long a,long b){
		x=a;
		y=b;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof Pll)) return false;
        Pll key = (Pll) o;
        return x==key.x&&y==key.y;
    }

    @Override
    public int hashCode() {
        long result=x;
        result=31*result+y;
        return (int)(result%998244353);
    }
}

class Useful{
	long mod;
	Useful(long m){mod=m;}
	void al(ArrayList<ArrayList<Integer>> a,int n){for(int i=0;i<n;i++) {a.add(new ArrayList<Integer>());}}
	void arr(int[] a,int init) {for(int i=0;i<a.length;i++) {a[i]=init;}}
	void arr(long[] a,long init) {for(int i=0;i<a.length;i++) {a[i]=init;}}
	void arr(int[][] a,int init) {for(int i=0;i<a.length;i++) {for(int j=0;j<a[i].length;j++) {a[i][j]=init;}}}
	void arr(long[][] a,long init) {for(int i=0;i<a.length;i++) {for(int j=0;j<a[i].length;j++) {a[i][j]=init;}}}
	void arr(int[][][] a,int init) {for(int i=0;i<a.length;i++) {for(int j=0;j<a[i].length;j++) {for(int k=0;k<a[i][j].length;k++) {a[i][j][k]=init;}}}}
	void arr(long[][][] a,long init) {for(int i=0;i<a.length;i++) {for(int j=0;j<a[i].length;j++) {for(int k=0;k<a[i][j].length;k++) {a[i][j][k]=init;}}}}
	void arr(int[][][][] a,int init) {for(int i=0;i<a.length;i++) {for(int j=0;j<a[i].length;j++) {for(int k=0;k<a[i][j].length;k++) {Arrays.fill(a[i][j][k],init);}}}}
	void arr(long[][][][] a,long init) {for(int i=0;i<a.length;i++) {for(int j=0;j<a[i].length;j++) {for(int k=0;k<a[i][j].length;k++) {Arrays.fill(a[i][j][k],init);}}}}
	long fast(long x,long pw) {
		if(pw<=0)return 1;
		if(pw==1)return x;
		long h=fast(x,pw>>1);
		if((pw&1)==0) {
			return h*h%mod;
		}
		return h*h%mod*x%mod;
	}
	long[][] mul(long[][] a,long[][] b){
		long[][] c=new long[a.length][b[0].length];
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<b[0].length;j++) {
				for(int k=0;k<a[0].length;k++) {
					c[i][j]+=a[i][k]*b[k][j];
					c[i][j]%=mod;
				}
			}
		}
		return c;
	}
 
	long[][] fast(long[][] x,int pw){
		if(pw==1)return x;
		long[][] h=fast(x,pw>>1);
		if((pw&1)==0) {
			return mul(h,h);
		}
		else {
			return mul(mul(h,h),x);
		}
	}
	int gcd(int a,int b) {
		if(a==0)return b;
		if(b==0)return a;
		return gcd(b,a%b);
	}
	long gcd(long a,long b) {
		if(a==0)return b;
		if(b==0)return a;
		return gcd(b,a%b);
	}
	long lcm(long a, long b){
	    return a*(b/gcd(a,b));
	}
	double log2(int x) {
		return (Math.log(x)/Math.log(2));
	}
	double log2(long x) {
		return (Math.log(x)/Math.log(2));
	}
}
