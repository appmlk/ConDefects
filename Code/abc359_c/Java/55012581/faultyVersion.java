import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long sx = sc.nextLong();
		long sy = sc.nextLong();
		long tx = sc.nextLong();
		long ty = sc.nextLong();
		if((sx+ty)%2 == 1) {
			sx--;
		}
		if((tx+ty)%2 == 1) {
			tx--;
		}
		long x = Math.abs(tx-sx);
		long y = Math.abs(ty-sy);
		if(x < y) {
			System.out.println(y);
		} else {
			System.out.println((x+y)/2);
		}
	}
}