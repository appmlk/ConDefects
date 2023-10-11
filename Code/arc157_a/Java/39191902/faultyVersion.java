import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int a = scan.nextInt();//xx
		int b = scan.nextInt();//xy
		int c = scan.nextInt();//yx
		int d = scan.nextInt();//yyの数
		//n文字にn-1個のabcdいずれかのパターンがある。
		String xyString = new String();
		if(!(a == 0 || b == 0) && a + b >= 2 && c + d == 0) {
			System.out.println("No");
		}else if(b - c >= 2) {
				System.out.println("No");
		}else if(c - b >= 2) {
				System.out.println("No");
		}else
			System.out.println("Yes");
	}

}
