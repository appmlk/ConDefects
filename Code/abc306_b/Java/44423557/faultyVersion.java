import java.math.BigInteger;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		BigInteger plus = BigInteger.ONE;
		BigInteger ans = BigInteger.ZERO;
		for(int i = 0;i < 63;i++) {
			int a = sc.nextInt();
			if(a == 1) {
				//System.out.print(ans + ", ");
				ans = ans.add(plus);
				//System.out.println(ans);
			}
			plus = plus.multiply(BigInteger.TWO);
			//System.out.println(plus);
		}
		System.out.print(ans);
	}
}