import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		StringBuilder sb = new StrignBuilder();
		int n = sc.nextInt();
		int h = sc.nextInt();
		int x = sc.nextInt();
		for(int i=1; i<=n; i++) {
			if(h+sc.nextInt()>x) {
				System.out.println(i);
				return;
			}
		}
	}

}
