import java.util.*;
class Main {
    
	public static void main(String[] args) { 
		Scanner scanner =new Scanner(System.in);
		int n=scanner.nextInt();
		int k=scanner.nextInt();
		int x=scanner.nextInt();
		
		int[]value=new int[n];
		for(int i=0;i<n;i++){
		    int y=scanner.nextInt();
		    if(y/x<=k){
		        k-=y/x;
		        value[i]=y%x;
		    } else{
		        value[i]=y-x*k;
		        k=0;
		    }
		}
		long ans=0;
		Arrays.sort(value);
		
		for(int i=n-1;i>=0;i--){
		    if(k>0){
		        k--;
		    } else{
		        value[i]+=ans;
		    }
		}
		System.out.println(ans);
		
	}
}
