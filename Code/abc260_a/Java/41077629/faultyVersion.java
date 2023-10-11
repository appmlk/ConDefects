import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner input = new Scanner(System.in);
		String s = input.next();
		
		if(s.charAt(0) != s.charAt(1) && s.charAt(0) != s.charAt(2)) {
			System.out.println(s.charAt(0));
		}else if(s.charAt(1) != s.charAt(2) && s.charAt(1) != s.charAt(2)){
			System.out.println(s.charAt(1));
		}else if(s.charAt(2) != s.charAt(0) && s.charAt(1) != s.charAt(2)){
			System.out.println(s.charAt(2));
		}else {
			System.out.println(-1);
		}
	}
}