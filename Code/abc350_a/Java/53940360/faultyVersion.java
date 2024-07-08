import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		String s = sc.next();
		for(int i = 3; i < 6; i ++ ) {
			if(s.charAt(i) < '0' || s.charAt(i) > '9') {
				System.out.print("No");
				return ;
			}
		}
		String s1 = s.substring(0, 3);
		if(!s1.equals("ABC")) {
			System.out.print("No");
			return ;
		}
		int s2 = Integer.valueOf(s.substring(3));
		
		if(s2 > 349 || s2 == 316) {
			System.out.print("No");
			return ;
		} else {
			System.out.print("Yes");
		}
	}
}
