import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		
		Pattern p = Pattern.compile("^(and|not|that|the|you)$");
		for(int i = 0; i < n; i++) {
			String w = sc.next();
			Matcher m = p.matcher(w);
			if(m.find()) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
