import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int q = scan.nextInt();
		int[] circumstance = new int[n];
		for (int i = 0; i < n; i++) {
			circumstance[i] = 0;
		}
		for (int k = 0; k < q; k++) {
			int query1 = scan.nextInt();
			int query2 = scan.nextInt();
			if (query1 == 1 || query1 == 2) {
				circumstance[query2-1] = circumstance[query2-1] + query1;
			} else {
				if (circumstance[query2-1] >= 2) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
				}
			}
		}
		scan.close();
	}
}