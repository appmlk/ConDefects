import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		String T = sc.next();
		char[] Sbox = S.toCharArray();
		char[] Tbox = T.toCharArray();

		int sabun=(Tbox[0]-Sbox[0]+26)%26;
		for(int i=0;i<S.length();i++) {
			if(sabun!=(Tbox[i]-Sbox[i]+26)%26) {
				System.out.println("No");
				return;
			}
			
		}
		System.out.println("Yes");
		
		
	}

}
