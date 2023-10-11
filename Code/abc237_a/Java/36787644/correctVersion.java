import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		System.out.println(n<Math.pow(2, 31)&&n>=-1*Math.pow(2, 31)?"Yes":"No");
		sc.close();
	}
}