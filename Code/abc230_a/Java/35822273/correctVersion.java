import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		scan.close();
		
		int a = 0;
		String s = "";
		
		if(n >= 42) {
			a = n + 1;
		} else {
			a = n;
		}

		if(a <= 99) {
//			s = "0" + String.valueOf(a);
			System.out.println("AGC" + String.format("%03d", a));
			
		} else {
//			s = String.valueOf(a);
			System.out.println("AGC" + a);

			
		}

		

	}

}
