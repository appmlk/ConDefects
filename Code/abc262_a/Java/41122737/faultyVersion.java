import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner input = new Scanner(System.in);
		int Y = input.nextInt();
		
		for(int i = Y; i < 3001; i++) {
			if(i%4 ==2) {
				System.out.println(i);
				break;
			}
		}
	}
}