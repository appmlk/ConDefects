import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int x = scan.nextInt();
		
		int y = scan.nextInt()-x;
		
		int count = 0;
		
		x = 0;
		
		if(y<0) {
			System.out.println(count);
			return;
		}
		while(true) {
			x+=10;
			count++;
			if(x>=y) {
				System.out.println(count);
				return;
			}
		}
		
	}

}
