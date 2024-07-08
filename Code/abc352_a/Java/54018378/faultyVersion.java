import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.next());
		int X = Integer.parseInt(sc.next());
		int Y = Integer.parseInt(sc.next());
		int Z = Integer.parseInt(sc.next());
		
		if(X<=Z&&Z<=Y) {
			System.out.print("Yes");
		}else {
			System.out.print("No");
		}

	}

}