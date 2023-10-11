import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
	public static void doublesort(int a[][]){
		merge(0,a.length-1,a);
		return;
	}
	public static boolean compare(int a,int b,int[][]origin) {
		for(int i=0;i<origin[a].length;i++) {
			if(origin[a][i]>origin[b][i]) {
				return false;
			}
			else if(origin[a][i]<origin[b][i]) {
				return true;
			}
		}
		return true;
	}
	public static void merge(int left,int right,int[][]origin) {
		if(left==right) {
			return;
		}
		else {
			int mid=(left+right)/2;
			merge(left,mid,origin);
			merge(mid+1,right,origin);
			int hoge2[][]=new int[right-left+1][origin[0].length];
			int itr=0;
			int leftcount=0;
			int rightcount=0;
			while(leftcount<=(mid-left)||rightcount<=(right-(mid+1))) {
				if(leftcount==mid-left+1) {
					for(int i=0;i<origin[0].length;i++) {
						hoge2[itr][i]=origin[mid+rightcount+1][i];
					}
					rightcount++;
				}
				else if(rightcount==right-(mid+1)+1) {
					for(int i=0;i<origin[0].length;i++) {
						hoge2[itr][i]=origin[left+leftcount][i];
					}
					leftcount++;
				}
				else {
					if(compare(left+leftcount,mid+rightcount+1,origin)) {
						for(int i=0;i<origin[0].length;i++) {
							hoge2[itr][i]=origin[left+leftcount][i];
						}
						leftcount++;
					}
					else {
						for(int i=0;i<origin[0].length;i++) {
							hoge2[itr][i]=origin[mid+rightcount+1][i];
						}
						rightcount++;
					}
				}
				itr++;
			}
			for(int i=0;i<(right-left+1);i++) {
				for(int j=0;j<origin[0].length;j++) {
					origin[left+i][j]=hoge2[i][j];
				}
			}
		}
	}
	
	static Scanner scan=new Scanner(System.in);
	public static void main(String[] args) {
		int n=scan.nextInt();
		int l=scan.nextInt();
		int a[]=setArray(n);
		for(int i=0;i<n;i++) {
			if(l==2&&a[i]==2) {
				l-=2;
			}
			else if(l>=2) {
				l-=(a[i]+1);
			}
			else if(l<=1&&a[i]==2) {
				System.out.println("No");
				return;
			}
		}
		System.out.println("Yes");
	}
}

