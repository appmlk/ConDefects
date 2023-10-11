import java.util.*;

public class Main {
	public static final long big = 998244353;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		Long n = sc.nextLong();
		Long x = n - big;
		Long sub;
		if(x < 0) {
			sub = -x;
			x += sub/big * big;
			if(x != 0)x += big;
		}else if(x >= big) {
			sub = x;
			x -= (sub/big - 1) * big;
		}
		System.out.print(x);
	}
}