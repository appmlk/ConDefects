import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		System.out.println(Math.abs(n)>=Math.pow(2, 31)?"No":"Yes");
		sc.close();
	}
}