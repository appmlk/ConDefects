

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		long x = sc.nextLong();
		long ans = 0;

		if(x>=0 || x %10 != 0) {
				ans = x/10;
		}else if(x<0 && x%10 != 0) {
			ans = x/10-1;
		}
		System.out.println(ans);
	}

}
