
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int s = sc.nextInt();
		int ans = 0;
		if(t/4 == 1 || s/4 == 1 ) {
			ans += 4;
		}
		t %= 4;
		s %= 4;
		if(t/2 == 1 || s/2 == 1) {
			ans += 2;
		}
		t %= 2;
		s %= 1;
		if(t == 1 || s == 1) {
			ans += 1;
		}
		System.out.print(ans);
		

	}

}
