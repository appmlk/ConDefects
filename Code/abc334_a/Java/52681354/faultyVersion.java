import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int b = sc.nextInt();
		int g = sc.nextInt();
		if(b>g) {
			System.out.println("Bat");
		}else {
			System.out.println("Globe");
		}
	}

}
