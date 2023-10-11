import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		int num = scn.nextInt();
		if(num%5 <= 2) {
			System.out.println((num/5)*5);
		} else if(num%5>2) {
			System.out.println((num/5)*5+5);
		}else if(num%5==0) {
			System.out.println(num);
		}
		scn.close();
	}

}