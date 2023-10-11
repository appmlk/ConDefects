import java.util.*;
class Main {
    
	public static void main(String[] args) { 
		Scanner scanner = new Scanner(System.in);
		int n=scanner.nextInt();
		int m=scanner.nextInt();
		int[]a=new int[n];
		int[]b=new int[m];
		for(int i=0;i<n;i++){
		    a[i]=scanner.nextInt();
		}
		for(int i=0;i<m;i++){
		    b[i]=scanner.nextInt();
		}
		int[]c=new int[n+m];
		
		for(int i=0;i<n;i++){
		    c[i]=a[i];
		}
		for(int i=0;i<m;i++){
		    c[n+1]=b[i];
		}
		Arrays.sort(c);
		
		for(int i=0;i<n;i++){
		    int ans=Arrays.binarySearch(c,a[i])+1;
		    System.out.print(ans+" ");
		}
		System.out.println();
		
		for(int i=0;i<m;i++){
		    int ans=Arrays.binarySearch(c,b[i])+1;
		    System.out.print(ans+" ");
		}
		
	}
}