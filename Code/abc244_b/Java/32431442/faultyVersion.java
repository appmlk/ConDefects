
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String str = sc.next();
		char[] c = str.toCharArray();
		int X = 0;
		int Y = 0;
		//方角
		char muki = 'E';
		for(int i =0; i<c.length; i++) {
				if(c[i] == 'S') {
					if(muki == 'N') {
						Y++;
					}else if(muki == 'E') {
						X++;					
					}else if(muki == 'S') {
						Y--;				
					}else if(muki == 'W') {
						X--;			
					}
				}else if(c[i] == 'R') {
					if(muki == 'N') {
						muki =  'E';
					}else if(muki == 'E') {
						muki = 'S';					
					}else if(muki == 'S') {
						muki = 'W';					
					}else if(muki == 'W') {
						muki = 'N';					
					}
				}
				System.out.println(X+" "+Y);
		}
	}

}
