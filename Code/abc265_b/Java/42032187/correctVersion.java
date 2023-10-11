import java.util.*;
class Main {
	public static void main(String[] args) { 
		Scanner scanner =new Scanner(System.in);
		int n=scanner.nextInt();
		int m=scanner.nextInt();
		long t=scanner.nextLong();
		
		int[]a=new int[n-1];
		for(int i=0;i<n-1;i++){
		    a[i]=scanner.nextInt();
		}
		int[]bonas=new int[n];
		for(int i=0;i<m;i++){
		    int x=scanner.nextInt();
		    int y=scanner.nextInt();
		    bonas[x-1]=y;
		}
		for(int i=0;i<n-1;i++){
		    t-=a[i];
		    if(t<=0){
		        break;
		    }
		    t+=bonas[i+1];
		    
		}
		if(t>0){
		    System.out.println("Yes");
		} else{
		    System.out.println("No");
		}
	}
}