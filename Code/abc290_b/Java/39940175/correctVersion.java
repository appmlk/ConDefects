import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		
		int k = scanner.nextInt();
		
		String s = scanner.next();
		
		int number = 0;
		
		for(int i = 0; i < n; i ++) {
			if(s.charAt(i) == 'o' && number < k) {
				System.out.print("o");
				number ++;
			}else {
				System.out.print("x");
			}
		}
	}
}