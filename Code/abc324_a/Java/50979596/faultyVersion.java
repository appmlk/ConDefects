import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		int N=sc.nextInt();
		int First=sc.nextInt();
		int count=1;
		
		for(int i=2;i<=N;i++){
		  
		  	int next=sc.nextInt();
		  	
		  	if (next!=First){
		  	  i++;
		  	  
		  	}
		  	
		  	else{
		  	  First=next;
		  	  count++;
		  	}
		  	
		
		}
		if(count==N){
		System.out.print("Yes");
	}
	else{
	  	System.out.print("NO");
	  
	}
}
}
