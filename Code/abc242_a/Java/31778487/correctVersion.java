import java.util.Scanner;

public class Main
{
    public static void main(String[] args) 
    {
    	Scanner sc = new Scanner(System.in);
    	
    	// int[][] array = new int[2][];
    	int A = Integer.parseInt(sc.next());
    	int B = Integer.parseInt(sc.next());
    	int C = Integer.parseInt(sc.next());
    	int X = Integer.parseInt(sc.next());
    	sc.close();
    	
    	if (A >= X) {
    		System.out.println(1);
    	}  	
    	else if (B >= X) {
    		double res = (double)C / (B - A);
       		System.out.println(res);    		
    	}
    	else {
       		System.out.println(0);    	
    	}
    }
}