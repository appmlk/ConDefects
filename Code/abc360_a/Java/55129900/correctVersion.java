import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		
		int pos_r = 0, pos_s = 0;
		for (int i = 0; i < 3; i++) {
			if (s.charAt(i) == 'R') {
				pos_r = i;
			}
			if (s.charAt(i) == 'M') {
				pos_s = i;
			}
		}
		String ans = "No";
		if (pos_r < pos_s) {
			ans = "Yes";
		}
		System.out.println(ans);
		sc.close();
	}

}
