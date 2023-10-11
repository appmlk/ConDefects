import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long t = sc.nextLong();
		for(int i=0; i<t; i++) {
			long n = sc.nextLong();
			long k = sc.nextLong();
			boolean ans = false;
			Long l = 0L;
			String s = Long.toString(n, 3);
			String[] num = s.split("");
			for(int j=0; j<s.length(); j++) {
				l += Integer.parseInt(num[j]);
			}
			if(k%2 != 0 && n%2 != 0) {
				if(l<=k && k<=n) {
					ans = true;
				}
			}else {
				if(l<=k && k<=n) {
					ans = true;
				}
			}
			System.out.println(ans ? "Yes":"No");
		}
		
		
	}
}