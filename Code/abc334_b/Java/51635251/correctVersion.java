import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		long A = sc.nextLong();
		long M = sc.nextLong();
		long L = sc.nextLong() - A;
		long R = sc.nextLong() - A;
		long cnt = 0;
		if (R < 0) {
			R++;
		} else if (L > 0) {
			L--;
		}
		BigDecimal bd = new BigDecimal(R);
		BigDecimal bd1 = new BigDecimal(L);
		BigDecimal bd2 = new BigDecimal(M);
		long cntR =  bd.divide(bd2,RoundingMode.DOWN).longValue();
		long cntL = bd1.divide(bd2,RoundingMode.DOWN).longValue();
		cnt = cntR -cntL;
		if (L < 0 && 0 <= R) {cnt++;}
		//else if ((L % M == 0 || R % M ==0) && (L > 0 && R > 0)) cnt++;
		System.out.println(cnt);
		
		
		sc.close();
		
		
	}
}
	