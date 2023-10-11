// https://atcoder.jp/contests/abc241/tasks/abc241_b
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan =new Scanner(System.in);

		String s = scan.nextLine();
		String n = scan.nextLine();
		String m = scan.nextLine();
		int count = 0;

		String[] N =n.split(" ");
		String[] M =m.split(" ");

		System.out.println(Arrays.toString(N));
		System.out.println(Arrays.toString(M));

		a: for(int i = 0; i < M.length; i++) {
			for(int j = 0; j < N.length; j++) {
				if(M[i].equals(N[j])) {
					N[j] = "";
					count++;
					continue a;
				}
			}
		}

		if(M.length==count) {
			System.out.println("Yes");
		}else if(M.length!=count) {
			System.out.println("No");

		}



	}

}
