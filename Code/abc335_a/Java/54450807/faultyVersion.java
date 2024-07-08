import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();

		StringBuilder sb = new StringBuilder(s);
		sb.replace(s.length(), s.length(), "4");
		
		String answer = sb.toString();
		System.out.println(answer);
	}
}
