import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		int num = scn.nextInt();
		String str1 = scn.next();
		String str2 = scn.next();
		
		str1 = str1.replace("0", "o").replace("1","l");
		str2 = str2.replace("0", "o").replace("1","l");
		if(str1==str2) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		scn.close();
	}

}