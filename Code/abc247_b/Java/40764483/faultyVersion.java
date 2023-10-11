import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int n;
		String strSi;
		String strTi;
		ArrayList<String> astrSi = new ArrayList<String>();
		ArrayList<String> astrTi = new ArrayList<String>();
		boolean[] ablnSiErr2;
		boolean[] ablnTiErr2;

		try (Scanner sc = new Scanner(System.in)) {
			n = sc.nextInt();
			ablnSiErr2 = new boolean[n];
			ablnTiErr2 = new boolean[n];
			for (int i = 0; i < n; i++) {
				ablnSiErr2[i] = false;
				ablnTiErr2[i] = false;
			}
			for (int i = 0; i < n; i++) {
				strSi = sc.next();
				strTi = sc.next();

				for (int j = 0; j < astrSi.size(); j++) {
					if (astrSi.get(j).equals(strSi)) {
						ablnSiErr2[i] = true;
					}
					if (astrTi.get(j).equals(strSi)) {
						ablnTiErr2[j] = true;
						ablnSiErr2[i] = true;
					}
					if (astrSi.get(j).equals(strTi)) {
						ablnSiErr2[j] = true;
						ablnTiErr2[i] = true;
					}
					if (astrTi.get(j).equals(strTi)) {
						ablnTiErr2[i] = true;
					}
				}
				astrSi.add(strSi);
				astrTi.add(strTi);
			}
		}
		for (int i = 0; i < n; i++) {
			if (ablnSiErr2[i] && ablnTiErr2[i]) {
				System.out.print("No");
				return;
			}
		}
		System.out.print("Yes");
	}
}