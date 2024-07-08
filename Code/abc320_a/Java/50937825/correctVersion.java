import java.util.*;

public class Main {
	public static void main(String[] args){
	    Scanner sc = new Scanner(System.in);
	    long A = sc.nextLong();
	    long B = sc.nextLong();
	    System.out.println((long)Math.pow(A, B)+(long)Math.pow(B, A));
	}
}