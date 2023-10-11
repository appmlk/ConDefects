import java.util.Scanner;

public class Main {

	public static void main(String[] args) {


		//整数の入力を受け付ける
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		
		//42を超える数字であった場合は+1する
		if(num>=42) {
			num = num+1;
		}
		
		//数字を0埋めして表示する
		String formatStr = String.format("AGC%03d", num);
		
		System.out.println(formatStr);
	}

}
