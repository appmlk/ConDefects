import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		String s[] = new String[N];
		int T=0;
		for(int i=0;i<N;i++) {
			s[i] = scanner.next();
			T+=scanner.nextInt();
			
		}
		
		Arrays.sort(s);
		
		System.out.println(s[T%3]);
		
		
		
		scanner.close();
}
}
