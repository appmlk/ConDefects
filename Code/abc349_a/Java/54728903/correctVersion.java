import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// inputA
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.next());
		int A[] = new int[N-1];
		Arrays.setAll(A, e -> Integer.parseInt(sc.next()));
		
		int sum = Arrays.stream(A).sum();
		System.out.println(sum * (-1));
	} 
}