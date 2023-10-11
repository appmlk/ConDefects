import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); 
	    	int x;
	    	x=scanner.nextInt();
	     
	    	int res=x%100;
	    	if(res <10) {
	    		System.out.println("0"+res);
	    	}
	    	else {
	    		System.out.println(res);
	    	}
	    	
	}

}
