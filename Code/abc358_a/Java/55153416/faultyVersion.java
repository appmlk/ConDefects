import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word1 = sc.next();
		String word2 = sc.next();
		if(word1 == "AtCoder" && word2 == "Land") {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		sc.close();
	}
}
