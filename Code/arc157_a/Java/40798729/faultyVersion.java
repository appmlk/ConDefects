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
		int a=scan.nextInt();
		int b=scan.nextInt();
		int c=scan.nextInt();
		int d=scan.nextInt();
		if(b==0&&c==0){
			if(a==n-1||b==n-1) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
		else if(abs(b-c)<=1) {
			System.out.println("Yes");
		}
		else {
			System.out.println("No");
		}
	}
}
