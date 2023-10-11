import java.util.Scanner;

public class Main {
	final static String ATOZ ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		long ans = 0;
		ans = (ATOZ.indexOf(str.charAt(str.length() -1)) + 1);
		//System.out.println(ans);
		for(int i = str.length() - 2; i >= 0;i--) {
			//System.out.println((ATOZ.indexOf(str.charAt(i)) + 1) * Math.pow(26,str.length() - i - 1));
			ans += (ATOZ.indexOf(str.charAt(i)) + 1) * (long)Math.pow(26,str.length() - i - 1);
		}
		System.out.print(ans);
	}

}
