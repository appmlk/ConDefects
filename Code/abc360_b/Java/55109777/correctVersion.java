import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String S = sc.next(), T = sc.next();
		
		sc.close();
		for(int w = 1; w < S.length() - 1; w++) {
			for(int c = 1; c <= w; c++) {
				StringBuilder sb = new StringBuilder();
				
				for(int i = 0; i < S.length(); i += w) {
					String sub;
					if(i + w >= S.length()) {
						sub = S.substring(i);
					}else {
						sub = S.substring(i, i+w);
					}
					if(sub.length() >= c) {
						sb.append(sub.charAt(c - 1));
						//System.out.println("sub : " + sb.toString());
					}
				}
				
				if(sb.toString().equals(T)) {
					System.out.println("Yes");
					return;
				}
				
			}
		}
		
		System.out.println("No");
		
	}
}
