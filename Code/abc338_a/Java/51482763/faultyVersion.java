import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		//StringBuilder sb = new StringBuilder();
		char [] s = sc.next().toCharArray();
		if(s[0] < 'A' || 'Z' < s[0]) {
			System.out.println("No");
			return;
		}
		for(int i = 1; i < s.length; i++) {
			if(s[0] < 'a' || 'z' < s[0]) {
				System.out.println("No");
				return;
			}
		}
		System.out.println("Yes");

		//.append("\n");
		//System.out.print(sb);

	}

}
