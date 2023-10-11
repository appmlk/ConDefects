import java.util.*;

public class Main {

	public static void main(String ars[]) {
		
		// 거듭제곱 연산자
		
		Scanner input = new Scanner(System.in);
		
		long N = input.nextLong();
		// int형으로 처음부터 받으려면 
		
		long result = (long) Math.pow(2, 31);
	
		
		if (N >= (result * -1) && N <= result-1 ) {
	
		System.out.println("Yes");
		
		}
		else {
			System.out.println("No");
		}
}
	}
