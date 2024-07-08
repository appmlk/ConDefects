import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//整数列
		int N = scanner.nextInt();
		int L = scanner.nextInt();
		int R = scanner.nextInt();
		
		for(int i = 0; i < N; i++) {
		  int A = scanner.nextInt();
		  int X;
		  if (L <= A && A <= R) {
		    X = A;
		  } else if (A < L) {
		    X = L;
		  } else {
		    X = R;
		  }
		  System.out.println("X");
		}
		
		scanner.close();
	}
}
