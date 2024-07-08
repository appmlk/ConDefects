import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = Integer.parseInt(sc.next());
		int b = Integer.parseInt(sc.next());
		sc.close();
		System.out.println((int) Math.pow(a, b) + (int) Math.pow(b, a));
	}
}
