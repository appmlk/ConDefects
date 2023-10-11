import java.util.*;
class Main {
	public static void main(String[] args) { 
		Scanner scanner = new Scanner(System.in);
		int n =scanner.nextInt();
		int K =scanner.nextInt();
		int[]a=new int[n];
		int[]b=new int[n];
		for(int i=0;i<n;i++){
		    a[i]=scanner.nextInt();
		}
		for(int i=0;i<n;i++){
		    b[i]=scanner.nextInt();
		}
		int[]dp=new int[n+1];
		int[]ep=new int[n+1];
		dp[1]=1;
		ep[1]=1;
		
		for(int i=2;i<=n;i++){
		    if(dp[i-1]==1 && Math.abs(a[i-1] - a[i-2]) <= K){
		        dp[i]=1;
		    }
		    if(ep[i-1]==1 && Math.abs(b[i-1] - a[i-2]) <= K){
		        dp[i]=1;
		    }
		    if(dp[i-1]==1 && Math.abs(b[i-1] - a[i-2]) <= K){
		        ep[i]=1;
		    }
		    if(ep[i-1]==1 && Math.abs(b[i-1] - b[i-2]) <= K){
		        ep[i]=1;            
		    }
		}
		if(dp[n]==1 || ep[n]==1){
		    System.out.println("Yes");
		} else{
		    System.out.println("No");
		}
	}
}