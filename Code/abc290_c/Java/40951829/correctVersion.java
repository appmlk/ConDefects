import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void printArray(int[]a) {
		for(int i=0;i<a.length-1;i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println(a[a.length-1]);
	}
	public static long lmax(long a,long b) {
		if(a<b)return b;
		else return a;
	}
	public static long lmin(long a,long b) {
		if(a>b)return b;
		else return a;
	}
	public static int max(int a,int b) {
		if(a<b)return b;
		else return a;
	}
	public static int min(int a,int b) {
		if(a>b)return b;
		else return a;
	}
	public static int[] setArray(int n) {
		int a[]=new int[n];
		for(int i=0;i<n;i++)a[i]=scan.nextInt();
		return a;
	}
	public static long[] lsetArray(int n) {
		long a[]=new long[n];
		for(int i=0;i<n;i++)a[i]=scan.nextLong();
		return a;
	}
	public static int abs(int n) {
		if(n<0)n*=-1;
		return n;
	}
	public static long labs(long n) {
		if(n<0)n*=-1;
		return n;
	}
	static Scanner scan=new Scanner(System.in);
	public static void main(String[] args) {
		int n=scan.nextInt();
		int k=scan.nextInt();
		int count[]=new int[3*100000+100];
		int a[]=setArray(n);
		for(int e:a) {
			if(e<count.length)
				count[e]++;
		}
		int ans=0;
		while(count[ans]!=0)ans++;
		System.out.println(min(ans,k));
	}
}
