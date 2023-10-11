import java.util.*;
class Main {
	public static void main(String[] args) { 
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[]a=new int[n];
		int[]b=new int[m];
		for(int i=0;i<n;i++){
		    a[i]=sc.nextInt();
		}
		for(int i=0;i<m;i++){
		    b[i]=sc.nextInt();
		}
		Arrays.sort(a);
		Arrays.sort(b);
		int left=0;
		int right=1000000001;
		while(right-left>1){
		    int mid=(left+right)/2;
		    int ac=0;
		    int bc=0;
		    for(int i=0;i<n;i++){
		        if(a[i]<=mid){
		            ac++;
		        }
		    }
		    for(int i=0;i<m;i++){
		        if(b[i]>=mid){
		            bc++;
		        }
		    }
		    if(ac<bc){
		        left=mid;
		    } else{
		        right=mid;
		    }
		}
		System.out.println(left+1);
	}
}