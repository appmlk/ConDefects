import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x = sc.nextLong();
		System.out.println(x<0?(x-9)/10:x/10);
	}
}
