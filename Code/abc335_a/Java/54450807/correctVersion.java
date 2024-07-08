import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();

		StringBuilder sb = new StringBuilder(s);
		sb.replace(s.length() - 1, s.length(), "4");
		
		String answer = sb.toString();
		System.out.println(answer);
	}
}
