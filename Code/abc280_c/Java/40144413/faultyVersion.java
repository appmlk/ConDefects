import java.util.Scanner;


 
public class Main {
 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		String t = scanner.next();
		for(int i=0; i < s.length(); i++) {
			if(s.charAt(i) != t.charAt(i)) {
				System.out.println(i + 1);
				break;
			}
			
			if(i==s.length()-1 && s.charAt(i) == t.charAt(i)) {
				System.out.println(i+1);
			}
		}
	}
}

