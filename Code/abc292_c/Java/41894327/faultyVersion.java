import java.util.*;
class Main {

	public static void main(String[] args)throws Exception { 
		Scanner scanner = new Scanner(System.in);
		int n=scanner.nextInt();
		int ans=0;
		
		for(int i=1;i<n;i++){
		    int X=0;
		    int Y=n-i;
		    int x=0;
		    int y=0;
		    for(int j=1;j*j<=X;j++){
		        if(X%j==0){
		            x++;
		            if(X!=j*j)x++;
		        }
		    }
		    for(int j=1;j*j<=Y;j++){
		        if(Y%j==0){
		            y++;
		            if(Y!=j*j)y++;
		        }
		    }
		    ans+=x*y;
		}
		System.out.println(ans);
	}
}