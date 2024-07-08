import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int h = Integer.parseInt(sc.next());
		int x = Integer.parseInt(sc.next());
		for (int i = 1; i <= n; i++) {
			if (x - h <= Integer.parseInt(sc.next())) {
				System.out.println(i);
				break;
			}
		}
		sc.close();
	}
}