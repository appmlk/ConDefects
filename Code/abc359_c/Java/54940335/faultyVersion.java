import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			long Sx = sc.nextLong();
			long Sy = sc.nextLong();
			long Tx = sc.nextLong();
			long Ty = sc.nextLong();
			long result = 0;
			
			long xReduction = 0;
			xReduction += Math.abs(Ty - Sy);
			
			result += Math.abs(Ty - Sy);
			
			if (Tx - Sx >= 0) {
				if ((Sx + Sy) % 2 == 0 ) {
					xReduction++;
				}
				
				if ((Tx + Ty) % 2 == 1) {
					xReduction++;
				}
			} else {
				if ((Sx + Sy) % 2 == 1 ) {
					xReduction++;
				}
				
				if ((Tx + Ty) % 2 == 0) {
					xReduction++;
				}
			}
			
			if (xReduction < Math.abs(Tx - Sx)) {
				result += (Math.abs(Tx - Sx) - xReduction) / 2;
			}
			
			System.out.println(result);
		}
	}
}